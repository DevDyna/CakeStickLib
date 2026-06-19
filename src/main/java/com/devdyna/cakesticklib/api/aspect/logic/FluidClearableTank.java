package com.devdyna.cakesticklib.api.aspect.logic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public interface FluidClearableTank {

    default InteractionResult useItemToClear(BlockState blockState, Level level,
            BlockPos blockPos, Player player, BlockHitResult blockHitResult) {

        // if (!player.isCrouching())
        // return InteractionResult.FAIL;

        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        var cap = level.getCapability(Capabilities.Fluid.BLOCK, blockPos,
                blockHitResult.getDirection());

        if (cap == null)
            return InteractionResult.FAIL;

        if (cap.getAmountAsInt(0) == 0)
            return InteractionResult.FAIL;

        try (var tx = Transaction.openRoot()) {
            cap.extract(0, cap.getResource(0), cap.getAmountAsInt(0), tx);
            tx.commit();
        }
        return InteractionResult.SUCCESS;

    }
}
