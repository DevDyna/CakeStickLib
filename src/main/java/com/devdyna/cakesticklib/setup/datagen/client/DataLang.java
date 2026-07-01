package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;
import static com.devdyna.cakesticklib.api.datagen.LangUtils.*;

import java.util.List;

import com.devdyna.cakesticklib.api.compat.jei.JEIAliasesHelper;
import com.devdyna.cakesticklib.api.datagen.LangUtils;
import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.setup.registry.*;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataLang extends LanguageProvider {

        public DataLang(PackOutput o) {
                super(o, MODULE_ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                // jei aliases
                add(JEIAliasesHelper.Aliases.BLOCK_BREAKER, "Block Breaker");
                add(JEIAliasesHelper.Aliases.DRILL, "Drill");
                add(JEIAliasesHelper.Aliases.MINER, "Miner");
                add(JEIAliasesHelper.Aliases.TREE_CUTTER, "Tree cutter");
                add(JEIAliasesHelper.Aliases.TREE_FELLER, "Tree feller");
                add(JEIAliasesHelper.Aliases.ENTITY_KILLER, "Entity killer");
                add(JEIAliasesHelper.Aliases.FAKEPLAYER, "FakePlayer");
                add(JEIAliasesHelper.Aliases.HOPPER, "Hopper");
                add(JEIAliasesHelper.Aliases.ITEM_COLLECTOR, "Item Collector");
                add(JEIAliasesHelper.Aliases.ENTITY_MOVER, "Entity Mover");
                add(JEIAliasesHelper.Aliases.REDSTONE_SENSIBLE, "Redstone Sensible");

                // mainly for Item Tooltips
                add(MODULE_ID + ".hold.shift", "§8Hold [" + TipColors.ITEM_TOOLTIP + "Shift§8] to see more details");
                add(MODULE_ID + ".item.disabled", TipColors.ITEM_TOOLTIP + "Item-Form unobtainable");
                add(MODULE_ID + ".item.placeable", TipColors.ITEM_TOOLTIP + "Can be placed");
                add(MODULE_ID + ".block.rotate_by_click", TipColors.ITEM_TOOLTIP + "Can be rotated with right-click");
                add(MODULE_ID + ".block.blast_proof", TipColors.ITEM_TOOLTIP + "Blast resistance");
                add(MODULE_ID + ".item.click.install", TipColors.ITEM_TOOLTIP + "Right click to install");
                add(MODULE_ID + ".block.safe_building", TipColors.ITEM_TOOLTIP + "Safe for decoration");
                add(MODULE_ID + ".item.crafting_ingredient", TipColors.ITEM_TOOLTIP + "Crafting Ingredient");

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
                                TipColors.ITEM_TOOLTIP + "Block : ");

                add(MODULE_ID + ".info.identifier.desc",
                                TipColors.ITEM_TOOLTIP
                                                + "Craft in-world Stonecutter recipes when bound to the result block");

                // setup

                add(MODULE_ID + ".creative_tab.resources", "Resources");

                List.of(

                                LibItems.zItem,
                                LibItems.zSimple,
                                LibItems.zUpgrade,
                                LibItems.zDusts,
                                LibItems.zPlates,
                                LibItems.zPebbles,
                                LibItems.zMolds,
                                LibItems.zIngots,
                                LibItems.zGears,
                                LibItems.zNuggets,
                                LibItems.zFoils,
                                LibItems.zCoils,
                                LibItems.zDeposits,
                                LibItems.zChunks,
                                LibItems.zBlockItem)
                                .stream()
                                .map(DeferredRegister.Items::getEntries)
                                .forEach(c -> c.forEach(i -> addItem(i, named(i, MODULE_ID))));

                add(MODULE_ID + ".redstone_acid.tip", TipColors.ITEM_TOOLTIP + "Oxidize copper blocks");
                add(MODULE_ID + ".honey_solution.tip", TipColors.ITEM_TOOLTIP + "Wax copper blocks");

                add(MODULE_ID + ".glass_cutter.tip", TipColors.ITEM_TOOLTIP + "Instamine glass blocks");
                add(MODULE_ID + ".wrench.tip", TipColors.ITEM_TOOLTIP + "Rotate blocks");

                add(MODULE_ID + ".cakestick.tip", TipColors.ITEM_TOOLTIP + "Place cake slices");
                LangUtils.advKey(this, MODULE_ID, "cake_stick", "The cake is(n't) a lie!", "The Cake stick is right!");

                add(MODULE_ID + ".jei.copper_oxidation", "Copper Oxidation Info");
                add(MODULE_ID + ".jei.strippable", "Block Strippable Info");

                add(MODULE_ID + ".jei.patina_drop",
                                TipColors.ITEM_TOOLTIP + "Drop %s" + TipColors.ITEM_TOOLTIP + " items every scrape");

                // upgrades

                add(MODULE_ID + ".upgrades.title", TipColors.ITEM_TOOLTIP + "Upgrade Modifiers");

                add(MODULE_ID + ".upgrades.modifier.energy", TipColors.ITEM_TOOLTIP + "Energy Usage: %s");
                add(MODULE_ID + ".upgrades.modifier.speed", TipColors.ITEM_TOOLTIP + "Recipe Speed: %s");
                add(MODULE_ID + ".upgrades.modifier.luck", TipColors.ITEM_TOOLTIP + "Secondary Output: %s");
                add(MODULE_ID + ".upgrades.modifier.fluid", TipColors.ITEM_TOOLTIP + "Fluid Usage: %s");

                add(MODULE_ID + ".screen.upgrades", "Supported Upgrades:");

                add(MODULE_ID + ".screen.modifier.energy", TipColors.ITEM_TOOLTIP + "Energy Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.speed", TipColors.ITEM_TOOLTIP + "Speed Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.luck", TipColors.ITEM_TOOLTIP + "Luck Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.fluid", TipColors.ITEM_TOOLTIP + "Fluid Modifier §7[§f§a%s§7]");

                // config
                add(MODULE_ID + ".configuration.events", "General events");

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

                add(MODULE_ID + ".configuration.upgrades", "Machine Upgrades");

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