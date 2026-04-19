package com.devdyna.cakesticklib.setup.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationBuilder;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.OxidationStatus;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
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

        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.TOOLS, zItems.REDSTONE_ACID.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.REDSTONE)
                .requires(zItems.PATINA.get())
                .unlockedBy("patina", has(zItems.PATINA.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.TOOLS, zItems.HONEY_SOLUTION.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.HONEYCOMB)
                .requires(Items.DANDELION)
                .unlockedBy("honeycomb", has(Items.HONEYCOMB))
                .save(output);

        CopperOxidationBuilder.of()
                .type(OxidationStatus.OXIDIZING)
                .catalyst(zItemTags.OXIDIZER, registries)
                .unlockedBy()
                .save(output);
        CopperOxidationBuilder.of()
                .type(OxidationStatus.WAXING)
                .catalyst(zItemTags.WAXING, registries)
                .unlockedBy()
                .save(output);
        CopperOxidationBuilder.of()
                .type(OxidationStatus.SCRAPPING)
                .catalyst(ItemTags.AXES, registries)
                .unlockedBy()
                .save(output);
        CopperOxidationBuilder.of()
                .type(OxidationStatus.UNWAXING)
                .catalyst(ItemTags.AXES, registries)
                .unlockedBy()
                .save(output);

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, zItems.CHISEL.get())
                .pattern("  N")
                .pattern(" I ")
                .pattern("S  ")
                .define('N', Items.IRON_NUGGET)
                .define('S', Items.STICK)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("iron", has(Items.IRON_INGOT))
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