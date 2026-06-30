package com.devdyna.cakesticklib.setup.registry;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.CakeStickLib;
import com.devdyna.cakesticklib.api.RegistryUtils;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

public class LibTags {

        public static void register(IEventBus bus) {
                Blocks.register(bus);
                Items.register(bus);
        }

        public class Blocks {
                public static void register(IEventBus bus) {
                }

                public static final TagKey<Block> HARVESTABLE_BLACKLIST = RegistryUtils
                                .tagBlock(MODULE_ID, "harvestable/blacklist");

                // storage_blocks
                public static final TagKey<Block> STEEL_BLOCK = RegistryUtils
                                .tagBlock("c", "storage_blocks/steel");

                public static final TagKey<Block> ADVANCED_ALLOY_BLOCK = RegistryUtils
                                .tagBlock("c", "storage_blocks/advanced_alloy");

                public static final TagKey<Block> WROUGHT_IRON_BLOCK = RegistryUtils
                                .tagBlock("c", "storage_blocks/wrought_iron");

                public static final TagKey<Block> MINEABLE_WITH_GLASS_CUTTER = RegistryUtils
                                .tagBlock(MODULE_ID, "mineable/glass_cutter");

        }

        public class Items {

                public static void register(IEventBus bus) {
                }

                // sawdust
                public static final TagKey<Item> SAWDUST = RegistryUtils.tagItem("c", "dusts/wood");
                public static final TagKey<Item> SAWDUST2 = RegistryUtils.tagItem("c", "dusts/saw");
                // dusts
                public static final TagKey<Item> SULFUR_DUST = RegistryUtils.tagItem("c", "dusts/sulfur");
                public static final TagKey<Item> GOLD_DUST = RegistryUtils.tagItem("c", "dusts/gold");
                public static final TagKey<Item> IRON_DUST = RegistryUtils.tagItem("c", "dusts/iron");
                public static final TagKey<Item> EMERALD_DUST = RegistryUtils.tagItem("c", "dusts/emerald");
                public static final TagKey<Item> QUARTZ_DUST = RegistryUtils.tagItem("c", "dusts/quartz");
                public static final TagKey<Item> DIAMOND_DUST = RegistryUtils.tagItem("c", "dusts/diamond");
                public static final TagKey<Item> AMETHYST_DUST = RegistryUtils.tagItem("c", "dusts/amethyst");
                public static final TagKey<Item> COPPER_DUST = RegistryUtils.tagItem("c", "dusts/copper");
                public static final TagKey<Item> COAL_DUST = RegistryUtils.tagItem("c", "dusts/coal");
                public static final TagKey<Item> CARBON_DUST = RegistryUtils.tagItem("c", "dusts/carbon");
                public static final TagKey<Item> LAPIS_DUST = RegistryUtils.tagItem("c", "dusts/lapis");
                public static final TagKey<Item> GLASS_DUST = RegistryUtils.tagItem("c", "dusts/glass");
                // ingots
                public static final TagKey<Item> STEEL_INGOT = RegistryUtils.tagItem("c", "ingots/steel");
                public static final TagKey<Item> ADVANCED_ALLOY_INGOT = RegistryUtils.tagItem("c",
                                "ingots/advanced_alloy");
                public static final TagKey<Item> WROUGHT_IRON_INGOT = RegistryUtils.tagItem("c", "ingots/wrought_iron");
                // nuggets
                public static final TagKey<Item> STEEL_NUGGET = RegistryUtils.tagItem("c", "nuggets/steel");
                public static final TagKey<Item> ADVANCED_ALLOY_NUGGET = RegistryUtils.tagItem("c",
                                "nuggets/advanced_alloy");
                public static final TagKey<Item> COPPER_NUGGET = RegistryUtils.tagItem("c", "nuggets/copper");
                public static final TagKey<Item> WROUGHT_IRON_NUGGET = RegistryUtils.tagItem("c",
                                "nuggets/wrought_iron");
                // foils
                public static final TagKey<Item> FOILS = RegistryUtils.tagItem("c", "foils");
                public static final TagKey<Item> COPPER_FOIL = RegistryUtils.tagItem("c", "foils/copper");
                public static final TagKey<Item> GOLD_FOIL = RegistryUtils.tagItem("c", "foils/gold");
                public static final TagKey<Item> IRON_FOIL = RegistryUtils.tagItem("c", "foils/iron");
                // plates
                public static final TagKey<Item> PLATES = RegistryUtils.tagItem("c", "plates");
                public static final TagKey<Item> COPPER_PLATE = RegistryUtils.tagItem("c", "plates/copper");
                public static final TagKey<Item> GOLD_PLATE = RegistryUtils.tagItem("c", "plates/gold");
                public static final TagKey<Item> IRON_PLATE = RegistryUtils.tagItem("c", "plates/iron");
                public static final TagKey<Item> STEEL_PLATE = RegistryUtils.tagItem("c", "plates/steel");
                public static final TagKey<Item> COAL_PLATE = RegistryUtils.tagItem("c", "plates/coal");
                public static final TagKey<Item> CARBON_PLATE = RegistryUtils.tagItem("c", "plates/carbon");
                public static final TagKey<Item> ADVANCED_ALLOY_PLATE = RegistryUtils.tagItem("c",
                                "plates/advanced_alloy");
                public static final TagKey<Item> WROUGHT_IRON_PLATE = RegistryUtils.tagItem("c", "plates/wrought_iron");
                // plastic
                public static final TagKey<Item> PLASTIC = RegistryUtils.tagItem("c", "plastic");
                public static final TagKey<Item> PLASTIC2 = RegistryUtils.tagItem("c", "plastics");
                public static final TagKey<Item> PLASTIC_PLATE = RegistryUtils.tagItem("c", "plates/plastic");
                public static final TagKey<Item> PLASTIC_PNEUMATICCRAFT = RegistryUtils.tagItem("pneumaticcraft",
                                "plates/plastic_sheets");
                // silicon
                public static final TagKey<Item> GEMS_SILICON = RegistryUtils.tagItem("c", "gems/silicon");
                public static final TagKey<Item> SILICON = RegistryUtils.tagItem("c", "silicon");
                // gears
                public static final TagKey<Item> GEARS = RegistryUtils.tagItem("c", "gears");
                public static final TagKey<Item> WOODEN_GEAR = RegistryUtils.tagItem("c", "gears/wooden");
                public static final TagKey<Item> IRON_GEAR = RegistryUtils.tagItem("c", "gears/iron");
                public static final TagKey<Item> GOLD_GEAR = RegistryUtils.tagItem("c", "gears/gold");
                public static final TagKey<Item> COPPER_GEAR = RegistryUtils.tagItem("c", "gears/copper");
                public static final TagKey<Item> STEEL_GEAR = RegistryUtils.tagItem("c", "gears/steel");
                // coils
                public static final TagKey<Item> COILS = RegistryUtils.tagItem("c", "coils");
                public static final TagKey<Item> COPPER_COIL = RegistryUtils.tagItem("c", "coils/copper");
                public static final TagKey<Item> GOLD_COIL = RegistryUtils.tagItem("c", "coils/gold");
                public static final TagKey<Item> IRON_COIL = RegistryUtils.tagItem("c", "coils/iron");

