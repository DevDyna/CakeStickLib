package com.devdyna.cakesticklib.api.recipe.recipeBuilder;

import java.util.Arrays;
import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ItemAttach {
    public class Input {

        public static interface OptionalConsume<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER consumeItemInput();

        }

        public static interface CatalystItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER consumeCatalyst();

            abstract BUILDER catalyst(SizedIngredient catalyst);

            default BUILDER catalyst(Ingredient catalyst) {
                return catalyst(x.itemSized(catalyst));
            }

            default BUILDER catalyst(ItemStackTemplate... catalyst) {
                return catalyst(x.itemIngredient(catalyst));
            }

            default BUILDER catalyst(DeferredHolder<Item, Item>... catalyst) {
                return catalyst((Item[]) Arrays.asList(catalyst).stream().map(DeferredHolder::get).toArray());
            }

            default BUILDER catalyst(Item... catalyst) {
                return catalyst(x.itemIngredient(catalyst));
            }

            default BUILDER catalyst(ItemLike... catalyst) {
                return catalyst(x.itemIngredient(catalyst));
            }

            default BUILDER catalyst(TagKey<Item> catalyst,HolderLookup.Provider p) {
                return catalyst(x.itemIngredient(catalyst,p));
            }

        }

        public static interface NoItemCount<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER input(Ingredient input);

            default BUILDER input(ItemLike input) {
                return input(x.itemIngredient(input));
            }

            default BUILDER input(DeferredHolder<Item, Item> input) {
                return input(input.get());
            }

            default BUILDER input(TagKey<Item> input,HolderLookup.Provider p) {
                return input(x.itemIngredient(input,p));
            }

        }

        public static interface ListedItemCount<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER add(SizedIngredient input);

            default BUILDER add(Ingredient input) {
                return add(x.itemSized(input));
            }

            default BUILDER add(Ingredient input, int c) {
                return add(x.itemSized(input, c));
            }

            default BUILDER add(Item input, int c) {
                return add(x.itemIngredient(input), c);
            }

            default BUILDER add(Item input) {
                return add(x.itemIngredient(input));
            }

            default BUILDER add(DeferredHolder<Item, Item> input, int c) {
                return add(input.get(), c);
            }

            default BUILDER add(TagKey<Item> input, int c,HolderLookup.Provider p) {
                return add(x.itemIngredient(input,p), c);
            }

            default BUILDER add(ItemLike input, int c) {
                return add(x.itemIngredient(input), c);
            }

            default BUILDER add(DeferredHolder<Item, Item> input) {
                return add(input.get());
            }

            default BUILDER add(TagKey<Item> input,HolderLookup.Provider p) {
                return add(x.itemIngredient(input,p));
            }

            default BUILDER add(ItemLike input) {
                return add(x.itemIngredient(input));
            }
        }

        public interface ItemCounted<BUILDER extends BaseRecipeBuilder> extends NoItemCount<BUILDER> {

            abstract BUILDER input(SizedIngredient input);

            default BUILDER input(Item input) {
                return input(x.itemSized(input));
            }

            default BUILDER input(Ingredient input) {
                return input(x.itemSized(input));
            }

            default BUILDER input(DeferredHolder<Item, Item> input) {
                return input(input.get());
            }

            default BUILDER input(TagKey<Item> input,HolderLookup.Provider p) {
                return input(x.itemSized(input,p));
            }

            default BUILDER input(Item input, int c) {
                return input(x.itemSized(input, c));
            }

            default BUILDER input(DeferredHolder<Item, Item> input, int c) {
                return input(input.get(), c);
            }

            default BUILDER input(TagKey<Item> input, int c,HolderLookup.Provider p) {
                return input(x.itemSized(input, c,p));
            }

        }

        public static interface DoubleItemCounted<BUILDER extends BaseRecipeBuilder>
                extends ItemCounted<BUILDER> {

            abstract BUILDER inputs(SizedIngredient right, SizedIngredient left);

            default BUILDER inputs(Ingredient right, int a, Ingredient left, int b) {
                return inputs(x.itemSized(right, a), x.itemSized(left, b));
            }

            default BUILDER inputs(ItemLike right, int a, ItemLike left, int b) {
                return inputs(x.itemSized(right, a), x.itemSized(left, b));
            }

            default BUILDER inputs(Ingredient right, Ingredient left) {
                return inputs(right, 1, left, 1);
            }

            default BUILDER inputs(ItemLike right, ItemLike left) {
                return inputs(right, 1, left, 1);
            }

            default BUILDER inputs(DeferredHolder<Item, Item> right, int a, DeferredHolder<Item, Item> left, int b) {
                return inputs(right.get(), a, left.get(), b);
            }

            default BUILDER inputs(DeferredHolder<Item, Item> right, DeferredHolder<Item, Item> left) {
                return inputs(right.get(), left.get());
            }

            default BUILDER inputs(TagKey<Item> right, TagKey<Item> left,HolderLookup.Provider p) {
                return inputs(right, 1, left, 1,p);
            }

            default BUILDER inputs(TagKey<Item> right, Item left,HolderLookup.Provider p) {
                return inputs(x.itemIngredient(right,p), x.itemIngredient(left));
            }

            default BUILDER inputs(Item right, TagKey<Item> left,HolderLookup.Provider p) {
                return inputs(x.itemIngredient(right), x.itemIngredient(left,p));
            }

            default BUILDER inputs(TagKey<Item> right, int a, TagKey<Item> left, int b,HolderLookup.Provider p) {
                return inputs(x.itemIngredient(right,p), a, x.itemIngredient(left,p), b);
            }

            default BUILDER inputs(TagKey<Item> right, int a, Item left, int b,HolderLookup.Provider p) {
                return inputs(x.itemIngredient(right,p), a, x.itemIngredient(left), b);
            }

            default BUILDER inputs(Item right, int a, TagKey<Item> left, int b,HolderLookup.Provider p) {
                return inputs(x.itemIngredient(right), a, x.itemIngredient(left,p), b);
            }

        }
    }

    public class Output {

        public static interface SimpleOutputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER output(ItemStackTemplate output);

            default BUILDER output(Item output) {
                return output(x.itemTemplate(output));
            }

            default BUILDER output(ItemLike output) {
                return output(x.itemTemplate(output));
            }

            default BUILDER output(DeferredHolder<Item, Item> output) {
                return output(output.get());
            }

            default BUILDER output(Item output, int count) {
                return output(x.itemTemplate(output, count));
            }

            default BUILDER output(DeferredHolder<Item, Item> output, int count) {
                return output(output.get(), count);
            }
        }

        public static interface SecondaryOutputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

            abstract BUILDER secondary(ItemStackTemplate output, float chance);

            /**
             * default chance of success -> 100%
             */
            default BUILDER secondary(ItemStackTemplate secondary) {
                return secondary(secondary, 1f);
            }

            /**
             * default chance of success -> 100%
             */
            default BUILDER secondary(DeferredHolder<Item, Item> secondary) {
                return secondary(secondary.get());
            }

            /**
             * default chance of success -> 100%
             */
            default BUILDER secondary(Item secondary) {
                return secondary(x.itemTemplate(secondary));
            }

            /**
             * default chance of success -> 100%
             */
            default BUILDER secondary(Item secondary, int count) {
                return secondary(x.itemTemplate(secondary, count));
            }

            /**
             * default chance of success -> 100%
             */
            default BUILDER secondary(DeferredHolder<Item, Item> secondary, int count) {
                return secondary(secondary.get(), count);
            }

            default BUILDER secondary(Item secondary, float chance) {
                return secondary(x.itemTemplate(secondary), chance);
            }

            default BUILDER secondary(DeferredHolder<Item, Item> secondary, float chance) {
                return secondary(secondary.get(), chance);
            }

            default BUILDER secondary(Item secondary, int count, float chance) {
                return secondary(x.itemTemplate(secondary, count), chance);
            }

            default BUILDER secondary(DeferredHolder<Item, Item> secondary, int count, float chance) {
                return secondary(secondary.get(), count, chance);
            }
        }

        public static interface ListedOutputItemStackTemplate<BUILDER extends BaseRecipeBuilder>
                extends BuilderAttach<BUILDER> {

            abstract BUILDER output(List<ItemStackTemplate> output);

            default BUILDER output(ItemStackTemplate output) {
                return output(List.of(output));
            }

            default BUILDER output(ItemStackTemplate... output) {
                return output(List.of(output));
            }

            default BUILDER output(Item output) {
                return output(x.itemTemplate(output));
            }

            default BUILDER output(Item... output) {
                return output(List.of(output).stream().map(i -> x.itemTemplate(i)).toList());
            }

            default BUILDER output(DeferredHolder<Item, Item> output) {
                return output(output.get());
            }

            default BUILDER output(ItemLike output) {
                return output(x.itemTemplate(output));
            }

            default BUILDER output(DeferredHolder<Item, Item>... output) {
                return output(List.of(output).stream().map(DeferredHolder::get).map(ItemStackTemplate::new).toList());
            }

            default BUILDER output(ItemLike... output) {
                return output(List.of(output).stream().map(ItemLike::asItem).map(ItemStackTemplate::new).toList());
            }
        }
    }

}
