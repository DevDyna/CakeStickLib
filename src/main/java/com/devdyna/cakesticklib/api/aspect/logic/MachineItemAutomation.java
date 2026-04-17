package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public interface MachineItemAutomation extends IndexModifier<ItemResource> {

    ItemStacksResourceHandler getAutomationItemStorage();

    ItemStacksResourceHandler getItemStorage();

    int getMachineSlots();

    List<Integer> getInputSlotIndex();

    List<Integer> getOutputSlotIndex();

    /**
     * return excess items
     */
    default ItemStack insertItem(int slot, ItemStack stack) {

        var inserted = 0;
        if (!getOutputSlotIndex().contains(slot))
            try (Transaction tx = Transaction.openRoot()) {
                inserted = getItemStorage().insert(slot, ItemResource.of(stack), stack.getCount(), tx);
                tx.commit();
            }

        return x.item(stack.getItem(), stack.getCount() - inserted);

    }

    /**
     * return extracted items
     */
    default ItemStack extractItem(int slot, int amount) {

        var resource = getItemStorage().getResource(slot);
        var extracted = 0;

        if (resource.isEmpty())
            return ItemStack.EMPTY;

        if (!getInputSlotIndex().contains(slot))
            try (Transaction tx = Transaction.openRoot()) {

                extracted = getItemStorage()
                        .extract(slot, resource, getItemStorage().getAmountAsInt(slot), tx);
                tx.commit();

            }
        return resource.toStack(extracted);
    }

    default ItemStack getStackInSlot(int slot) {
        if (getItemStorage().size() >= slot)
            return getItemStorage().getResource(slot).toStack().copy();

        return ItemStack.EMPTY;
    }

    default int getSlotLimit(int slot) {
        return getStackInSlot(slot).getMaxStackSize();
    }

    default void setStackInSlot(int slot,ItemStack stack){
        if(!getStackInSlot(slot).isEmpty())
        extractItem(slot, getStackInSlot(slot).count());
        insertItem(slot, stack);
    }

}
