package dev.nitron.elegance.datagen;

import dev.nitron.elegance.registration.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COMPACTED_SALT);
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SALT_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SALT_BRICKS)
                .stairs(ModBlocks.SALT_BRICK_STAIRS)
                .slab(ModBlocks.SALT_BRICK_SLAB)
                .wall(ModBlocks.SALT_BRICK_WALL);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ROSE_QUARTZ_BRICKS)
                .stairs(ModBlocks.ROSE_QUARTZ_STAIRS)
                .slab(ModBlocks.ROSE_QUARTZ_SLAB)
                .wall(ModBlocks.ROSE_QUARTZ_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
