package com.devdyna.cakesticklib.setup.common;

import static com.devdyna.cakesticklib.Main.ID;

import com.devdyna.cakesticklib.setup.registry.types.zItems;

import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ItemToolTipped {
    private static final int OVER_THE_REGISTRY_ID = 1;

    @SubscribeEvent
    public static void main(ItemTooltipEvent event) {
        if (event.getItemStack().is(zItems.CAKE_STICK))
            event.getToolTip().add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".setup.cakestick.tip"));

    }
}
