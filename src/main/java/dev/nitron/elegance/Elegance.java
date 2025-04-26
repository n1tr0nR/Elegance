package dev.nitron.elegance;

import dev.nitron.elegance.registration.ModBlockEntities;
import dev.nitron.elegance.registration.ModBlocks;
import dev.nitron.elegance.registration.ModItems;
import dev.nitron.elegance.registration.ModParticles;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Elegance implements ModInitializer {
	public static final String MOD_ID = "elegance";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier of(String path){
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.init();
		ModBlockEntities.init();
		ModParticles.init();
	}

	public static final RegistryKey<DamageType> VAPORISED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "vaporised"));
}