package com.devdyna.cakesticklib.setup.registry.types;

import com.devdyna.cakesticklib.Main;
import com.devdyna.cakesticklib.setup.registry.CakeStick;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {
    public static void register(IEventBus bus) {
        zItem.register(bus);
    }

    public static final DeferredRegister.Items zItem = DeferredRegister.createItems(Main.ID);

    public static final DeferredItem<Item> CAKE_STICK = zItem.register("cake_stick",()-> new CakeStick());

}
