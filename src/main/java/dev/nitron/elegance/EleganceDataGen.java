package dev.nitron.elegance;

import dev.nitron.elegance.datagen.ModBlockLangProvider;
import dev.nitron.elegance.datagen.ModBlockLootTableProvider;
import dev.nitron.elegance.datagen.ModBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EleganceDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModBlockLangProvider::new);
    }
}
