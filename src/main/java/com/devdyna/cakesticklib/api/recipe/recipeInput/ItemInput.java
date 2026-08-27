package com.devdyna.cakesticklib.api.recipe.recipeInput;

import java.util.Arrays;
import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.ItemLike;

public class ItemInput {

    public record simple(ItemStack item) implements RecipeInput {

        public static simple of(ItemStack i) {
            return new simple(i);
        }

        public static simple of(ItemLike i) {
            return new simple(x.item(i));
        }

        @Override
        public ItemStack getItem(int s) {
            return item;
        }

        @Override
        public int size() {
            return 1;
        }

    }

    public record dual(ItemStack first, ItemStack second) implements RecipeInput {

        public static dual of(ItemStack a, ItemStack b) {
            return new dual(a, b);
        }

        public static dual of(ItemLike a, ItemStack b) {
            return of(x.item(a), b);
        }

        public static dual of(ItemLike a, ItemLike b) {
            return of(a, x.item(b));
        }

        public static dual of(ItemStack a, ItemLike b) {
            return of(a, x.item(b));
        }

        @Override
        public ItemStack getItem(int s) {
            return List.of(first, second).get(s);
        }

        @Override
        public int size() {
            return 2;
        }

    }

    public record multiple(ItemStack... items) implements RecipeInput {

        public static multiple of(ItemStack... i) {
            return new multiple(i);
        }

        public static multiple of(ItemLike... i) {
            return of(Arrays.asList(i).stream().map(x::item).toArray(ItemStack[]::new));
        }

        @Override
        public ItemStack getItem(int s) {
            return items[s];
        }

        @Override
        public int size() {
            return items.length;
        }

    }

    @Deprecated
    public record withNumber(ItemStack item, double value) implements RecipeInput {

        public static withNumber of(ItemStack i, double v) {
            return new withNumber(i, v);
        }

        public static withNumber of(ItemLike i, double v) {
            return new withNumber(x.item(i), v);
        }

        public static withNumber of(ItemStack i, int v) {
            return new withNumber(i, (double) v);
        }

        public static withNumber of(ItemLike i, int v) {
            return new withNumber(x.item(i), (double) v);
        }

        public static withNumber of(ItemStack i, long v) {
            return new withNumber(i, (double) v);
        }

        public static withNumber of(ItemLike i, long v) {
            return new withNumber(x.item(i), (double) v);
        }

        public static withNumber of(ItemStack i, float v) {
            return new withNumber(i, (double) v);
        }

        public static withNumber of(ItemLike i, float v) {
            return new withNumber(x.item(i), (double) v);
        }

        @Override
        public ItemStack getItem(int s) {
            return item;
        }

        @Override
        public int size() {
            return 1;
        }

    }

}
