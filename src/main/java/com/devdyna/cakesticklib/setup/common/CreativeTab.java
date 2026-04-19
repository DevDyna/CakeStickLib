package com.devdyna.cakesticklib.setup.common;

import com.devdyna.cakesticklib.setup.registry.types.zLibItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CreativeTab {
    @SubscribeEvent
    public static void register(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
            event.accept(zLibItems.CAKE_STICK.get());
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(zLibItems.CHISEL.get());
            event.accept(zLibItems.REDSTONE_ACID.get());
            event.accept(zLibItems.HONEY_SOLUTION.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
            event.accept(zLibItems.PATINA.get());

    }
}
