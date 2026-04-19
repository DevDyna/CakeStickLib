package com.devdyna.cakesticklib.setup.common;

import com.devdyna.cakesticklib.setup.registry.zLibrary.*;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CreativeTab {
    @SubscribeEvent
    public static void register(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
            event.accept(zItems.CAKE_STICK.get());
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(zItems.CHISEL.get());
            event.accept(zItems.REDSTONE_ACID.get());
            event.accept(zItems.HONEY_SOLUTION.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
            event.accept(zItems.PATINA.get());

    }
}
