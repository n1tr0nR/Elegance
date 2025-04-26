package dev.nitron.elegance.datagen;

import dev.nitron.elegance.registration.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLangProvider extends FabricLanguageProvider {
    public ModBlockLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModBlocks.ROSE_QUARTZ_PRISM, "Rose Quartz Prism");
        translationBuilder.add("death.attack.vaporised", "%1$s was vaporised");
        translationBuilder.add("death.attack.vaporised.player", "%1$s was vaporised whilst fighting %2$s");
    }
}
