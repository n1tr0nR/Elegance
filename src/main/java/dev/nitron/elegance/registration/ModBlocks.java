package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.block.RoseQuartzPrismBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModBlocks {
    public static void init(){

    }

    //Sound Groups
    public static final BlockSoundGroup ROSE_QUARTZ
            = new BlockSoundGroup(1.0F, 1.0F,
            ModSounds.ROSE_QUARTZ_BREAK,
            SoundEvents.BLOCK_VAULT_STEP,
            ModSounds.ROSE_QUARTZ_PLACE,
            SoundEvents.BLOCK_CALCITE_HIT,
            SoundEvents.BLOCK_VAULT_FALL);


    public static final Block ROSE_QUARTZ_PRISM = generate(new RoseQuartzPrismBlock(AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK).requiresTool().sounds(ROSE_QUARTZ)), "rose_quartz_prism");

    public static Block generate(Block block, String name){
        Registry.register(Registries.ITEM, Elegance.of(name), new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, Elegance.of(name), block);
    }

    public static Block generateWithoutItem(Block block, String name){
        return Registry.register(Registries.BLOCK, Elegance.of(name), block);
    }


}
