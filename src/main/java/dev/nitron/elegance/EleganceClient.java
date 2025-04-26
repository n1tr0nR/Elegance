package dev.nitron.elegance;

import dev.nitron.elegance.client.ber.RoseQuartzPrismBlockEntityRenderer;
import dev.nitron.elegance.particles.RoseSparkParticle;
import dev.nitron.elegance.registration.ModBlockEntities;
import dev.nitron.elegance.registration.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class EleganceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ModBlockEntities.ROSE_QUARTZ_PRISM_BLOCK_ENTITY, RoseQuartzPrismBlockEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.ROSE_SPARK, RoseSparkParticle.Factory::new);
    }
}
