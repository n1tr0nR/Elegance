package dev.nitron.elegance.client.ber;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.block_entity.RoseQuartzPrismBlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class RoseQuartzPrismBlockEntityRenderer implements BlockEntityRenderer<RoseQuartzPrismBlockEntity> {
    public static final Identifier BEAM_TEXTURE = Identifier.of(Elegance.MOD_ID, "textures/entity/beam.png");

    public RoseQuartzPrismBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    public void render(RoseQuartzPrismBlockEntity beaconBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        long l = beaconBlockEntity.getWorld().getTime();
        List<RoseQuartzPrismBlockEntity.BeamSegment> list = beaconBlockEntity.getBeamSegments();
        int k = 0;

        for (RoseQuartzPrismBlockEntity.BeamSegment segment : list) {
            RoseQuartzPrismBlockEntity.BeamSegment beamSegment = (RoseQuartzPrismBlockEntity.BeamSegment) segment;
            renderBeam(matrixStack, vertexConsumerProvider, f, l, k, beamSegment.getHeight(), beamSegment.getColor());
            k += beamSegment.getHeight();
        }

    }

    private static void renderBeam(MatrixStack matrices, VertexConsumerProvider vertexConsumers, float tickDelta, long worldTime, int yOffset, int maxY, int color) {
        renderBeam(matrices, vertexConsumers, BEAM_TEXTURE, tickDelta, 1.0F, worldTime, yOffset, maxY, color, 0.2F, 0.25F);
    }

    public static void renderBeam(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier textureId, float tickDelta, float heightScale, long worldTime, int yOffset, int maxY, int color, float innerRadius, float outerRadius) {
        int i = yOffset + maxY;
        matrices.push();
        matrices.translate((double)0.5F, (double)0.0F, (double)0.5F);
        float f = (float)Math.floorMod(worldTime, 40) + tickDelta;
        float g = maxY < 0 ? f : -f;
        float h = MathHelper.fractionalPart(g * 0.2F - (float)MathHelper.floor(g * 0.1F));
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f * 2.25F - 45.0F));
        float j = 0.0F;
        float m = 0.0F;
        float n = -innerRadius;
        float o = 0.0F;
        float p = 0.0F;
        float q = -innerRadius;
        float r = 0.0F;
        float s = 1.0F;
        float t = -1.0F + h;
        float u = (float)maxY * heightScale * (0.5F / innerRadius) + t;
        renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(textureId, false)), color, yOffset, i, 0.0F, innerRadius, innerRadius, 0.0F, n, 0.0F, 0.0F, q, 0.0F, 1.0F, u, t);
        matrices.pop();
        j = -outerRadius;
        float k = -outerRadius;
        m = -outerRadius;
        n = -outerRadius;
        r = 0.0F;
        s = 1.0F;
        t = -1.0F + h;
        u = (float)maxY * heightScale + t;
        renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(textureId, true)), ColorHelper.Argb.withAlpha(32, color), yOffset, i, j, k, outerRadius, m, n, outerRadius, outerRadius, outerRadius, 0.0F, 1.0F, u, t);
        matrices.pop();
    }

    private static void renderBeamLayer(MatrixStack matrices, VertexConsumer vertices, int color, int yOffset, int height, float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4, float u1, float u2, float v1, float v2) {
        MatrixStack.Entry entry = matrices.peek();
        renderBeamFace(entry, vertices, color, yOffset, height, x1, z1, x2, z2, u1, u2, v1, v2);
        renderBeamFace(entry, vertices, color, yOffset, height, x4, z4, x3, z3, u1, u2, v1, v2);
        renderBeamFace(entry, vertices, color, yOffset, height, x2, z2, x4, z4, u1, u2, v1, v2);
        renderBeamFace(entry, vertices, color, yOffset, height, x3, z3, x1, z1, u1, u2, v1, v2);
    }

    private static void renderBeamFace(MatrixStack.Entry matrix, VertexConsumer vertices, int color, int yOffset, int height, float x1, float z1, float x2, float z2, float u1, float u2, float v1, float v2) {
        renderBeamVertex(matrix, vertices, color, height, x1, z1, u2, v1);
        renderBeamVertex(matrix, vertices, color, yOffset, x1, z1, u2, v2);
        renderBeamVertex(matrix, vertices, color, yOffset, x2, z2, u1, v2);
        renderBeamVertex(matrix, vertices, color, height, x2, z2, u1, v1);
    }

    private static void renderBeamVertex(MatrixStack.Entry matrix, VertexConsumer vertices, int color, int y, float x, float z, float u, float v) {
        vertices.vertex(matrix, x, (float)y, z).color(color).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(matrix, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public boolean rendersOutsideBoundingBox(RoseQuartzPrismBlockEntity beaconBlockEntity) {
        return true;
    }

    public int getRenderDistance() {
        return 256;
    }

    @Override
    public boolean isInRenderDistance(RoseQuartzPrismBlockEntity beaconBlockEntity, Vec3d vec3d) {
        return Vec3d.ofCenter(beaconBlockEntity.getPos()).multiply((double)1.0F, (double)0.0F, (double)1.0F).isInRange(vec3d.multiply((double)1.0F, (double)0.0F, (double)1.0F), (double)this.getRenderDistance());
    }
}
