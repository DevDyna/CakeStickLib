package com.devdyna.cakesticklib.setup.common.events;

import java.util.*;

import com.devdyna.cakesticklib.api.factories.plants.*;
import com.devdyna.cakesticklib.setup.Config;

import net.minecraft.world.InteractionHand;
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

        if (!Config.HARVESTABLE_ACTION.get())
            return;

        List<ItemStack> check = VanillaPlants.checkReplant(level, pos,player,hand);

        if (check != null && hand.equals(InteractionHand.MAIN_HAND)) 
            check.forEach(i -> player.addItem(i));
        

      
    }
}