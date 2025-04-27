package dev.nitron.elegance.util.models;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.registration.ModItems;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModModelLoaders {
    public static final HashMap<Item, ArrayList<WeaponModels>> WEAPONS = new HashMap<>();
    public static final List<Identifier> MODELS = new ArrayList<>();

    public static final WeaponModels QUORITE_SWORD = new WeaponModels(
            Identifier.of(Elegance.MOD_ID, "item/quorite_sword_gui"),
            Identifier.of(Elegance.MOD_ID, "item/quorite_sword")
    );

    public static final WeaponModels QUORITE_TWINSWORD = new WeaponModels(
            Identifier.of(Elegance.MOD_ID, "item/quorite_twinsword_gui"),
            Identifier.of(Elegance.MOD_ID, "item/quorite_twinsword")
    );

    public static final WeaponModels QUORITE_BATTLEAXE = new WeaponModels(
            Identifier.of(Elegance.MOD_ID, "item/quorite_battleaxe_gui"),
            Identifier.of(Elegance.MOD_ID, "item/quorite_battleaxe")
    );

    public static void loadModels(){
        registerWeapon(ModItems.QUORITE_SWORD, QUORITE_SWORD);
        registerWeapon(ModItems.QUORITE_TWINSWORD, QUORITE_TWINSWORD);
        registerWeapon(ModItems.QUORITE_BATTLEAXE, QUORITE_BATTLEAXE);

        ModelLoadingPlugin.register(context -> context.addModels(MODELS));
    }

    private static void registerWeapon(Item item, WeaponModels models){
        ArrayList<WeaponModels> list = new ArrayList<>();
        list.add(models);

        if(WEAPONS.containsKey(item)) list.addAll(WEAPONS.get(item));
        WEAPONS.put(item, list);
        MODELS.add(models.guiModel());
        if(models.guiModel() != models.handModel()) MODELS.add(models.handModel());
    }
}
