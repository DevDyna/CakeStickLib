package com.devdyna.cakesticklib.setup.common;

import com.devdyna.cakesticklib.api.CreativeTabUtils;
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

            event.accept(zItems.SPEED_UPGRADE.get().set(20, 125, 0, 0));
            event.accept(zItems.ENERGY_UPGRADE.get().set(0, -50, 0, 0));
            event.accept(zItems.LUCK_UPGRADE.get().set(0, 150, 15, 0));
            event.accept(zItems.FLUID_UPGRADE.get().set(0, 150, 0, -20));
        }

        if (event.getTabKey() == zCreativeTabs.INGREDIENTS.getKey()) {
            CreativeTabUtils.accept(event,
                    zItems.zSimple,
                    zItems.zPebbles,
                    zItems.zNuggets,
                    zItems.zIngots,
                    zItems.zBlockItem,
                    zItems.zDusts,
                    zItems.zPlates,
                    zItems.zCoils,
                    zItems.zFoils,
                    zItems.zGears,
                    zItems.zMolds,
                    zItems.zChunks,
                    zItems.zDeposits);

        }

    }
}