                // storage_blocks
                public static final TagKey<Item> STEEL_BLOCK = RegistryUtils.tagItem("c", "storage_blocks/steel");
                public static final TagKey<Item> ADVANCED_ALLOY_BLOCK = RegistryUtils.tagItem("c",
                                "storage_blocks/advanced_alloy");
                public static final TagKey<Item> WROUGHT_IRON_BLOCK = RegistryUtils.tagItem("c",
                                "storage_blocks/wrought_iron");
                //
                public static final TagKey<Item> ORE_DEPOSITS = RegistryUtils.tagItem("c", "deposit");
                //
                public static final TagKey<Item> BATTERIES = RegistryUtils.tagItem("c", "batteries");
                //
                public static final TagKey<Item> UPGRADES = RegistryUtils
                                .tagItem(CakeStickLib.MODULE_ID, "upgrades");
                //
                public static final TagKey<Item> MOLDS = RegistryUtils
                                .tagItem("c", "molds");
                //
                public static final TagKey<Item> ELECTRON_TUBES = RegistryUtils.tagItem("c", "electron_tubes");
                //
                public static final TagKey<Item> OXIDIZER = RegistryUtils.tagItem(CakeStickLib.MODULE_ID, "oxidizer");
                public static final TagKey<Item> WAXING = RegistryUtils.tagItem(CakeStickLib.MODULE_ID, "waxing");
                //
                public static final TagKey<Item> METAL_NUGGETS = RegistryUtils.tagItem("c", "metal_nuggets");
                //
                public static final TagKey<Item> FLOUR = RegistryUtils.tagItem("c", "flour");
                //
                public static final TagKey<Item> CARBON_FIBER = RegistryUtils.tagItem("c", "carbon_fiber");
                public static final TagKey<Item> CARBON_FIBER2 = RegistryUtils.tagItem("c", "carbon_fibers");

        }

}
