package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ROSE_QUARTZ_BREAK = registerSound("rose_quartz_break");
    public static final SoundEvent ROSE_QUARTZ_PLACE = registerSound("rose_quartz_place");
    public static final SoundEvent ROSE_QUARTZ_RESONATE = registerSound("rose_quartz_resonate");
    public static final SoundEvent ROSE_QUARTZ_ATTACK = registerSound("rose_quartz_attack");
    public static final SoundEvent ROSE_QUARTZ_ATTACK_CRIT = registerSound("rose_quartz_attack_crit");
    public static final SoundEvent ROSE_QUARTZ_ATTACK_SWEEP = registerSound("rose_quartz_attack_sweep");
    public static final SoundEvent ROSE_QUARTZ_ATTACK_KNOCKBACK = registerSound("rose_quartz_attack_knockback");

    public static void init(){}

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Elegance.of(id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
}
