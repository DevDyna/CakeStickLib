package com.devdyna.cakesticklib.setup.common;

import java.util.function.Function;

import com.devdyna.cakesticklib.api.CreativeTabUtils;
import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier.UseType;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.*;

import net.minecraft.core.Direction;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

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

           event.accept(upgrade(LibItems.SPEED_UPGRADE, b -> b.speed(20).energy(125).create()));
            event.accept(upgrade(LibItems.ENERGY_UPGRADE, b -> b.energy(-50).create()));
            event.accept(upgrade(LibItems.LUCK_UPGRADE, b -> b.luck(15).energy(150).create()));
            event.accept(upgrade(LibItems.FLUID_UPGRADE, b -> b.fluid(-20).energy(150).create()));
            event.accept(upgrade(LibItems.EJECT_UPGRADE, b -> b.eject(Direction.DOWN, UseType.ITEM).create()));
            event.accept(upgrade(LibItems.EJECT_UPGRADE, b -> b.eject(Direction.DOWN, UseType.FLUID).create()));

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

     private static ItemStack upgrade(DeferredHolder<Item, Item> item,
            Function<UpgradeComponents.Builder, UpgradeComponents> upgrades) {
        var stack = x.item(item.get());
        stack.set(LibComponents.UPGRADE_COMPONENTS, upgrades.apply(UpgradeComponents.Builder.of()));
        return stack;
    }
}
