package com.devdyna.cakesticklib.api.recipe.recipeInput;

import java.util.Arrays;
import java.util.List;

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
            return of(x.fluid(f));
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

    public record dual(FluidStack first, FluidStack second) implements RecipeInput {

        public static dual of(FluidStack a, FluidStack b) {
            return new dual(a, b);
        }

        public static dual of(Fluid a, FluidStack b) {
            return of(x.fluid(a), b);
        }

        public static dual of(Fluid a, Fluid b) {
            return of(a, x.fluid(b));
        }

        public static dual of(FluidStack a, Fluid b) {
            return of(a, x.fluid(b));
        }

        @Override
        public ItemStack getItem(int s) {
            return List.of(first, second).get(s).getFluidType().getBucket(List.of(first, second).get(s));
        }

        @Override
        public int size() {
            return 2;
        }

    }

    public record multiple(FluidStack... fluids) implements RecipeInput {

        public static multiple of(FluidStack... i) {
            return new multiple(i);
        }

        public static multiple of(Fluid... i) {
            return of(Arrays.asList(i).stream().map(x::fluid).toArray(FluidStack[]::new));
        }

        @Override
        public ItemStack getItem(int s) {
            return fluids[s].getFluidType().getBucket(fluids[s]);
        }

        @Override
        public int size() {
            return fluids.length;
        }

    }

    @Deprecated
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
