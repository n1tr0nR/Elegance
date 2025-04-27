package dev.nitron.elegance;

import dev.nitron.elegance.registration.*;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Unique;

public class Elegance implements ModInitializer {
	public static final String MOD_ID = "elegance";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier of(String path){
		return Identifier.of(MOD_ID, path);
	}

	public static final TagKey<Item> ROSE_QUARTZ_WEAPONRY = TagKey.of(Registries.ITEM.getKey(), Elegance.of("rose_quartz_weaponry"));

	public static Text withName(Text text, int color){
		return Text.literal(text.getString()).withColor(color);
	}

	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.init();
		ModBlockEntities.init();
		ModParticles.init();
		ModComponentTypes.init();
	}

	public static final RegistryKey<DamageType> VAPORISED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "vaporised"));
}