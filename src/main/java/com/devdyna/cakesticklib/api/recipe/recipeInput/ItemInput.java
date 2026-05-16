package com.devdyna.cakesticklib.api.recipe.recipeInput;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class ItemInput {

    public record simple(ItemStack item) implements RecipeInput {

        public static simple of(ItemStack i) {
            return new simple(i);
        }

        public static simple of(Item i) {
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

    public record withNumber(ItemStack item, double value) implements RecipeInput {

        public static withNumber of(ItemStack i, double v) {
            return new withNumber(i, v);
        }

        public static withNumber of(Item i, double v) {
            return new withNumber(x.item(i), v);
        }

        public static withNumber of(ItemStack i, int v) {
            return new withNumber(i, (double) v);
        }

        public static withNumber of(Item i, int v) {
            return new withNumber(x.item(i), (double) v);
        }

        public static withNumber of(ItemStack i, long v) {
            return new withNumber(i, (double) v);
        }

        public static withNumber of(Item i, long v) {
            return new withNumber(x.item(i), (double) v);
        }

        public static withNumber of(ItemStack i, float v) {
            return new withNumber(i, (double) v);
        }

        public static withNumber of(Item i, float v) {
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
