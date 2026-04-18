package com.devdyna.cakesticklib.setup.datagen;

import static com.devdyna.cakesticklib.Main.MODULE_ID;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.datagen.client.*;
import com.devdyna.cakesticklib.setup.datagen.server.*;
import com.devdyna.cakesticklib.setup.datagen.server.DataAdvancement.DataAdvancementGenerator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataGenerator.PackGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = MODULE_ID)
public class Controller {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client e) {
        DataGenerator g = e.getGenerator();
        CompletableFuture<HolderLookup.Provider> pr = e.getLookupProvider();
        PackGenerator v = g.getVanillaPack(true);

        // client

        v.addProvider(DataLang::new);

        // server

        v.addProvider(o -> new DataAdvancement(o, pr, List.of(new DataAdvancementGenerator())));

        v.addProvider(o -> new DataRecipe.RecipeRunner(o, pr));
    }

}