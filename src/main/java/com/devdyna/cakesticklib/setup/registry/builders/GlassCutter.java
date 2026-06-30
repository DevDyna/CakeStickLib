package com.devdyna.cakesticklib.setup.registry.builders;

import com.devdyna.cakesticklib.api.aspect.logic.InstamineHandler;
import com.devdyna.cakesticklib.setup.registry.LibTags;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class GlassCutter extends Item implements InstamineHandler{

    public GlassCutter(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return isValidInstamine(stack, state);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return isValidInstamine(stack, state) ? 30.0f : super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isValidInstamine(ItemStack stack, BlockState state) {
        return state.is(LibTags.Blocks.MINEABLE_WITH_GLASS_CUTTER);
    }



}