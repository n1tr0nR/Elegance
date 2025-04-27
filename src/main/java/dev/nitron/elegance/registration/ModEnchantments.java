package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class ModEnchantments {
    public static void init(){}

    public static final RegistryKey<Enchantment> REFRACTION = of("refraction");

    public static RegistryKey<Enchantment> of(String name){
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Elegance.of(name));
    }
}
