package com.devdyna.cakesticklib.api;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CreativeTabUtils {
    public static void accept(BuildCreativeModeTabContentsEvent e, Item... items) {
        for (Item i : items)
            e.accept(i);
    }
}
