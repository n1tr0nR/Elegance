package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModParticles {
    public static void init(){

    }

    public static final SimpleParticleType ROSE_SPARK = Registry.register(Registries.PARTICLE_TYPE,
            Elegance.of("rose_spark"),
            FabricParticleTypes.simple(true)
    );
}
