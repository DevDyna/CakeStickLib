package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.devdyna.cakesticklib.api.datagen.*;
import com.devdyna.cakesticklib.setup.registry.*;
import net.minecraft.advancements.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.*;

public class DataAdvancement extends AdvancementProvider {

        public DataAdvancement(PackOutput o, CompletableFuture<Provider> r, List<AdvancementSubProvider> s) {
                super(o, r, s);
        }

        public static class DataAdvancementGenerator implements AdvancementSubProvider, AdvancementGenerator {

                @Override
                public void generate(Provider p, Consumer<AdvancementHolder> c) {

                        AdvancementsUtils
                                        .getExistingParent(VanillaAdvancments.ADVENTURE_ROOT, LibItems.CAKE_STICK.get(),
                                                        MODULE_ID,
                                                        "cake_stick", AdvancementType.CHALLENGE, true, true, false)
                                        .addCriterion("craft_cake_stick",
                                                        has(LibItems.CAKE_STICK.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_cake_stick")))
                                        .save(c, MODULE_ID + ":setup/cake_stick");

                        AdvancementsUtils
                                        .getExistingParent(VanillaAdvancments.ADVENTURE_ROOT,
                                                        LibItems.ENERGY_UPGRADE.get(),
                                                        MODULE_ID,
                                                        "upgrades", AdvancementType.CHALLENGE,
                                                        true, true, false)
                                        .addCriterion("craft_upgrades",
                                                        has(p, LibTags.Items.UPGRADES))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_upgrades")))
                                        .save(c, MODULE_ID + ":setup/upgrades");
                                        
                        AdvancementsUtils
                                        .getExistingParent(VanillaAdvancments.ADVENTURE_ROOT,
                                                        LibItems.WOODEN_GEAR.get(),
                                                        MODULE_ID,
                                                        "_oil", AdvancementType.CHALLENGE,
                                                        true, true, false)
                                        .addCriterion("craft_coils", has(p, LibTags.Items.COILS))
                                        .addCriterion("craft_foils", has(p, LibTags.Items.FOILS))
                                        .addCriterion("craft_gears", has(p, LibTags.Items.GEARS))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_coils","craft_foils","craft_gears")))
                                        .save(c, MODULE_ID + ":setup/_oil");

                        simpleTask(VanillaAdvancments.ADVENTURE_ROOT,
                                        LibItems.DYNAMITE.get(),
                                        MODULE_ID + ":setup/", c);

                        simpleTask(VanillaAdvancments.ADVENTURE_ROOT,
                                        LibItems.WOODEN_CROOK.get(),
                                        MODULE_ID + ":setup/", c);

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

                        simpleTask(VanillaAdvancments.SMELT_IRON,
                                        LibItems.SAW.get(),
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