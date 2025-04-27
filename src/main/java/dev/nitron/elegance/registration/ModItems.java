package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    public static final Item QUORITE_SWORD = generate(new QuoriteSwordItem(new Item.Settings()), "quorite_sword");
    public static final Item QUORITE_TWINSWORD = generate(new QuoriteTwinswordItem(new Item.Settings()), "quorite_twinsword");
    public static final Item QUORITE_BATTLEAXE = generate(new QuoriteBattleaxeItem(new Item.Settings()), "quorite_battleaxe");
    public static final Item ROSE_QUARTZ = generate(new QuoriteItem(new Item.Settings()), "rose_quartz");
    public static final Item QUORITE_INGOT = generate(new QuoriteItem(new Item.Settings()), "quorite_ingot");
    public static final Item QUORITE_NUGGET = generate(new QuoriteItem(new Item.Settings()), "quorite_nugget");
    public static final Item SALT = generate(new QuoriteItem(new Item.Settings()), "salt");

    public static Item generate(Item item, String name) {
        return Registry.register(Registries.ITEM, Elegance.of(name), item);
    }

    public static final RegistryKey<ItemGroup> ELEGANCE_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Elegance.of("elegance_group"));
    public static final ItemGroup ELEGANCE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(QUORITE_SWORD))
            .displayName(Text.translatable("item_group.elegance.elegance").withColor(0xff767e))
            .build();

    public static void init(){
        Registry.register(Registries.ITEM_GROUP, ELEGANCE_GROUP_KEY, ELEGANCE_GROUP);
        ItemGroupEvents.modifyEntriesEvent(ELEGANCE_GROUP_KEY).register(entries -> {
            entries.add(QUORITE_SWORD);
            entries.getContext().lookup()
                    .getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOptional(ModEnchantments.REFRACTION)
                    .ifPresent(enchantment -> {
                        Enchantment enchant = enchantment.value();
                        RegistryEntry<Enchantment> entry = RegistryEntry.of(enchant);
                        ItemStack stack = new ItemStack(QUORITE_SWORD);
                        stack.addEnchantment(entry, 1);
                        entries.add(stack);
            });
            entries.add(QUORITE_TWINSWORD);
            entries.add(QUORITE_BATTLEAXE);
            entries.add(ROSE_QUARTZ);
            entries.add(QUORITE_INGOT);
            entries.add(QUORITE_NUGGET);
            entries.add(SALT);

            entries.add(ModBlocks.ROSE_QUARTZ_PRISM);
            entries.add(ModBlocks.COMPACTED_SALT);
            entries.add(ModBlocks.SALT_BRICKS);
            entries.add(ModBlocks.SALT_BRICK_STAIRS);
            entries.add(ModBlocks.SALT_BRICK_SLAB);
            entries.add(ModBlocks.SALT_BRICK_WALL);
            entries.add(ModBlocks.ROSE_QUARTZ_BRICKS);
            entries.add(ModBlocks.ROSE_QUARTZ_STAIRS);
            entries.add(ModBlocks.ROSE_QUARTZ_SLAB);
            entries.add(ModBlocks.ROSE_QUARTZ_WALL);
        });
    }
}
