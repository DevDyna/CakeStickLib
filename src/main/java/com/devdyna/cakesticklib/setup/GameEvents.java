package com.devdyna.cakesticklib.setup;

import com.devdyna.cakesticklib.setup.common.*;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

public class GameEvents {

    public static void build(IEventBus bus, ModContainer c) {
        NeoForge.EVENT_BUS.register(ItemStorageBreak.class);
        NeoForge.EVENT_BUS.register(ItemToolTipped.class);
        bus.register(CreativeTab.class);
    }

}
