package dev.nitron.elegance.datagen;

import dev.nitron.elegance.registration.ModBlocks;
import dev.nitron.elegance.registration.ModItems;
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
        translationBuilder.add("enchantment.elegance.refraction", "Refraction");
        translationBuilder.add("item_group.elegance.elegance", "Elegance");
        translationBuilder.add("subtitles.block.rose_quartz.resonate", "Rose quartz prism resonates");
        translationBuilder.add("trim_material.elegance.rose_quartz", "Rose Quartz Material");
        translationBuilder.add(ModItems.QUORITE_SWORD, "Quorite Sword");
        translationBuilder.add(ModItems.QUORITE_TWINSWORD, "Quorite Twinsword");
        translationBuilder.add(ModItems.QUORITE_BATTLEAXE, "Quorite Battleaxe");
        translationBuilder.add(ModItems.ROSE_QUARTZ, "Rose Quartz");
        translationBuilder.add(ModItems.QUORITE_INGOT, "Quorite Ingot");
        translationBuilder.add(ModItems.QUORITE_NUGGET, "Quorite Nugget");
        translationBuilder.add(ModBlocks.COMPACTED_SALT, "Compacted Salt");
        translationBuilder.add(ModBlocks.SALT_BRICKS, "Salt Bricks");
        translationBuilder.add(ModBlocks.SALT_BRICK_STAIRS, "Salt Brick Stairs");
        translationBuilder.add(ModBlocks.SALT_BRICK_SLAB, "Salt Brick Slab");
        translationBuilder.add(ModBlocks.SALT_BRICK_WALL, "Salt Brick Wall");
        translationBuilder.add(ModBlocks.ROSE_QUARTZ_BRICKS, "Rose Quartz Bricks");
        translationBuilder.add(ModBlocks.ROSE_QUARTZ_STAIRS, "Rose Quartz Brick Stairs");
        translationBuilder.add(ModBlocks.ROSE_QUARTZ_SLAB, "Rose Quartz Brick Slab");
        translationBuilder.add(ModBlocks.ROSE_QUARTZ_WALL, "Rose Quartz Brick Wall");
        translationBuilder.add(ModItems.SALT, "Salt");
    }
}
