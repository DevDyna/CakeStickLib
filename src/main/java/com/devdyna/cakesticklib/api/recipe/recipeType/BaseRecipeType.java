package com.devdyna.cakesticklib.api.recipe.recipeType;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeInput;

public abstract class BaseRecipeType<RECIPE_INPUT extends RecipeInput>
        implements Recipe<RECIPE_INPUT> {

    // @Override
    // public RecipeSerializer<? extends BaseRecipeType<RECIPE_INPUT>>
    // getSerializer() {
    // return getRecipe().getSerializer();
    // }

    // @Override
    // public RecipeType<? extends BaseRecipeType<RECIPE_INPUT>> getType() {
    // return getRecipe().getType();
    // }

    // public abstract RecipeRegister<? extends BaseRecipeType<RECIPE_INPUT>>
    // getRecipe();

    public abstract Item getToastIcon();

    // @Override
    // public String group() {
    // return ID + "/quern";
    // }

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