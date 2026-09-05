package com.devdyna.cakesticklib.api.recipe.recipeBuilder;

import java.util.Arrays;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface BuilderAttach<BUILDER extends BaseRecipeBuilder> {
    public abstract BUILDER getBuilder();

    abstract HolderLookup.Provider getProvider();

    abstract BUILDER unlockedBy(String name, Criterion<?> criterion);

    default BUILDER unlockedBy(ItemLike name, ItemLike... has) {
        return unlockedBy(x.name(name), has);
    }
    default BUILDER unlockedBy(DeferredHolder<Item, Item> name, DeferredHolder<Item, Item>... has) {
        return unlockedBy(name.get(), Arrays.asList(has).stream().map(DeferredHolder::get).toArray(ItemLike[]::new));
    }

    default BUILDER unlockedBy(ItemLike item) {
        return unlockedBy(item, item);
    }

    default BUILDER unlockedBy(DeferredHolder<Item, Item> item) {
        return unlockedBy(item.get());
    }

    default BUILDER unlockedBy(TagKey<Item> tag, HolderGetter<Item> items) {
        return unlockedBy(x.name(tag),
                InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, tag)));
    }

    default BUILDER unlockedBy(String name, ItemLike... has) {
        return unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(has));
    }

}
