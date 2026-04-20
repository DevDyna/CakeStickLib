package com.devdyna.cakesticklib.setup.common.events;

import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.Config;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class EnderEyeReturn {
    @SubscribeEvent
    public static void endFrameEncaseEvent(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var state = level.getBlockState(pos);
        var item = event.getItemStack();
        var hand = event.getHand();

        if (state.is(Blocks.END_PORTAL_FRAME) && state.getValue(EndPortalFrameBlock.HAS_EYE)
                && Config.ENDER_EYE_RETURN_EVENT.get() && item.isEmpty() && hand.equals(InteractionHand.MAIN_HAND)) {
            player.swing(hand);
            player.setItemInHand(hand, x.item(Items.ENDER_EYE));
            level.playSound(player, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 0.6F);
            level.setBlockAndUpdate(pos, state.setValue(EndPortalFrameBlock.HAS_EYE, false));
        }

    }
}