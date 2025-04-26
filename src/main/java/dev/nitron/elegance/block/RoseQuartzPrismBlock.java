package dev.nitron.elegance.block;

import com.mojang.serialization.MapCodec;
import dev.nitron.elegance.block_entity.RoseQuartzPrismBlockEntity;
import dev.nitron.elegance.registration.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RoseQuartzPrismBlock extends BlockWithEntity implements Stainable {
    public static MapCodec<RoseQuartzPrismBlock> CODEC = createCodec(RoseQuartzPrismBlock::new);
    public static final BooleanProperty UPSIDE_DOWN = BooleanProperty.of("upside_down");

    public RoseQuartzPrismBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(UPSIDE_DOWN, false));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        boolean upsideDown = side == Direction.DOWN;
        return this.getDefaultState().with(UPSIDE_DOWN, upsideDown);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RoseQuartzPrismBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.ROSE_QUARTZ_PRISM_BLOCK_ENTITY, (RoseQuartzPrismBlockEntity::tick));
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.WHITE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UPSIDE_DOWN);
    }
}
