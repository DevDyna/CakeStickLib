package com.devdyna.cakesticklib.setup.registry;

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

                public static final TagKey<Block> BLOCK_STEEL = RegistryUtils
                                .tagBlock("c", "storage_blocks/steel");

                public static final TagKey<Block> BLOCK_ADVANCED_ALLOY = RegistryUtils
                                .tagBlock("c", "storage_blocks/advanced_alloy");

                public static final TagKey<Block> BLOCK_WROUGHT_IRON = RegistryUtils
                                .tagBlock("c", "storage_blocks/wrought_iron");
        }

        public class Items {

                public static void register(IEventBus bus) {
                }

                public static final TagKey<Item> SAWDUST = RegistryUtils.tagItem("c", "dusts/wood");
                public static final TagKey<Item> SAWDUST2 = RegistryUtils.tagItem("c", "dusts/saw");

                public static final TagKey<Item> METAL_NUGGETS = RegistryUtils.tagItem("c", "metal_nuggets");

                public static final TagKey<Item> DUST_SULFUR = RegistryUtils.tagItem("c", "dusts/sulfur");
                public static final TagKey<Item> DUST_GOLD = RegistryUtils.tagItem("c", "dusts/gold");
                public static final TagKey<Item> DUST_IRON = RegistryUtils.tagItem("c", "dusts/iron");
                public static final TagKey<Item> DUST_EMERALD = RegistryUtils.tagItem("c", "dusts/emerald");
                public static final TagKey<Item> DUST_QUARTZ = RegistryUtils.tagItem("c", "dusts/quartz");
                public static final TagKey<Item> DUST_DIAMOND = RegistryUtils.tagItem("c", "dusts/diamond");
                public static final TagKey<Item> DUST_AMETHYST = RegistryUtils.tagItem("c", "dusts/amethyst");
                public static final TagKey<Item> DUST_COPPER = RegistryUtils.tagItem("c", "dusts/copper");
                public static final TagKey<Item> DUST_COAL = RegistryUtils.tagItem("c", "dusts/coal");
                public static final TagKey<Item> DUST_LAPIS = RegistryUtils.tagItem("c", "dusts/lapis");

                public static final TagKey<Item> OXIDIZER = RegistryUtils.tagItem(CakeStickLib.MODULE_ID, "oxidizer");
                public static final TagKey<Item> WAXING = RegistryUtils.tagItem(CakeStickLib.MODULE_ID, "waxing");

                public static final TagKey<Item> INGOT_STEEL = RegistryUtils.tagItem("c", "ingots/steel");
                public static final TagKey<Item> INGOT_ADVANCEDALLOY = RegistryUtils.tagItem("c",
                                "ingots/advanced_alloy");
                public static final TagKey<Item> INGOT_WROUGHT_IRON = RegistryUtils.tagItem("c", "ingots/wrought_iron");

                public static final TagKey<Item> NUGGET_STEEL = RegistryUtils.tagItem("c", "nuggets/steel");
                public static final TagKey<Item> NUGGET_ADVANCEDALLOY = RegistryUtils.tagItem("c",
                                "nuggets/advanced_alloy");
                public static final TagKey<Item> NUGGET_COPPER = RegistryUtils.tagItem("c", "nuggets/copper");
                public static final TagKey<Item> NUGGET_WROUGHT_IRON = RegistryUtils.tagItem("c",
                                "nuggets/wrought_iron");

                public static final TagKey<Item> FOILS = RegistryUtils.tagItem("c", "foils");
                public static final TagKey<Item> FOIL_COPPER = RegistryUtils.tagItem("c", "foils/copper");
                public static final TagKey<Item> FOIL_GOLD = RegistryUtils.tagItem("c", "foils/gold");
                public static final TagKey<Item> FOIL_IRON = RegistryUtils.tagItem("c", "foils/iron");

                public static final TagKey<Item> PLATES = RegistryUtils.tagItem("c", "plates");

                public static final TagKey<Item> PLATE_COPPER = RegistryUtils.tagItem("c", "plates/copper");
                public static final TagKey<Item> PLATE_GOLD = RegistryUtils.tagItem("c", "plates/gold");
                public static final TagKey<Item> PLATE_IRON = RegistryUtils.tagItem("c", "plates/iron");
                public static final TagKey<Item> PLATE_STEEL = RegistryUtils.tagItem("c", "plates/steel");
                public static final TagKey<Item> PLATE_COAL = RegistryUtils.tagItem("c", "plates/coal");
                public static final TagKey<Item> PLATE_ADVANCED_ALLOY = RegistryUtils.tagItem("c",
                                "plates/advanced_alloy");
                public static final TagKey<Item> PLATE_WROUGHT_IRON = RegistryUtils.tagItem("c", "plates/wrought_iron");

                public static final TagKey<Item> GEMS_SILICON = RegistryUtils.tagItem("c", "gems/silicon");

                public static final TagKey<Item> SILICON = RegistryUtils.tagItem("c", "silicon");

                public static final TagKey<Item> GEARS = RegistryUtils.tagItem("c", "gears");
                public static final TagKey<Item> GEAR_WOODEN = RegistryUtils.tagItem("c", "gears/wooden");
                public static final TagKey<Item> GEAR_IRON = RegistryUtils.tagItem("c", "gears/iron");
                public static final TagKey<Item> GEAR_GOLD = RegistryUtils.tagItem("c", "gears/gold");
                public static final TagKey<Item> GEAR_COPPER = RegistryUtils.tagItem("c", "gears/copper");
                public static final TagKey<Item> GEAR_STEEL = RegistryUtils.tagItem("c", "gears/steel");

                public static final TagKey<Item> COILS = RegistryUtils.tagItem("c", "coils");
                public static final TagKey<Item> COIL_COPPER = RegistryUtils.tagItem("c", "coils/copper");
                public static final TagKey<Item> COIL_GOLD = RegistryUtils.tagItem("c", "coils/gold");
                public static final TagKey<Item> COIL_IRON = RegistryUtils.tagItem("c", "coils/iron");

                public static final TagKey<Item> UPGRADES = RegistryUtils
                                .tagItem(CakeStickLib.MODULE_ID, "upgrades");

                public static final TagKey<Item> MOLDS = RegistryUtils
                                .tagItem("c", "molds");

                public static final TagKey<Item> ELECTRON_TUBES = RegistryUtils.tagItem("c", "electron_tubes");

                public static final TagKey<Item> BLOCK_STEEL = RegistryUtils
                                .tagItem("c", "storage_blocks/steel");

                public static final TagKey<Item> BLOCK_ADVANCED_ALLOY = RegistryUtils
                                .tagItem("c", "storage_blocks/advanced_alloy");

                public static final TagKey<Item> BLOCK_WROUGHT_IRON = RegistryUtils
                                .tagItem("c", "storage_blocks/wrought_iron");

                public static final TagKey<Item> ORE_DEPOSITS = RegistryUtils.tagItem("c", "deposit");

                public static final TagKey<Item> BATTERIES = RegistryUtils.tagItem("c", "batteries");

        }

}
