package com.devdyna.cakesticklib.api.factories.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface DoubleCropHandler {

    abstract Block asBlock();

    abstract boolean isMaxAge(BlockState state);

    default boolean isTop(BlockGetter level, BlockPos pos) {
        return hasBelow(level, pos) || !hasAbove(level, pos);
    }

    default boolean isBottom(BlockGetter level, BlockPos pos) {
        return hasAbove(level, pos) || !hasBelow(level, pos);
    }

    default boolean isDouble(BlockGetter level, BlockPos pos) {
        return hasAbove(level, pos) || hasBelow(level, pos);
    }

    default BlockState above(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.above());
    }

    default BlockState below(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.below());
    }

    default boolean isFullyMature(BlockGetter level, BlockPos pos) {
        return isDouble(level, pos)
                && isMaxAge(level.getBlockState(pos))
                && isMaxAge(level.getBlockState(pos.relative(
                        hasAbove(level, pos)
                                ? Direction.UP
                                : Direction.DOWN)));
    }

    default boolean hasAbove(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(asBlock());
    }

    default boolean hasBelow(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(asBlock());
    }

}
