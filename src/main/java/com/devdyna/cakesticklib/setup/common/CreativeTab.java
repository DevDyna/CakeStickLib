package com.devdyna.cakesticklib.setup.common;

import com.devdyna.cakesticklib.setup.registry.types.zItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CreativeTab {
    @SubscribeEvent
    public static void register(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
            event.accept(zItems.CAKE_STICK.get());

    }
}
