package dev.nitron.elegance;

import dev.nitron.elegance.client.ber.RoseQuartzPrismBlockEntityRenderer;
import dev.nitron.elegance.particles.RoseSparkParticle;
import dev.nitron.elegance.registration.ModBlockEntities;
import dev.nitron.elegance.registration.ModItems;
import dev.nitron.elegance.registration.ModParticles;
import dev.nitron.elegance.util.EnchantmentHelper;
import dev.nitron.elegance.util.models.ModModelLoaders;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.ItemConvertible;

public class EleganceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ModBlockEntities.ROSE_QUARTZ_PRISM_BLOCK_ENTITY, RoseQuartzPrismBlockEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.ROSE_SPARK, RoseSparkParticle.Factory::new);

        ModModelLoaders.loadModels();
    }
}
