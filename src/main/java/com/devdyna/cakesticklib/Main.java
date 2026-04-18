package com.devdyna.cakesticklib;

import com.devdyna.cakesticklib.setup.GameEvents;
import com.devdyna.cakesticklib.setup.registry.Material;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(Main.MODULE_ID)
public class Main {

    public static final String MODULE_ID = "cakesticklib";
    public static final String ID = "synergy";

    public Main(IEventBus bus, ModContainer c) {
        Material.register(bus);
        GameEvents.build(bus, c);
    }

}
