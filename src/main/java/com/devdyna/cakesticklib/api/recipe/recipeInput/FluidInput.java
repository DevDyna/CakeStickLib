package com.devdyna.cakesticklib.api.recipe.recipeInput;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidInput {

    public record fluid(FluidStack i) implements RecipeInput {

        public static fluid of(FluidStack i) {
            return new fluid(i);
        }

        public static fluid of(Fluid i) {
            return new fluid(x.fluid(i));
        }

        @Override
        public ItemStack getItem(int s) {
            return i.getFluidType().getBucket(i);
        }

        @Override
        public int size() {
            return 1;
        }

    }

    public record fluidValue(FluidStack i, double value) implements RecipeInput {

        public static fluidValue of(FluidStack i, double v) {
            return new fluidValue(i, v);
        }

        public static fluidValue of(Fluid i, double v) {
            return new fluidValue(x.fluid(i), v);
        }

        public static fluidValue of(FluidStack i, int v) {
            return new fluidValue(i, (double) v);
        }

        public static fluidValue of(Fluid i, int v) {
            return new fluidValue(x.fluid(i), (double) v);
        }

        public static fluidValue of(FluidStack i, long v) {
            return new fluidValue(i, (double) v);
        }

        public static fluidValue of(Fluid i, long v) {
            return new fluidValue(x.fluid(i), (double) v);
        }

        public static fluidValue of(FluidStack i, float v) {
            return new fluidValue(i, (double) v);
        }

        public static fluidValue of(Fluid i, float v) {
            return new fluidValue(x.fluid(i), (double) v);
        }

        @Override
        public ItemStack getItem(int s) {
            return i.getFluidType().getBucket(i);
        }

        @Override
        public int size() {
            return 1;
        }

    }

}
