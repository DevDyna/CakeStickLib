package com.devdyna.cakesticklib.setup.common.events;

import com.devdyna.cakesticklib.api.aspect.logic.InstamineHandler;
import com.devdyna.cakesticklib.api.utils.LootTableHelper;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

public class InstamineDropEvent {

    @SubscribeEvent
    public static void onBlockBreak(BreakBlockEvent event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var player = event.getPlayer();
        var item = player.getMainHandItem();
        var state = level.getBlockState(pos);

        if (level.isClientSide())
            return;

        if (!(item.getItem() instanceof InstamineHandler mine))
            return;

        if (!mine.isValidInstamine(item, state))
            return;

        event.setCanceled(true);

        if (!player.isCreative() && !player.isSpectator())
            LootTableHelper.getAsSilkTouch(state,
                    (ServerLevel) level, pos,
                    item, player,
                    null).forEach(i -> Block.popResource((Level) level, pos, i));

        level.removeBlock(pos, false);

    }
}
