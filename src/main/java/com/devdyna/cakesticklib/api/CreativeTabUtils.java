package com.devdyna.cakesticklib.api;

import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Items;

public class CreativeTabUtils {
    public static void accept(BuildCreativeModeTabContentsEvent e, ItemLike... items) {
        for (var i : items)
            e.accept(i);
    }

    public static void accept(BuildCreativeModeTabContentsEvent e, DeferredRegister.Items... items) {
        for (Items i : items)
            for (var r : i.getEntries())
                e.accept(r.get());
    }
}
