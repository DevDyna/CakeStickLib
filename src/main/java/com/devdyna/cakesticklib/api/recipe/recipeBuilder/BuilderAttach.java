package com.devdyna.cakesticklib.api.recipe.recipeBuilder;

import net.minecraft.core.HolderLookup;

public interface BuilderAttach<BUILDER extends BaseRecipeBuilder> {
    public abstract BUILDER getBuilder();

   abstract HolderLookup.Provider getProvider();

}
