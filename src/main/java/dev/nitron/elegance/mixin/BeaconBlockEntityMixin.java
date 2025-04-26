package dev.nitron.elegance.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.nitron.elegance.block_entity.RoseQuartzPrismBlockEntity;
import dev.nitron.elegance.registration.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"
            )
    )
    private static BlockState elegance$overrideBlockStateIfAbovePrism(
            World world, BlockPos pos, Operation<BlockState> original
    ) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos.getX(), pos.getY(), pos.getZ());
        for (int y = pos.getY() - 1; y >= world.getBottomY(); y--) {
            mutable.setY(y);
            BlockState state = world.getBlockState(mutable);
            if (!state.isAir()) {
                BlockEntity entity = world.getBlockEntity(mutable);
                if (state.isOf(ModBlocks.ROSE_QUARTZ_PRISM) && entity instanceof RoseQuartzPrismBlockEntity blockEntity) {
                    blockEntity.setOnTicks(2, true);
                    return Blocks.AIR.getDefaultState();
                }
            }
        }

        return original.call(world, pos);
    }

}