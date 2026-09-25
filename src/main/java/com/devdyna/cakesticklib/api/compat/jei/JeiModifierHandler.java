package com.devdyna.cakesticklib.api.compat.jei;

import java.util.List;

import com.devdyna.cakesticklib.setup.Client;
import com.devdyna.cakesticklib.setup.common.recipes.jei_modifier.JeiModiferRecipe;
import com.devdyna.cakesticklib.setup.registry.LibRecipeTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;

public interface JeiModifierHandler {

    public static final List<RecipeHolder<JeiModiferRecipe>> MODIFIERS = List
            .copyOf(Client.getRecipeCollector().byType(LibRecipeTypes.JEI_MODIFIER.getType()));

    default JeiModiferRecipe getRecipeMatch(Identifier rl) {
        for (var r : MODIFIERS)
            if (r.value().getTarget().equals(rl))
                return r.value();
        return null;
    }

    default JeiModiferRecipe getRecipeMatch(RecipeHolder<?> h) {
        return getRecipeMatch(h.id().identifier());
    }

}
