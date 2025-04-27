package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.util.models.WeaponModels;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponentTypes {
    public static final ComponentType<WeaponModels> WEAPON_MODELS_COMPONENT = ComponentType.<WeaponModels>builder().codec(WeaponModels.CODEC).packetCodec(WeaponModels.PACKET_CODEC).build();

    public static void init(){
        register("weapon_models", WEAPON_MODELS_COMPONENT);
    }

    public static void register(String name, ComponentType<?> componentType){
        Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Elegance.MOD_ID, name), componentType);
    }
}
