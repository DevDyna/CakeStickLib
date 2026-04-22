package com.devdyna.cakesticklib.api;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Items;

public class CreativeTabUtils {
    public static void accept(BuildCreativeModeTabContentsEvent e, Item... items) {
        for (Item i : items)
            e.accept(i);
    }

    public static void accept(BuildCreativeModeTabContentsEvent e, DeferredRegister.Items... items) {
        for (Items i : items)
            for (DeferredHolder<Item, ? extends Item> r : i.getEntries())
                e.accept(r.get());
    }
}
