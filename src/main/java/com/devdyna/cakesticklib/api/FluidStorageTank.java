package com.devdyna.cakesticklib.api;

import net.minecraft.core.NonNullList;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

public class FluidStorageTank extends FluidStacksResourceHandler {

    public FluidStorageTank(BlockEntity be, int size, int capacity) {
        super(size, capacity);
        this.be = be;
    }

    public FluidStorageTank(BlockEntity be, NonNullList<FluidStack> stacks, int capacity) {
        super(stacks, capacity);
        this.be = be;
    }

    private final BlockEntity be;

    @Override
    protected void onContentsChanged(int index, FluidStack previousContents) {
        super.onContentsChanged(index, previousContents);

        if (be == null || be.getLevel() == null)
            return;

        be.setChanged();

        if (!be.getLevel().isClientSide()) {
            be.getLevel().sendBlockUpdated(
                    be.getBlockPos(),
                    be.getBlockState(),
                    be.getBlockState(),
                    3);
        }
    }

}
