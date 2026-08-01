package com.devdyna.cakesticklib.api.aspect.logic;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public interface ItemStorageBlock {

    ItemStacksResourceHandler getItemStorage();

    int getSlots();

    BlockState getBlockState();

    default boolean dropOnBreak() {
        return !(getBlockState().getBlock() instanceof BlockItemKeeper);
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
    default boolean isSlotsFull() {
        for (int i = 0; i < getSlots(); i++)
            if (!isSlotFull(i))
                return false;
        return true;
    }

    default boolean isSlotsEmpty(int start, int end) {
        for (int i = start; i < end; i++)
            if (!getItemStorage().getResource(i).isEmpty())
                return false;
        return true;
    }

    default boolean isSlotsEmpty() {
        return isSlotsEmpty(0, getSlots());
    }

}
