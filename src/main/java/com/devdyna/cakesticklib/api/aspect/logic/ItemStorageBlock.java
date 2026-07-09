package com.devdyna.cakesticklib.api.aspect.logic;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public interface ItemStorageBlock {

    ItemStacksResourceHandler getItemStorage();

    int getSlots();

    default boolean dropOnBreak() {
        return true;
    }

    default ItemStack getStackInSlot(int index) {
        return getItemStorage().getResource(index).toStack(getItemStorage().getAmountAsInt(index));
    }

    default boolean isSlotFull(int index) {
        return !getItemStorage().getResource(index).isEmpty()
                && getItemStorage()
                        .getCapacityAsInt(index, getItemStorage().getResource(index)) <= getItemStorage()
                                .getAmountAsInt(index);
    }

    /**
     * Override must be required on IO machines!
     */
    default boolean isFull() {
        for (int i = 0; i < getSlots(); i++)
            if (!isSlotFull(i))
                return false;
        return true;
    }

}
