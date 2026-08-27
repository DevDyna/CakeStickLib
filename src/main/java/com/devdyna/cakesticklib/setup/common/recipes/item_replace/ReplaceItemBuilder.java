package com.devdyna.cakesticklib.setup.common.recipes.item_replace;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.BaseRecipeBuilder;
import com.devdyna.cakesticklib.api.recipe.recipeBuilder.ItemAttach;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;

public class ReplaceItemBuilder extends BaseRecipeBuilder
                implements ItemAttach.Output.SimpleOutputItem<ReplaceItemBuilder>,
                ItemAttach.Input.ListedNoItemCount<ReplaceItemBuilder> {

        private final List<Ingredient> items = new ArrayList<>();
        private final List<ItemReplacement> replacements = new ArrayList<>();
        private ItemStackTemplate output;

        public ReplaceItemBuilder(HolderLookup.Provider p) {
                super(p);
                this.criteria = new LinkedHashMap<String, Criterion<?>>();
        }

        public static ReplaceItemBuilder of(HolderLookup.Provider p) {
                return new ReplaceItemBuilder(p);
        }

        public ReplaceItemBuilder unlockedBy() {
                return unlockedBy(x.name(Items.CRAFTING_TABLE),
                                InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE));
        }

        public ReplaceItemBuilder unlockedBy(String name, Criterion<?> criterion) {
                this.criteria.put(name, criterion);
                return this;
        }

        /**
         * Add an item that will be consumed but not replaced from filters
         */
        @Override
        public ReplaceItemBuilder add(Ingredient input) {
                items.add(input);
                return this;
        }

        public ReplaceItemBuilder replace(Ingredient filter, ItemStackTemplate replace) {
                replacements.add(ItemReplacement.of(filter, replace));
                return this;
        }

        public ReplaceItemBuilder replace(Ingredient filter, ItemLike replace) {
                return replace(filter, x.itemTemplate(replace));
        }

        public ReplaceItemBuilder replace(TagKey<Item> filter, ItemStackTemplate replace) {
               return replace(x.itemIngredient(filter, getProvider()), replace);
        }

        public ReplaceItemBuilder replace(TagKey<Item> filter, ItemLike replace) {
                return replace(x.itemIngredient(filter, getProvider()), replace);
        }

        public ReplaceItemBuilder replace(ItemLike filter, ItemLike replace) {
                return replace(x.itemIngredient(filter),replace);
        }

        @Override
        public ReplaceItemBuilder output(ItemStackTemplate output) {
                this.output = output;
                return this;
        }

        @Override
        public Identifier getSuffix(String extra) {
                return x.rl(MODULE_ID, "replace_item/" + x.name(output).toLowerCase() + extra);
        }

        @Override
        public Recipe<?> createRecipe() {
                return new ReplaceItemRecipe(items, replacements, output);
        }

        @Override
        public ReplaceItemBuilder getBuilder() {
                return this;
        }
}