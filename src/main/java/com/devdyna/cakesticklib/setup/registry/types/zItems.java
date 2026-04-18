package com.devdyna.cakesticklib.setup.registry.types;

import com.devdyna.cakesticklib.Main;
import com.devdyna.cakesticklib.setup.registry.builders.*;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {
        public static void register(IEventBus bus) {
                zItem.register(bus);
        }

        public static final DeferredRegister.Items zItem = DeferredRegister.createItems(Main.ID);

        public static final DeferredItem<Item> CAKE_STICK = zItem.registerItem("cake_stick",
                        p -> new CakeStick(p));

        public static final DeferredHolder<Item, Item> REDSTONE_ACID = zItem.registerItem("redstone_acid",
                        p -> new RedstoneAcid(p));

        public static final DeferredHolder<Item, Item> HONEY_SOLUTION = zItem.registerItem("honey_solution",
                        p -> new HoneySolution(p));

        public static final DeferredHolder<Item, Item> CHISEL = zItem.registerItem("chisel",
                        p -> new Chisel(p));

        public static final DeferredHolder<Item, Item> PATINA = zItem.registerSimpleItem("patina");

}
