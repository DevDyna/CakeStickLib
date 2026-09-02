package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.List;
import java.util.stream.IntStream;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

public class ResourceRestricted {

    public static interface Item extends ItemStorageBlock {

        ItemStacksResourceHandler getAutomationItemStorage();

        ItemStacksResourceHandler getItemStorage();

        int getMachineSlots();

        List<Integer> getInputSlotIndex();

        List<Integer> getOutputSlotIndex();

        BlockState getBlockState();

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

        default int getSlotLimit(int slot) {
            return getStackInSlot(slot).getMaxStackSize();
        }

        default void setStackInSlot(int slot, ItemStack stack) {
            if (!getStackInSlot(slot).isEmpty())
                extractItem(slot, getStackInSlot(slot).count());
            insertItem(slot, stack);
        }

        default boolean dropOnBreak(Player player) {
            return dropOnBreak();
        }

        @Deprecated
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
            for (int i = 0; i < getMachineSlots(); i++)
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

    }

    public static interface Fluid extends SimpleFluidStorage {

        /**
         * Deny the extraction on input tanks
         */
        default boolean denyExtractInputs() {
            return false;
        }

        default FluidStacksResourceHandler getAutomationFluidStorage() {
            return new FluidStacksResourceHandler(getTanks(), getTankCapacity()) {

                @Override
                public FluidResource getResource(int i) {
                    return getFluidStorage().getResource(i);
                }

                @Override
                public long getAmountAsLong(int i) {
                    return getFluidStorage().getAmountAsLong(i);
                }

                @Override
                public long getCapacityAsLong(int i, FluidResource r) {
                    if (getInputTankIndex().contains(i))
                        return getFluidStorage().getCapacityAsLong(i, r);

                    if (getOutputTankIndex().contains(i))
                        return getFluidStorage().getCapacityAsLong(i, r);

                    return 0;
                }

                @Override
                public boolean isValid(int i, FluidResource r) {
                    return getInputTankIndex().contains(i) && getFluidStorage().isValid(i, r);
                }

                @Override
                public int extract(FluidResource r, int a, TransactionContext t) {
                    if (r.isEmpty() || a <= 0)
                        return 0;

                    if (!denyExtractInputs())
                        return getFluidStorage().extract(r, a, t);

                    int extracted = 0;

                    for (int i : getOutputTankIndex()) {
                        extracted += getFluidStorage().extract(i, r, a - extracted, t);

                        if (extracted >= a)
                            break;
                    }

                    return extracted;
                }

                @Override
                public int insert(FluidResource r, int a, TransactionContext t) {

                    if (r.isEmpty() || a <= 0)
                        return 0;

                    int inserted = 0;

                    for (int i : getInputTankIndex()) {
                        inserted += getFluidStorage().insert(i, r, a - inserted, t);

                        if (inserted >= a)
                            break;
                    }

                    return inserted;
                }

                @Override
                public int extract(int i, FluidResource r, int a, TransactionContext t) {
                    if (!denyExtractInputs())
                        return getFluidStorage().extract(i, r, a, t);

                    if (getOutputTankIndex().contains(i))
                        return getFluidStorage().extract(i, r, a, t);
                    return 0;
                }

                @Override
                public int insert(int i, FluidResource r, int a, TransactionContext t) {
                    if (getInputTankIndex().contains(i))
                        return getFluidStorage().insert(i, r, a, t);
                    return 0;
                }

            };
        }

        FluidStacksResourceHandler getFluidStorage();

        int getTankCapacity();

        int getTanks();

        default ItemStack getAsBucket(int index) {
            return x.item(getFluidStorage().getResource(index).getFluid().getBucket());
        }

        default FluidStack getAsStack(int index) {
            return getFluidStorage().getResource(index).toStack(getFluidStorage().getAmountAsInt(index));
        }

        default List<Integer> getInputTankIndex() {
            return IntStream.rangeClosed(0, getTanks() - 1).boxed().toList();
        }

        default List<Integer> getOutputTankIndex() {
            return IntStream.rangeClosed(0, getTanks() - 1).boxed().toList();
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

}
