package com.devdyna.cakesticklib.setup.common;

import com.devdyna.cakesticklib.api.CreativeTabUtils;
import com.devdyna.cakesticklib.setup.registry.*;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CreativeTab {
    @SubscribeEvent
    public static void register(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
            event.accept(LibItems.CAKE_STICK.get());

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(LibItems.CHISEL.get());
            event.accept(LibItems.GLASS_CUTTER.get());
            event.accept(LibItems.REDSTONE_ACID.get());
            event.accept(LibItems.HONEY_SOLUTION.get());
            event.accept(LibItems.HAMMER.get());
            event.accept(LibItems.WRENCH.get());

            event.accept(LibItems.SPEED_UPGRADE.get().set(20, 125, 0, 0));
            event.accept(LibItems.ENERGY_UPGRADE.get().set(0, -50, 0, 0));
            event.accept(LibItems.LUCK_UPGRADE.get().set(0, 150, 15, 0));
            event.accept(LibItems.FLUID_UPGRADE.get().set(0, 150, 0, -20));
        }

        if (event.getTabKey() == LibCreativeTab.INGREDIENTS.getKey()) {
            CreativeTabUtils.accept(event,
                    LibItems.zSimple,
                    LibItems.zPebbles,
                    LibItems.zNuggets,
                    LibItems.zIngots,
                    LibItems.zBlockItem,
                    LibItems.zDusts,
                    LibItems.zPlates,
                    LibItems.zCoils,
                    LibItems.zFoils,
                    LibItems.zGears,
                    LibItems.zMolds,
                    LibItems.zChunks,
                    LibItems.zDeposits);

        }

    }
}
