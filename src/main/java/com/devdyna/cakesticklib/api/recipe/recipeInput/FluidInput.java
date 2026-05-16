package com.devdyna.cakesticklib.api.recipe.recipeInput;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidInput {

    public record simple(FluidStack fluid) implements RecipeInput {

        public static simple of(FluidStack f) {
            return new simple(f);
        }

        public static simple of(Fluid f) {
            return new simple(x.fluid(f));
        }

        @Override
        public ItemStack getItem(int s) {
            return fluid.getFluidType().getBucket(fluid);
        }

        @Override
        public int size() {
            return 1;
        }

    }

    public record withNumber(FluidStack fluid, double value) implements RecipeInput {

        public static withNumber of(FluidStack f, double v) {
            return new withNumber(f, v);
        }

        public static withNumber of(Fluid f, double v) {
            return new withNumber(x.fluid(f), v);
        }

        public static withNumber of(FluidStack f, int v) {
            return new withNumber(f, (double) v);
        }

        public static withNumber of(Fluid f, int v) {
            return new withNumber(x.fluid(f), (double) v);
        }

        public static withNumber of(FluidStack f, long v) {
            return new withNumber(f, (double) v);
        }

        public static withNumber of(Fluid f, long v) {
            return new withNumber(x.fluid(f), (double) v);
        }

        public static withNumber of(FluidStack f, float v) {
            return new withNumber(f, (double) v);
        }

        public static withNumber of(Fluid f, float v) {
            return new withNumber(x.fluid(f), (double) v);
        }

        @Override
        public ItemStack getItem(int s) {
            return fluid.getFluidType().getBucket(fluid);
        }

        @Override
        public int size() {
            return 1;
        }

    }

}
