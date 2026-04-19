package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.devdyna.cakesticklib.api.datagen.AdvancementsUtils;
import com.devdyna.cakesticklib.setup.registry.types.zItems;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;

public class DataAdvancement extends AdvancementProvider {

        public DataAdvancement(PackOutput output, CompletableFuture<Provider> registries,
                        List<AdvancementSubProvider> subProviders) {
                super(output, registries, subProviders);
        }

        public static class DataAdvancementGenerator implements AdvancementSubProvider {

                @Override
                public void generate(Provider p, Consumer<AdvancementHolder> c) {

                         AdvancementsUtils
                                        .getExistingParent("minecraft:adventure/root", zItems.CAKE_STICK.get(),
                                                        MODULE_ID,
                                                        "cake_stick", AdvancementType.CHALLENGE, true, true, false)
                                        .addCriterion("craft_cake_stick",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.CAKE_STICK.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_cake_stick")))
                                        .save(c, MODULE_ID + ":setup/cake_stick");

                }

        }

}