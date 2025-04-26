package dev.nitron.elegance.block_entity;

import com.google.common.collect.Lists;
import com.nitron.nitrogen.util.ScreenShaker;
import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.block.RoseQuartzPrismBlock;
import dev.nitron.elegance.registration.ModBlockEntities;
import dev.nitron.elegance.registration.ModBlocks;
import dev.nitron.elegance.registration.ModParticles;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.*;

public class RoseQuartzPrismBlockEntity extends BlockEntity {
    private List<BeamSegment> beamSegments = Lists.newArrayList();
    private List<BeamSegment> tempSegments = Lists.newArrayList();
    private int minY;
    private int onTicks = 0;
    private boolean poweredByBeacon = false;

    public RoseQuartzPrismBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ROSE_QUARTZ_PRISM_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("onTicks", this.onTicks);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.onTicks = nbt.getInt("onTicks");
    }

    public void setOnTicks(int ticks, boolean fromBeacon) {
        this.onTicks = ticks;
        this.poweredByBeacon = fromBeacon;
    }

    public static void tick(World world, BlockPos pos, BlockState state, RoseQuartzPrismBlockEntity blockEntity) {
        if (blockEntity.onTicks > 0) {
            blockEntity.onTicks--;
            if (blockEntity.onTicks == 0) {
                blockEntity.poweredByBeacon = false;
            }
        } else {
            blockEntity.beamSegments = List.of();
            return;
        }

        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int topY = world.getTopY(Heightmap.Type.WORLD_SURFACE, i, k);
        int receiverY = -1;
        for (int y = j + 1; y <= topY; y++) {
            BlockPos checkPos = new BlockPos(i, y, k);
            BlockState checkState = world.getBlockState(checkPos);
            if (checkState.isOf(ModBlocks.ROSE_QUARTZ_PRISM) && checkState.get(RoseQuartzPrismBlock.UPSIDE_DOWN)) {
                receiverY = y;
                break;
            }
        }
        if (receiverY == -1) {
            blockEntity.beamSegments = List.of();
            return;
        }
        if (state.isOf(ModBlocks.ROSE_QUARTZ_PRISM) && state.get(RoseQuartzPrismBlock.UPSIDE_DOWN) && blockEntity.onTicks <= 0) return;
        blockEntity.tempSegments = Lists.newArrayList();
        blockEntity.minY = j - 1;
        BlockPos currentPos = blockEntity.minY < j ? pos : new BlockPos(i, blockEntity.minY + 1, k);
        BeamSegment currentSegment = null;
        while (currentPos.getY() <= receiverY) {
            BlockState blockState = world.getBlockState(currentPos);
            Block block = blockState.getBlock();
            if (world instanceof ServerWorld serverWorld){
                if (serverWorld.getRandom().nextInt(15) == 0)
                    serverWorld.spawnParticles(ModParticles.ROSE_SPARK, currentPos.getX() + 0.5F, currentPos.getY() + 0.5F, currentPos.getZ() + 0.5F, 1, 0.3F, 0.5F, 0.3F, 0);
            }
            Vec3d center = new Vec3d(currentPos.getX(), currentPos.getY(), currentPos.getZ());
            List<LivingEntity> entities = world.getEntitiesByClass(
                    LivingEntity.class,
                    Box.from(center).expand(0),
                    livingEntity -> true
            );
            for (LivingEntity entity : entities) {
                entity.damage(world.getDamageSources().create(Elegance.VAPORISED), 5);
                if (entity instanceof ScreenShaker shaker){
                    shaker.addScreenShake(2, 2);
                }
            }
            if (block instanceof Stainable stainable) {
                int color = stainable.getColor().getEntityColor();
                if (blockEntity.tempSegments.isEmpty() || currentSegment == null) {
                    currentSegment = new BeamSegment(color);
                    blockEntity.tempSegments.add(currentSegment);
                } else if (color == currentSegment.color) {
                    currentSegment.increaseHeight();
                } else {
                    currentSegment = new BeamSegment(ColorHelper.Argb.averageArgb(currentSegment.color, color));
                    blockEntity.tempSegments.add(currentSegment);
                }
            } else {
                if (blockState.getOpacity(world, currentPos) >= 15 && !blockState.isAir()) {
                    blockEntity.tempSegments.clear();
                    break;
                }
                if (currentSegment != null) {
                    currentSegment.increaseHeight();
                }
            }
            currentPos = currentPos.up();
            ++blockEntity.minY;
        }
        blockEntity.beamSegments = blockEntity.tempSegments;
        if (blockEntity.minY >= topY) {
            blockEntity.minY = world.getBottomY() - 1;
        }
        if (blockEntity.poweredByBeacon) {
            for (Direction dir : Direction.values()) {
                BlockPos currentPos1 = pos.offset(dir);
                while (true) {
                    BlockState neighborState = world.getBlockState(currentPos1);
                    if (!neighborState.isOf(ModBlocks.ROSE_QUARTZ_PRISM)) break;

                    BlockEntity be = world.getBlockEntity(currentPos1);
                    if (be instanceof RoseQuartzPrismBlockEntity neighborPrism) {
                        neighborPrism.setOnTicks(2, false);
                    }

                    currentPos1 = currentPos1.offset(dir);
                }
            }
        }


    }

    public List<BeamSegment> getBeamSegments() {
        return this.beamSegments;
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public void setWorld(World world) {
        super.setWorld(world);
        this.minY = world.getBottomY() - 1;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return this.createNbt(registryLookup);
    }

    public static class BeamSegment {
        final int color;
        private int height;

        public BeamSegment(int color) {
            this.color = color;
            this.height = 1;
        }

        protected void increaseHeight() {
            ++this.height;
        }

        public int getColor() {
            return this.color;
        }

        public int getHeight() {
            return this.height;
        }
    }
}
