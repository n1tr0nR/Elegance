package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.block_entity.RoseQuartzPrismBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<RoseQuartzPrismBlockEntity> ROSE_QUARTZ_PRISM_BLOCK_ENTITY;

    public static void init(){
        ROSE_QUARTZ_PRISM_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                Elegance.of("rose_quartz_prism"),
                BlockEntityType.Builder.create((RoseQuartzPrismBlockEntity::new), ModBlocks.ROSE_QUARTZ_PRISM).build());
    }
}
