package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.devdyna.cakesticklib.api.datagen.AdvancementGenerator;
import com.devdyna.cakesticklib.api.datagen.AdvancementsUtils;
import com.devdyna.cakesticklib.setup.registry.*;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;

public class DataAdvancement extends AdvancementProvider {

        public DataAdvancement(PackOutput output, CompletableFuture<Provider> registries,
                        List<AdvancementSubProvider> subProviders) {
                super(output, registries, subProviders);
        }

        public static class DataAdvancementGenerator implements AdvancementSubProvider, AdvancementGenerator {

                @Override
                public void generate(Provider p, Consumer<AdvancementHolder> c) {

                        AdvancementsUtils
                                        .getExistingParent(VanillaAdvancments.ADVENTURE_ROOT, LibItems.CAKE_STICK.get(),
                                                        MODULE_ID,
                                                        "cake_stick", AdvancementType.CHALLENGE, true, true, false)
                                        .addCriterion("craft_cake_stick",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(LibItems.CAKE_STICK.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_cake_stick")))
                                        .save(c, MODULE_ID + ":setup/cake_stick");

                        AdvancementsUtils
                                        .getExistingParent(VanillaAdvancments.ADVENTURE_ROOT,
                                                        LibItems.ENERGY_UPGRADE.get(),
                                                        MODULE_ID,
                                                        "upgrades", AdvancementType.CHALLENGE, true, true, false)
                                        .addCriterion("craft_upgrades",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(ItemPredicate.Builder.item().of(p
                                                                                        .lookupOrThrow(Registries.ITEM),
                                                                                        LibTags.Items.UPGRADES)
                                                                                        .build()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_upgrades")))
                                        .save(c, MODULE_ID + ":setup/upgrades");

                        var wrought_iron = simpleTask(VanillaAdvancments.SMELT_IRON,
                                        LibItems.WROUGHT_IRON_INGOT.get(),
                                        MODULE_ID + ":setup/", c);

                        simpleTask(wrought_iron,
                                        LibItems.STEEL_INGOT.get(),
                                        MODULE_ID + ":setup/", c);

                        simpleTask(VanillaAdvancments.SMELT_IRON,
                                        LibItems.CHISEL.get(),
                                        MODULE_ID + ":setup/", c);

                        simpleTask(VanillaAdvancments.SMELT_IRON,
                                        LibItems.HAMMER.get(),
                                        MODULE_ID + ":setup/", c);

                        simpleTask(VanillaAdvancments.WAX_OFF,
                                        LibItems.HONEY_SOLUTION.get(),
                                        MODULE_ID + ":setup/", c);

                        var patina = simpleTask(VanillaAdvancments.WAX_OFF,
                                        LibItems.PATINA.get(),
                                        MODULE_ID + ":setup/", c);

                        simpleTask(patina,
                                        LibItems.REDSTONE_ACID.get(),
                                        MODULE_ID + ":setup/", c);

                        simpleTask(VanillaAdvancments.SMELT_IRON,
                                        LibItems.GLASS_CUTTER.get(),
                                        MODULE_ID + ":setup/", c);

                        simpleTask(VanillaAdvancments.SMELT_IRON,
                                        LibItems.WRENCH.get(),
                                        MODULE_ID + ":setup/", c);
                }

                @Override
                public String getModName() {
                        return MODULE_ID;
                }

        }

}