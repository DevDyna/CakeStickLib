package com.devdyna.cakesticklib.api.recipe.recipeInput;

import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public class MixedInput {

    public record ItemFluidInput(FluidStack fluid, ItemStack item) implements RecipeInput {

        public static ItemFluidInput of(FluidStack a, ItemStack b) {
            return new ItemFluidInput(a, b);
        }

        public static ItemFluidInput of(Fluid a, ItemStack b) {
            return of(x.fluid(a), b);
        }

        public static ItemFluidInput of(Fluid a, ItemLike b) {
            return of(a, x.item(b));
        }

        public static ItemFluidInput of(FluidStack a, ItemLike b) {
            return of(a, x.item(b));
        }

        @Override
        public ItemStack getItem(int s) {
            return List.of(fluid.getFluidType().getBucket(fluid), item).get(s);
        }

        @Override
        public int size() {
            return 2;
        }

    }
}
