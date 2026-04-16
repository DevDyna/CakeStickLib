package com.devdyna.cakesticklib.setup.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.registry.types.zItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;

public class DataRecipe extends RecipeProvider {

    protected DataRecipe(Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {

        ShapedRecipeBuilder.shaped(items, RecipeCategory.FOOD, zItems.CAKE_STICK.get())
                .pattern(" C")
                .pattern("S ")
                .define('C', Items.CAKE)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE),
                        has(Items.CAKE))
                .save(output);

    }

    public static final class RecipeRunner extends RecipeProvider.Runner {
        public RecipeRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(
                HolderLookup.Provider lookupProvider,
                RecipeOutput output) {
            return new DataRecipe(lookupProvider, output);
        }

        @Override
        public String getName() {
            return "CakeStickLib";
        }
    }

}