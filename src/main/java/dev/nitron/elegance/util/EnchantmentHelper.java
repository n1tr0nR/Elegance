package dev.nitron.elegance.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.ItemStack;

public class EnchantmentHelper {
    public static boolean hasEnchantment(ItemStack stack, String enchantKey){
        final var enchantments = stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT).getEnchantmentEntries();
        for (final var entry : enchantments){
            String enchant = entry.getKey().getIdAsString();
            if (enchant.contains(enchantKey)){
                return true;
            }
        }
        return false;
    }
}
