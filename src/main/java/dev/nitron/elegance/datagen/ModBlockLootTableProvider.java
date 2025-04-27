package dev.nitron.elegance.datagen;

import dev.nitron.elegance.registration.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.ROSE_QUARTZ_PRISM);
        addDrop(ModBlocks.COMPACTED_SALT);
        addDrop(ModBlocks.SALT_BRICKS);
        addDrop(ModBlocks.SALT_BRICK_STAIRS);
        addDrop(ModBlocks.SALT_BRICK_SLAB);
        addDrop(ModBlocks.SALT_BRICK_WALL);
        addDrop(ModBlocks.ROSE_QUARTZ_BRICKS);
        addDrop(ModBlocks.ROSE_QUARTZ_STAIRS);
        addDrop(ModBlocks.ROSE_QUARTZ_SLAB);
        addDrop(ModBlocks.ROSE_QUARTZ_WALL);

    }
}
