package com.devdyna.cakesticklib;

import com.devdyna.cakesticklib.setup.Config;
import com.devdyna.cakesticklib.setup.GameEvents;
import com.devdyna.cakesticklib.setup.registry.zLibrary;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(CakeStickLib.MODULE_ID)
public class CakeStickLib {

    public static final String MODULE_ID = "cakesticklib";

    public CakeStickLib(IEventBus bus, ModContainer c) {
        zLibrary.register(bus);
        GameEvents.build(bus, c);
        Config.register(c);
    }

}
