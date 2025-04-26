package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.item.QuoriteSwordItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
    public static void init(){

    }

    public static final Item QUORITE_SWORD = generate(new QuoriteSwordItem(new Item.Settings()), "quorite_sword");

    public static Item generate(Item item, String name){
        return Registry.register(Registries.ITEM, Elegance.of(name), item);
    }
}
