package com.devdyna.cakesticklib.api.aspect.logic;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public interface InstamineHandler {
    abstract boolean isValidInstamine(ItemStack stack, BlockState state);
}
