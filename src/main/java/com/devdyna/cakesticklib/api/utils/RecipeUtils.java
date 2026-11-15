package com.devdyna.cakesticklib.api.utils;

import java.util.List;
import java.util.function.Function;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;

public class RecipeUtils {

    public static <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<?>> getRecipesByType(Level level,
            Function<Recipe<?>, Boolean> check) {
        return level.getServer().getRecipeManager().getRecipes().stream().filter(h -> check.apply(h.value())).toList();
    }
}
