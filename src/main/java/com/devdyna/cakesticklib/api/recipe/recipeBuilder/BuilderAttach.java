package com.devdyna.cakesticklib.api.recipe.recipeBuilder;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.ItemLike;

public interface BuilderAttach<BUILDER extends BaseRecipeBuilder> {
    public abstract BUILDER getBuilder();

    abstract HolderLookup.Provider getProvider();

    abstract BUILDER unlockedBy(String name, Criterion<?> criterion);

    default BUILDER unlockedBy(ItemLike name, ItemLike... has) {
        return unlockedBy(x.name(name), has);
    }

    default BUILDER unlockedBy(ItemLike item) {
        return unlockedBy(item, item);
    }

    default BUILDER unlockedBy(String name, ItemLike... has) {
        return unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(has));
    }

}
