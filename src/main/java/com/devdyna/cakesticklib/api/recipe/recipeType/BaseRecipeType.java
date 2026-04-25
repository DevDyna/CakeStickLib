package com.devdyna.cakesticklib.api.recipe.recipeType;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeInput;

public abstract class BaseRecipeType<RECIPE_INPUT extends RecipeInput>
        implements Recipe<RECIPE_INPUT> {

    public abstract Item getToastIcon();

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(x.itemIngredient(getToastIcon()));
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}