package com.devdyna.cakesticklib.setup;

import java.util.List;

import com.devdyna.cakesticklib.setup.common.*;
import com.devdyna.cakesticklib.setup.common.events.EnderEyeReturn;
import com.devdyna.cakesticklib.setup.common.events.ItemStorageBreak;
import com.devdyna.cakesticklib.setup.common.events.ItemToolTipped;
import com.devdyna.cakesticklib.setup.common.events.PatinaDropEvent;
import com.devdyna.cakesticklib.setup.common.events.RecipeSender;
import com.devdyna.cakesticklib.setup.common.events.VanillaHarvestable;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

public class GameEvents {

    public static void build(IEventBus bus, ModContainer c) {

        List.of(
                ItemStorageBreak.class,
                ItemToolTipped.class,
                PatinaDropEvent.class,
                VanillaHarvestable.class,
                RecipeSender.class,
                EnderEyeReturn.class
        ).forEach(NeoForge.EVENT_BUS::register);

        bus.register(CreativeTab.class);
    }

}
