package com.devdyna.cakesticklib.api.aspect.logic;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

public interface SimpleFluidStorage {

    FluidStacksResourceHandler getFluidStorage();

    int getTankCapacity();

    int getTanks();

    default ItemStack getAsBucket(int index) {
        return x.item(getFluidStorage().getResource(index).getFluid().getBucket());
    }

    default FluidStack getAsStack(int index) {
        return getFluidStorage().getResource(index).toStack(getFluidStorage().getAmountAsInt(index));
    }

    default boolean isTankFull(int index) {
        return !getFluidStorage().getResource(index).isEmpty()
                && getFluidStorage()
                        .getCapacityAsInt(index, getFluidStorage().getResource(index)) <= getFluidStorage()
                                .getAmountAsInt(index);
    }

    /**
     * Override must be required on IO machines!
     */
    default boolean isTanksFull() {
        for (int i = 0; i < getTanks(); i++)
            if (!isTankFull(i))
                return false;
        return true;
    }

}
