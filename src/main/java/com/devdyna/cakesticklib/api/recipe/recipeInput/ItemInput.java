package com.devdyna.cakesticklib.api.recipe.recipeInput;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class ItemInput {

    public record item(ItemStack i) implements RecipeInput {

        public static item of(ItemStack i) {
            return new item(i);
        }

        public static item of(Item i) {
            return new item(x.item(i));
        }

        @Override
        public ItemStack getItem(int s) {
            return i;
        }

        @Override
        public int size() {
            return 1;
        }

    }

    public record itemValue(ItemStack i, double value) implements RecipeInput {

        public static itemValue of(ItemStack i, double v) {
            return new itemValue(i, v);
        }

        public static itemValue of(Item i, double v) {
            return new itemValue(x.item(i), v);
        }

        public static itemValue of(ItemStack i, int v) {
            return new itemValue(i, (double) v);
        }

        public static itemValue of(Item i, int v) {
            return new itemValue(x.item(i), (double) v);
        }

        public static itemValue of(ItemStack i, long v) {
            return new itemValue(i, (double) v);
        }

        public static itemValue of(Item i, long v) {
            return new itemValue(x.item(i), (double) v);
        }

        public static itemValue of(ItemStack i, float v) {
            return new itemValue(i, (double) v);
        }

        public static itemValue of(Item i, float v) {
            return new itemValue(x.item(i), (double) v);
        }

        @Override
        public ItemStack getItem(int s) {
            return i;
        }

        @Override
        public int size() {
            return 1;
        }

    }

}
