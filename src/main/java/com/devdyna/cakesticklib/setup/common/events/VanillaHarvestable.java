package com.devdyna.cakesticklib.setup.common.events;

import java.util.*;

import com.devdyna.cakesticklib.api.factories.plants.*;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class VanillaHarvestable {
    @SubscribeEvent
    public static void harvestVanillaCrops(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var hand = event.getHand();
        var item = event.getItemStack();

        if (!Harvestable.getConfig())
            return;

        if (player.isSpectator())
            return;


            //prevent bonemeal usage conflict
        if (!(item.getItem() instanceof BoneMealItem) && hand.equals(InteractionHand.MAIN_HAND)) {
            List<ItemStack> check = VanillaPlants.checkReplant(level, pos, player, hand);

            if (check != null) {
                check.forEach(i -> player.addItem(i));
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }

    }
}