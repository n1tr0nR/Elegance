package dev.nitron.elegance.datagen;

import dev.nitron.elegance.registration.ModBlocks;
import dev.nitron.elegance.registration.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipieProvider extends FabricRecipeProvider {
    public ModRecipieProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        createStairsRecipe(ModBlocks.SALT_BRICKS, Ingredient.ofItems(ModBlocks.SALT_BRICK_STAIRS));
        createSlabRecipe(RecipeCategory.DECORATIONS, ModBlocks.SALT_BRICKS, Ingredient.ofItems(ModBlocks.SALT_BRICK_STAIRS));
        offerWallRecipe(recipeExporter,
                RecipeCategory.DECORATIONS,
                ModBlocks.SALT_BRICK_WALL,
                ModBlocks.SALT_BRICKS);
    }
}
