package dev.nitron.elegance.mixin;

import dev.nitron.elegance.registration.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BeaconBlockEntityRenderer.class)
public abstract class BeaconBlockEntityRenderMixin {

    @Shadow
    private static void renderBeam(MatrixStack matrices, VertexConsumerProvider vertexConsumers, float tickDelta, long worldTime, int yOffset, int maxY, int color) {
    }

    @Inject( method = "render(Lnet/minecraft/block/entity/BeaconBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
    at = @At("HEAD"),
    cancellable = true)
    private void elegance$render(BeaconBlockEntity beaconBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci){
        if (isSpecialBlockAbove(beaconBlockEntity.getPos(), beaconBlockEntity.getWorld())){
            BlockPos pos = getSpecialBlockAbove(beaconBlockEntity.getPos(), beaconBlockEntity.getWorld());
            long l = beaconBlockEntity.getWorld().getTime();
            List<BeaconBlockEntity.BeamSegment> list = beaconBlockEntity.getBeamSegments();

            int currentY = 0;
            int maxY = pos.getY() - beaconBlockEntity.getPos().getY();

            for (BeaconBlockEntity.BeamSegment segment : list) {
                int segmentHeight = segment.getHeight();
                int segmentEnd = currentY + segmentHeight;

                if (segmentEnd > maxY) {
                    segmentHeight = maxY - currentY;
                    if (segmentHeight <= 0) break;
                }

                renderBeam(matrixStack, vertexConsumerProvider, f, l, currentY, currentY + segmentHeight, segment.getColor());
                currentY += segmentHeight;
            }
            ci.cancel();
        }
    }

    @Unique
    public boolean isSpecialBlockAbove(BlockPos startPos, World world) {
        BlockPos.Mutable pos = new BlockPos.Mutable().set(startPos);
        for (int y = startPos.getY() + 1; y < world.getHeight(); y++) {
            pos.setY(y);
            BlockState state = world.getBlockState(pos);

            if (!state.isAir()) {
                return state.isOf(ModBlocks.ROSE_QUARTZ_PRISM);
            }
        }
        return false;
    }

    @Unique
    public BlockPos getSpecialBlockAbove(BlockPos startPos, World world) {
        BlockPos.Mutable pos = new BlockPos.Mutable().set(startPos);
        for (int y = startPos.getY() + 1; y < world.getHeight(); y++) {
            pos.setY(y);
            BlockState state = world.getBlockState(pos);

            if (!state.isAir()) {
                return pos;
            }
        }
        return new BlockPos(0, 0 , 0);
    }

}
