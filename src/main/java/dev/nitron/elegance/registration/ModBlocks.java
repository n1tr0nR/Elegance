package dev.nitron.elegance.registration;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.block.RoseQuartzPrismBlock;
import dev.nitron.elegance.item.QuoriteBlockItem;
import net.minecraft.block.*;
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
    public static final Block COMPACTED_SALT = generate(new Block(AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool()), "compacted_salt");
    public static final Block SALT_BRICKS = generate(new Block(AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool()), "salt_bricks");
    public static final Block SALT_BRICK_SLAB = generate(new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool()), "salt_brick_slab");
    public static final Block SALT_BRICK_STAIRS = generate(new StairsBlock(SALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool()), "salt_brick_stairs");
    public static final Block SALT_BRICK_WALL = generate(new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool()), "salt_brick_wall");

    public static final Block ROSE_QUARTZ_BRICKS = generate(new Block(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).requiresTool()), "rose_quartz_bricks");
    public static final Block ROSE_QUARTZ_SLAB = generate(new SlabBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).requiresTool()), "rose_quartz_slab");
    public static final Block ROSE_QUARTZ_STAIRS = generate(new StairsBlock(ROSE_QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).requiresTool()), "rose_quartz_stairs");
    public static final Block ROSE_QUARTZ_WALL = generate(new WallBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).requiresTool()), "rose_quartz_wall");

    public static Block generate(Block block, String name){
        Registry.register(Registries.ITEM, Elegance.of(name), new QuoriteBlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, Elegance.of(name), block);
    }

    public static Block generateWithoutItem(Block block, String name){
        return Registry.register(Registries.BLOCK, Elegance.of(name), block);
    }
}
