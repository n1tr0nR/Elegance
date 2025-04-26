package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ROSE_QUARTZ_BREAK = registerSound("rose_quartz_break");
    public static final SoundEvent ROSE_QUARTZ_PLACE = registerSound("rose_quartz_place");

    public static void init(){}

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Elegance.of(id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
}
