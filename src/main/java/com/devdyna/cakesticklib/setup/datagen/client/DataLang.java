package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;
import static com.devdyna.cakesticklib.api.datagen.LangUtils.*;

import java.util.List;

import com.devdyna.cakesticklib.api.datagen.LangUtils;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataLang extends LanguageProvider {

        public DataLang(PackOutput o) {
                super(o, MODULE_ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                // mainly for Item Tooltips
                add(MODULE_ID + ".hold.shift", "§8Hold [" + TIP_COLOR + "Shift§8] to see more details");
                add(MODULE_ID + ".item.disabled", TIP_COLOR + "Item-Form unobtainable");
                add(MODULE_ID + ".item.placeable", TIP_COLOR + "Can be placed");
                add(MODULE_ID + ".block.rotate_by_click", TIP_COLOR + "Can be rotated with right-click");
                add(MODULE_ID + ".block.blast_proof", TIP_COLOR + "Blast resistance");
                add(MODULE_ID + ".item.click.install", TIP_COLOR + "Right click to install");
                add(MODULE_ID + ".block.safe_building", TIP_COLOR + "Safe for decoration");
                add(MODULE_ID + ".item.crafting_ingredient", TIP_COLOR + "Crafting Ingredient");

                // mainly for menu or JEI categories
                add(MODULE_ID + ".ui.dont_consume", "§cNot consume");
                //
                add(MODULE_ID + ".tank_interact.empty", "Empty");
                add(MODULE_ID + ".interaction.world.immutable", "In-World interaction not editable");

                // mainly for production descriptions
                add(MODULE_ID + ".provider.generic", "Generate %s %s every %d ticks");
                add(MODULE_ID + ".provider.every_tick", "Generate %s %s every tick");
                //
                add(MODULE_ID + ".info.status.false", "Status: §cInactive");
                add(MODULE_ID + ".info.status.true", "Status: §aActive");
                add(MODULE_ID + ".info.color", "Color: %d");
                add(MODULE_ID + ".info.blockpos", "BlockPos : ");
                add(MODULE_ID + ".info.dir", "Dir : ");
                add(MODULE_ID + ".info.dirs", "Dirs : ");
                add(MODULE_ID + ".info.identifier.hold",
                                TIP_COLOR + "Block : ");

                add(MODULE_ID + ".info.identifier.desc",
                                TIP_COLOR + "Craft in-world Stonecutter recipes when bound to the result block");

                // setup

                add(MODULE_ID + ".creative_tab.resources", "Resources");

                List.of(
                                zItems.zItem,
                                zItems.zSimple,
                                zItems.zUpgrade,
                                zItems.zDusts,
                                zItems.zPlates,
                                zItems.zPebbles,
                                zItems.zMolds,
                                zItems.zIngots,
                                zItems.zDeposits,
                                zItems.zChunks)
                                .stream()
                                .map(DeferredRegister.Items::getEntries)
                                .forEach(c -> c.forEach(i -> addItem(i, named(i, MODULE_ID))));

                add(MODULE_ID + ".redstone_acid.tip", TIP_COLOR + "Oxidize copper blocks");
                add(MODULE_ID + ".honey_solution.tip", TIP_COLOR + "Wax copper blocks");

                add(MODULE_ID + ".cakestick.tip", TIP_COLOR + "Place cake slices");
                LangUtils.advKey(this, MODULE_ID, "cake_stick", "The cake is(n't) a lie!", "The Cake stick is right!");

                add(MODULE_ID + ".jei.copper_oxidation", "Copper Oxidation Info");

                add(MODULE_ID + ".jei.patina_drop", TIP_COLOR + "Drop %s" + TIP_COLOR + " items every scrape");

                // upgrades

                add(MODULE_ID + ".upgrades.title", TIP_COLOR + "Upgrade Modifiers");

                add(MODULE_ID + ".upgrades.modifier.energy", TIP_COLOR + "Energy Usage: %s");
                add(MODULE_ID + ".upgrades.modifier.speed", TIP_COLOR + "Recipe Speed: %s");
                add(MODULE_ID + ".upgrades.modifier.luck", TIP_COLOR + "Secondary Output: %s");
                add(MODULE_ID + ".upgrades.modifier.fluid", TIP_COLOR + "Fluid Usage: %s");

                add(MODULE_ID + ".screen.upgrades", "Supported Upgrades:");

                add(MODULE_ID + ".screen.modifier.energy", TIP_COLOR + "Energy Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.speed", TIP_COLOR + "Speed Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.luck", TIP_COLOR + "Luck Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.fluid", TIP_COLOR + "Fluid Modifier §7[§f§a%s§7]");

                // config
                add(MODULE_ID + ".configuration.events", "General events");

                add(MODULE_ID + ".configuration.upgrades", "Machine Upgrades");

                add(MODULE_ID + ".configuration.harvestable_action",
                                "Harvestable Action");

                add(MODULE_ID + ".configuration.tree_cutting_limit",
                                "Tree Cutting Limit");

                add(MODULE_ID + ".configuration.ender_eye_return",
                                "Eye of Ender Event");

                add(MODULE_ID + ".configuration.patina_drop",
                                "Patina drop");

                add(MODULE_ID + ".configuration.redstone_acid_oxide",
                                "Redstone Acid use");

                add(MODULE_ID + ".configuration.honey_solution_wax",
                                "Honey Solution use");

                add(MODULE_ID + ".configuration.max_speed_upgrades",
                                "Max Speed Modifiers");

                add(MODULE_ID + ".configuration.max_energy_upgrades",
                                "Max Energy Modifiers");

                add(MODULE_ID + ".configuration.max_luck_upgrades",
                                "Max Luck Modifiers");

                add(MODULE_ID + ".configuration.max_fluid_upgrades",
                                "Max Fluid Modifiers");

                add(MODULE_ID + ".configuration.min_tick_rate",
                                "Minimal Tick Rate");

                add(MODULE_ID + ".configuration.min_fe_cost",
                                "Minimal FE Cost");

                add(MODULE_ID + ".configuration.min_mb_cost",
                                "Minimal MB Cost");

                add(MODULE_ID + ".configuration.max_luck",
                                "Maximal Luck");

        }

}