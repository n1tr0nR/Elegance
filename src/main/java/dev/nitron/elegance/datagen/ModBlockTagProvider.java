package dev.nitron.elegance.datagen;

import dev.nitron.elegance.registration.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider<Block> {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.COMPACTED_SALT)
                .add(ModBlocks.SALT_BRICKS)
                .add(ModBlocks.SALT_BRICK_STAIRS)
                .add(ModBlocks.SALT_BRICK_SLAB)
                .add(ModBlocks.SALT_BRICK_WALL)
                .add(ModBlocks.ROSE_QUARTZ_PRISM)
                .add(ModBlocks.ROSE_QUARTZ_BRICKS)
                .add(ModBlocks.ROSE_QUARTZ_STAIRS)
                .add(ModBlocks.ROSE_QUARTZ_SLAB)
                .add(ModBlocks.ROSE_QUARTZ_WALL);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.COMPACTED_SALT)
                .add(ModBlocks.SALT_BRICKS)
                .add(ModBlocks.SALT_BRICK_STAIRS)
                .add(ModBlocks.SALT_BRICK_SLAB)
                .add(ModBlocks.SALT_BRICK_WALL)
                .add(ModBlocks.ROSE_QUARTZ_PRISM)
                .add(ModBlocks.ROSE_QUARTZ_BRICKS)
                .add(ModBlocks.ROSE_QUARTZ_STAIRS)
                .add(ModBlocks.ROSE_QUARTZ_SLAB)
                .add(ModBlocks.ROSE_QUARTZ_WALL);
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.SALT_BRICK_STAIRS)
                .add(ModBlocks.ROSE_QUARTZ_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.ROSE_QUARTZ_SLAB)
                .add(ModBlocks.SALT_BRICK_SLAB);
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.ROSE_QUARTZ_WALL)
                .add(ModBlocks.SALT_BRICK_WALL);
    }
}
