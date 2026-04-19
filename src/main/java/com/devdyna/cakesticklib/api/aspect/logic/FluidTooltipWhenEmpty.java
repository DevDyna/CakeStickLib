package com.devdyna.cakesticklib.api.aspect.logic;


import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

/**
 * Warning :
 * <br/><br/> This only support atm SINGLE FLUID TANKS!
 */
public interface FluidTooltipWhenEmpty {

    abstract FluidStacksResourceHandler getFluidTank(BlockEntity be, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult);

    default boolean showWhen(BlockEntity be) {
        return true;
    }

    default InteractionResult sendFluidTooltip(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        var tank = getFluidTank(level.getBlockEntity(pos), state, level, pos, player, hand, hitResult);
        if (showWhen(level.getBlockEntity(pos)) && tank != null) {
            var fluid = tank.getResource(0);
            if (fluid.isEmpty())
                player.sendOverlayMessage(Component.translatable(MODULE_ID + ".tank_interact.empty"));
            else
                player.sendOverlayMessage(
                        Component.literal(
                                fluid.getFluidType().getDescription().getString() + " : " + tank.getAmountAsInt(0) + "mb"));

            return InteractionResult.SUCCESS;
        }
        return onTooltipFail(stack, state, level, pos, player, hand, hitResult);
    }

    default InteractionResult onTooltipFail(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }
}