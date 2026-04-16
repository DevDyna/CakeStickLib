package com.devdyna.cakesticklib.api.aspect.logic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;

public interface BucketInteraction {

    default InteractionResult executeWhenEmpty(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    default InteractionResult executeWhenNotBucket(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    default InteractionResult bucketAction(ItemStack item, BlockState blockState, Level level,
            BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {

        if (item.isEmpty())
            return executeWhenEmpty(item, blockState, level, blockPos, player, hand, blockHitResult);

        ResourceHandler<FluidResource> bucket = ItemAccess.forStack(item).oneByOne()
                .getCapability(Capabilities.Fluid.ITEM);

        if (bucket == null)
            return executeWhenNotBucket(item, blockState, level, blockPos, player, hand, blockHitResult);

        ResourceHandler<FluidResource> cap = level.getCapability(Capabilities.Fluid.BLOCK, blockPos,
                blockHitResult.getDirection());

        if (cap == null)
            return executeWhenNotBucket(item, blockState, level, blockPos, player, hand, blockHitResult);

        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        if (FluidUtil.interactWithFluidHandler(player, hand, level, blockPos, blockHitResult.getDirection()))
            return InteractionResult.SUCCESS;

        return InteractionResult.PASS;
    }

    // default boolean insertFilter(FluidStack simulated) {
    //     return true;
    // }
}
