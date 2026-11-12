package com.devdyna.cakesticklib.api.recipe.recipeInput.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;

public interface TypeInputHandler extends RecipeInput {

    abstract int size();

    default List<Integer> getItemIndexes() {
        return getIndexesBySize();
    }

    default List<Integer> getFluidIndexes() {
        return List.of();
    }

    default List<Integer> getIndexesBySize() {
        return getIndexesBySize(size());
    }

    default List<Integer> getIndexesBySize(int size) {
        var list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++)
            list.add(i);
        return list;
    }

    default ItemStack getItemStack(int index) {
        return getItemIndexes().contains(index) ? getItem(index) : ItemStack.EMPTY;
    }

    default FluidStack getFluidStack(int index) {
        return getFluidIndexes().contains(index) ? FluidUtil.getFirstStackContained(getItem(index)) : FluidStack.EMPTY;
    }

}
