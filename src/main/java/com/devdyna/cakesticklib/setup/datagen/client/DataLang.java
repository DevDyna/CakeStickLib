package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;
import java.util.List;

import com.devdyna.cakesticklib.api.datagen.LangGenerators;
import com.devdyna.cakesticklib.api.compat.jei.JEIAliasesHelper;
import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier.UseType;
import com.devdyna.cakesticklib.setup.Config;
import com.devdyna.cakesticklib.setup.registry.*;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataLang extends LanguageProvider implements LangGenerators {

        public DataLang(PackOutput o) {
                super(o, MODULE_ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                LibFluids.zFluidTypes.getEntries()
                                .forEach(f -> addFluid(f.get(), named(f).replace(" Type", "")));
                LibItems.zBucketItems.getEntries().forEach(i -> addItem(i, named(i)));
                LibBlocks.zBlockFluids.getEntries().forEach(b -> addBlock(b, named(b)));

                // jei aliases
                add(JEIAliasesHelper.Alias.HOPPER, "Hopper");
                add(JEIAliasesHelper.Alias.REDSTONE_SENSIBLE, "Redstone Sensible");
                add(JEIAliasesHelper.Alias.REDSTONE_TIMER, "Redstone clock");
                add(JEIAliasesHelper.Alias.REDSTONE_COMPONENT, "Redstone component");

                add(JEIAliasesHelper.Alias.ENTITY_KILLER, "Entity killer");
                add(JEIAliasesHelper.Alias.ENTITY_FAKEPLAYER, "FakePlayer");
                add(JEIAliasesHelper.Alias.ENTITY_MOVER, "Entity Mover");

                add(JEIAliasesHelper.Alias.ITEM_COLLECTOR, "Item Collector");
                add(JEIAliasesHelper.Alias.BLOCK_BREAKER, "Block Breaker");
                add(JEIAliasesHelper.Alias.TIMER, "Timer");
                add(JEIAliasesHelper.Alias.ENERGY_GENERATOR, "Energy Generator");
                add(JEIAliasesHelper.Alias.ITEM_REPAIRER, "Item Repairer");
                add(JEIAliasesHelper.Alias.TRASH_CAN, "Trash Can");
                add(JEIAliasesHelper.Alias.FLUID_BUFFER, "Fluid storage buffer");
                add(JEIAliasesHelper.Alias.ENERGY_BUFFER, "Energy storage buffer");
                add(JEIAliasesHelper.Alias.ITEM_BUFFER, "Item storage buffer");

                add(JEIAliasesHelper.Alias.TREE_CUTTER, "Tree cutter");
                add(JEIAliasesHelper.Alias.TREE_FELLER, "Tree feller");
                add(JEIAliasesHelper.Alias.DRILL, "Drill");
                add(JEIAliasesHelper.Alias.MINER, "Miner");
                add(JEIAliasesHelper.Alias.ETHEREAL, "Ethereal");

                // mainly for Item Tooltips
                add(MODULE_ID + ".hold.shift", "§8Hold [" + TipColors.ITEM_TOOLTIP + "Shift§8] to see more details");
                add(MODULE_ID + ".hold.ctrl", "§8Hold [" + TipColors.ITEM_TOOLTIP + "Control§8] to see more details");
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

                // KeepItemStorage
                add(MODULE_ID + ".keep.storage.contain", TipColors.LIGHT_GRAY + "Contain some items");
                add(MODULE_ID + ".keep.storage.tip", TipColors.ITEM_TOOLTIP + "Keeps its items when broken");

                // setup

                add(MODULE_ID + ".creative_tab.resources", "Resources");

                List.of(
                                LibItems.zItem,
                                LibItems.zSimple,
                                LibItems.zUpgrades,
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
                                .forEach(c -> c.forEach(i -> addItem(i, named(i))));

                add(MODULE_ID + ".redstone_acid.tip", TipColors.ITEM_TOOLTIP + "Oxidize copper blocks");
                add(MODULE_ID + ".honey_solution.tip", TipColors.ITEM_TOOLTIP + "Wax copper blocks");

                add(MODULE_ID + ".glass_cutter.tip", TipColors.ITEM_TOOLTIP + "Instamine glass blocks");
                add(MODULE_ID + ".wrench.tip", TipColors.ITEM_TOOLTIP + "Rotate blocks");

                add(MODULE_ID + ".cakestick.tip", TipColors.ITEM_TOOLTIP + "Place cake slices");
                advKey("cake_stick", "The cake is(n't) a lie!", "The Cake stick is right!");

                add(MODULE_ID + ".jei.copper_oxidation", "Copper Oxidation Info");
                add(MODULE_ID + ".jei.strippable", "Block Strippable Info");

                add(MODULE_ID + ".jei.patina_drop",
                                TipColors.ITEM_TOOLTIP + "Drop " + TipColors.WHITE + "0-2" + TipColors.ITEM_TOOLTIP
                                                + " items every scrape");

                // upgrades

                add(MODULE_ID + ".upgrades.title", TipColors.ITEM_TOOLTIP + "Upgrade Modifiers");

                add(MODULE_ID + ".upgrades.modifier.energy", TipColors.ITEM_TOOLTIP + "Energy Usage: %s");
                add(MODULE_ID + ".upgrades.modifier.speed", TipColors.ITEM_TOOLTIP + "Recipe Speed: %s");
                add(MODULE_ID + ".upgrades.modifier.luck", TipColors.ITEM_TOOLTIP + "Secondary Output: %s");
                add(MODULE_ID + ".upgrades.modifier.fluid", TipColors.ITEM_TOOLTIP + "Fluid Usage: %s");
                add(MODULE_ID + ".upgrades.modifier.eject", TipColors.ITEM_TOOLTIP + "Ejection to: %s §7| Type : %s");

                add(MODULE_ID + ".screen.upgrades", "Supported Upgrades:");

                add(MODULE_ID + ".screen.modifier.energy", TipColors.ITEM_TOOLTIP + "Energy Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.speed", TipColors.ITEM_TOOLTIP + "Speed Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.luck", TipColors.ITEM_TOOLTIP + "Luck Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.fluid", TipColors.ITEM_TOOLTIP + "Fluid Modifier §7[§f§a%s§7]");
                add(MODULE_ID + ".screen.modifier.eject", TipColors.ITEM_TOOLTIP + "Eject Modifier §7[§f§a%s§7]");

                add(MODULE_ID + ".item_use.install", "Upgrade installed successfully");

                add(MODULE_ID + ".item_use.eject", "Eject Modifier Set to ");

                add(MODULE_ID + ".eject.gui.title", "Eject Modifier Settings");
                add(MODULE_ID + ".eject.gui.dir", "Selected: %s");

                add(MODULE_ID + ".upgrades.modifier.eject.shift",
                                TipColors.GRAY + TipColors.ITALIC + "<Shift-RightClick to configure>");

                add(MODULE_ID + ".upgrades.modifier.empty",
                                TipColors.GRAY + TipColors.ITALIC + "<Put on a crafting grid to reset>");

                for (var dir : Direction.values())
                        add(MODULE_ID + ".widgets.button." + dir.name(), dir.getName() + " Directional Button");

                for (var v : UseType.values())
                        add(MODULE_ID + ".widgets.button." + v.name().toLowerCase(),
                                        ((v == UseType.ITEM) ? TipColors.YELLOW : TipColors.LIGHT_BLUE) + TipColors.BOLD
                                                        + v.name());

                // config
                addConfigTitle("events", "Gameplay Additions");
                addConfigTitle("upgrades", "Machine Upgrades");

                addConfigKey(Config.HARVESTABLE_ACTION, "Harvestable Action");
                addConfigKey(Config.TREE_CUTTING_LIMIT, "Tree Cutting Limit");
                addConfigKey(Config.ENDER_EYE_RETURN_EVENT, "Eye of Ender Event");
                addConfigKey(Config.PATINA_DROP_EVENT, "Patina drop");
                addConfigKey(Config.REDSTONE_ACID_EVENT, "Redstone Acid use");
                addConfigKey(Config.HONEY_SOLUTION_EVENT, "Honey Solution use");

                addConfigKey(Config.MACHINE_MAX_SPEED_UPGRADES_TYPE, "Max Speed Modifiers");
                addConfigKey(Config.MACHINE_MAX_ENERGY_UPGRADES_TYPE, "Max Energy Modifiers");
                addConfigKey(Config.MACHINE_MAX_LUCK_UPGRADES_TYPE, "Max Luck Modifiers");
                addConfigKey(Config.MACHINE_MAX_FLUID_UPGRADES_TYPE, "Max Fluid Modifiers");

                addConfigKey(Config.MACHINE_MINIMAL_TICK_DELAY, "Minimal Tick Rate");
                addConfigKey(Config.MACHINE_MINIMAL_FE_COST, "Minimal FE Cost");
                addConfigKey(Config.MACHINE_MINIMAL_FLUID_COST, "Minimal MB Cost");
                addConfigKey(Config.MACHINE_MAXIMAL_LUCK, "Maximal Luck");

                // extra
                add("extra.effect.minecraft.glowing",
                                TipColors.MobEffectTypes.NEUTRAL + "Glowing (00:10)");

                add(MODULE_ID + ".jei.recipe.item_replace.remainder", "Replaced with %s");
                add(MODULE_ID + ".jei.recipe.durability_consume", "Consume %s durability");

                advKey("wrought_iron_ingot", "One shade of gray", "Combine some carbon dust with an iron ingot");
               
                advKey("steel_ingot", "Steel!", "Smelt on a blast furnace a wrought iron ingot");
               
                advKey("chisel", "StoneCutter on a Stick", "Craft a chisel to craft in-world stonecutting recipes");
               
                advKey("glass_cutter", "Cut off from the origin", "Craft a glass cutter to insta-mine glass blocks");
                
                advKey("hammer", "It's Hammer Time!", "Craft an hammer to craft new stuff");
               
                advKey("honey_solution", "Wax Standby",
                                "Craft an bottle of honey solution to wax stuff more efficiently");

                advKey("redstone_acid", "Wax NOT FOUND",
                                "Craft a bottle of redstone acid to directly oxidize copper blocks");

                advKey("patina", "Wax x@M", "Obtain some patina by scrapping oxidized copper blocks");

                advKey("wrench", "You spin me round", "Craft a wrench to cycle blockstates of clicked blocks");

                advKey("upgrades", "Let's mod it!", "Obtain some upgrades");

                // jade related
                add(MODULE_ID + ".jade.textual_delay.time.left",
                                TipColors.LIGHT_GRAY + "Crafting" + TipColors.LIGHT_GRAY + " : " + TipColors.WHITE
                                                + "%s");
                add(MODULE_ID + ".jade.textual_delay.time.empty", TipColors.YELLOW + "Waiting");
                add(MODULE_ID + ".jade.textual_delay.multiplier", "[%sx" + TipColors.LIGHT_GRAY + "] ");

                add("config.jade.plugin_" + MODULE_ID + ".textual_delay", "Textual Progress Recipes Info");
                add("config.jade.plugin_" + MODULE_ID + ".progressbar_delay", "Progress Bar Recipes Info");

                add(MODULE_ID + ".jade.hold.ctrl", TipColors.ITEM_TOOLTIP + "Hold [" + TipColors.YELLOW + "Control"
                                + TipColors.ITEM_TOOLTIP + "] to see more details");

                add(MODULE_ID + ".jade.hold.shift", TipColors.ITEM_TOOLTIP + "Hold [" + TipColors.YELLOW + "Shift"
                                + TipColors.ITEM_TOOLTIP + "] to see more details");

                add(MODULE_ID + ".screen.modifier.overflow.energy",
                                TipColors.RED + "Energy Modifier already maxed");
                add(MODULE_ID + ".screen.modifier.overflow.speed",
                                TipColors.RED + "Speed Modifier already maxed");
                add(MODULE_ID + ".screen.modifier.overflow.luck",
                                TipColors.RED + "Luck Modifier already maxed");
                add(MODULE_ID + ".screen.modifier.overflow.fluid",
                                TipColors.RED + "Fluid Modifier already maxed");
                add(MODULE_ID + ".screen.modifier.overflow.eject",
                                TipColors.RED + "Eject Modifier already maxed");

                add(MODULE_ID + ".gui.tip.scroll.up", TipColors.GRAY + TipColors.ITALIC + "<Scroll up>");
                add(MODULE_ID + ".gui.tip.scroll.down", TipColors.GRAY + TipColors.ITALIC + "<Scroll down>");

        }

}