package com.devdyna.cakesticklib.setup.registry;

import com.devdyna.cakesticklib.CakeStickLib;
import com.devdyna.cakesticklib.setup.registry.builders.CakeStick;
import com.devdyna.cakesticklib.setup.registry.builders.Chisel;
import com.devdyna.cakesticklib.setup.registry.builders.HoneySolution;
import com.devdyna.cakesticklib.setup.registry.builders.IndustrialUpgrade;
import com.devdyna.cakesticklib.setup.registry.builders.RedstoneAcid;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LibItems {
                public static void register(IEventBus bus) {
                        zItem.register(bus);
                        zSimple.register(bus);
                        zUpgrade.register(bus);
                        zDusts.register(bus);
                        zPlates.register(bus);
                        zPebbles.register(bus);
                        zMolds.register(bus);
                        zIngots.register(bus);
                        zDeposits.register(bus);
                        zChunks.register(bus);
                        zFoils.register(bus);
                        zCoils.register(bus);
                        zGears.register(bus);
                        zNuggets.register(bus);
                        zBlockItem.register(bus);
                }

                public static final DeferredRegister.Items zBlockItem = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zSimple = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zUpgrade = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zItem = DeferredRegister.createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zDusts = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zPlates = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zPebbles = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zMolds = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zIngots = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zDeposits = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zChunks = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zNuggets = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zCoils = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zFoils = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);
                public static final DeferredRegister.Items zGears = DeferredRegister
                                .createItems(CakeStickLib.MODULE_ID);

                public static final DeferredItem<Item> CAKE_STICK = zItem.registerItem("cake_stick",
                                p -> new CakeStick(p));

                public static final DeferredHolder<Item, Item> REDSTONE_ACID = zItem.registerItem("redstone_acid",
                                p -> new RedstoneAcid(p));

                public static final DeferredHolder<Item, Item> HONEY_SOLUTION = zItem.registerItem("honey_solution",
                                p -> new HoneySolution(p));

                public static final DeferredHolder<Item, Item> CHISEL = zItem.registerItem("chisel",
                                p -> new Chisel(p));

                public static final DeferredHolder<Item, Item> PATINA = zSimple.registerSimpleItem("patina");

                public static final DeferredHolder<Item, Item> SAWDUST = zSimple.registerSimpleItem("sawdust");
                public static final DeferredHolder<Item, Item> SULFUR_DUST = zDusts.registerSimpleItem("sulfur_dust");
                public static final DeferredHolder<Item, Item> FLOUR = zSimple.registerSimpleItem("flour");

                public static final DeferredHolder<Item, Item> AMETHYST_DUST = zDusts
                                .registerSimpleItem("amethyst_dust");
                public static final DeferredHolder<Item, Item> GLASS_DUST = zDusts.registerSimpleItem("glass_dust");

                public static final DeferredHolder<Item, Item> CARBON_DUST = zDusts.registerSimpleItem("carbon_dust");
                public static final DeferredHolder<Item, Item> COPPER_DUST = zDusts.registerSimpleItem("copper_dust");
                public static final DeferredHolder<Item, Item> DIAMOND_DUST = zDusts.registerSimpleItem("diamond_dust");
                public static final DeferredHolder<Item, Item> EMERALD_DUST = zDusts.registerSimpleItem("emerald_dust");
                public static final DeferredHolder<Item, Item> GOLD_DUST = zDusts.registerSimpleItem("gold_dust");
                public static final DeferredHolder<Item, Item> IRON_DUST = zDusts.registerSimpleItem("iron_dust");
                public static final DeferredHolder<Item, Item> LAPIS_DUST = zDusts.registerSimpleItem("lapis_dust");
                public static final DeferredHolder<Item, Item> QUARTZ_DUST = zDusts.registerSimpleItem("quartz_dust");

                public static final DeferredHolder<Item, IndustrialUpgrade> SPEED_UPGRADE = zUpgrade
                                .registerItem("speed_upgrade", p -> new IndustrialUpgrade(p));

                public static final DeferredHolder<Item, IndustrialUpgrade> ENERGY_UPGRADE = zUpgrade
                                .registerItem("energy_upgrade", p -> new IndustrialUpgrade(p));

                public static final DeferredHolder<Item, IndustrialUpgrade> LUCK_UPGRADE = zUpgrade
                                .registerItem("luck_upgrade", p -> new IndustrialUpgrade(p));

                public static final DeferredHolder<Item, IndustrialUpgrade> FLUID_UPGRADE = zUpgrade
                                .registerItem("fluid_upgrade", p -> new IndustrialUpgrade(p));

                public static final DeferredHolder<Item, Item> BLANK_MOLD = zSimple.registerSimpleItem("blank_mold");
                public static final DeferredHolder<Item, Item> CARBON_FIBER = zSimple
                                .registerSimpleItem("carbon_fiber");
                public static final DeferredHolder<Item, Item> CHIP = zSimple.registerSimpleItem("chip");
                public static final DeferredHolder<Item, Item> CONDENSER = zSimple.registerSimpleItem("condenser");
                public static final DeferredHolder<Item, Item> BLUE_BATTERY = zSimple
                                .registerSimpleItem("blue_battery");
                public static final DeferredHolder<Item, Item> GREEN_BATTERY = zSimple
                                .registerSimpleItem("green_battery");
                public static final DeferredHolder<Item, Item> ELECTRON_TUBE = zSimple
                                .registerSimpleItem("electron_tube");

                public static final DeferredHolder<Item, Item> FOSSIL = zSimple.registerSimpleItem("fossil");
                public static final DeferredHolder<Item, Item> PLASTIC = zSimple.registerSimpleItem("plastic");
                public static final DeferredHolder<Item, Item> HAMMER = zItem.registerSimpleItem("hammer",
                                p -> p.pickaxe(ToolMaterial.IRON, 6.0F, -3.1F)
                                                .durability(1024)
                                                .repairable(Items.IRON_INGOT));

                public static final DeferredHolder<Item, Item> RESISTOR = zSimple.registerSimpleItem("resistor");

                public static final DeferredHolder<Item, Item> ADVANCED_ALLOY_INGOT = zIngots
                                .registerSimpleItem("advanced_alloy_ingot");

                public static final DeferredHolder<Item, Item> WROUGHT_IRON_INGOT = zIngots
                                .registerSimpleItem("wrought_iron_ingot");

                public static final DeferredHolder<Item, Item> STEEL_INGOT = zIngots
                                .registerSimpleItem("steel_ingot");

                public static final DeferredHolder<Item, Item> MIXED_INGOT = zIngots
                                .registerSimpleItem("mixed_ingot");

                public static final DeferredHolder<Item, Item> ADVANCED_ALLOY_PLATE = zPlates
                                .registerSimpleItem("advanced_alloy_plate");

                public static final DeferredHolder<Item, Item> WROUGHT_IRON_PLATE = zPlates
                                .registerSimpleItem("wrought_iron_plate");

                public static final DeferredHolder<Item, Item> CARBON_PLATE = zPlates
                                .registerSimpleItem("carbon_plate");

                public static final DeferredHolder<Item, Item> COPPER_PLATE = zPlates
                                .registerSimpleItem("copper_plate");

                public static final DeferredHolder<Item, Item> GOLD_PLATE = zPlates
                                .registerSimpleItem("gold_plate");

                public static final DeferredHolder<Item, Item> IRON_PLATE = zPlates
                                .registerSimpleItem("iron_plate");

                public static final DeferredHolder<Item, Item> STEEL_PLATE = zPlates
                                .registerSimpleItem("steel_plate");

                public static final DeferredHolder<Item, Item> MOLD_GEAR = zMolds
                                .registerSimpleItem("gear_mold");

                public static final DeferredHolder<Item, Item> MOLD_FOIL = zMolds
                                .registerSimpleItem("foil_mold");

                public static final DeferredHolder<Item, Item> MOLD_INGOT = zMolds
                                .registerSimpleItem("ingot_mold");

                public static final DeferredHolder<Item, Item> MOLD_PLATE = zMolds
                                .registerSimpleItem("plate_mold");

                public static final DeferredHolder<Item, Item> MOLD_BLOCK = zMolds
                                .registerSimpleItem("block_mold");

                public static final DeferredHolder<Item, Item> ANDESITE_CHUNK = zChunks
                                .registerSimpleItem("andesite_chunk");
                public static final DeferredHolder<Item, Item> BLACKSTONE_CHUNK = zChunks
                                .registerSimpleItem("blackstone_chunk");
                public static final DeferredHolder<Item, Item> DEEPSLATE_CHUNK = zChunks
                                .registerSimpleItem("deepslate_chunk");
                public static final DeferredHolder<Item, Item> DIORITE_CHUNK = zChunks
                                .registerSimpleItem("diorite_chunk");
                public static final DeferredHolder<Item, Item> DRIPSTONE_CHUNK = zChunks
                                .registerSimpleItem("dripstone_chunk");
                public static final DeferredHolder<Item, Item> ENDSTONE_CHUNK = zChunks
                                .registerSimpleItem("endstone_chunk");
                public static final DeferredHolder<Item, Item> GRANITE_CHUNK = zChunks
                                .registerSimpleItem("granite_chunk");
                public static final DeferredHolder<Item, Item> TUFF_CHUNK = zChunks
                                .registerSimpleItem("tuff_chunk");

                public static final DeferredHolder<Item, Item> STONE_PEBBLE = zPebbles
                                .registerSimpleItem("stone_pebble");
                public static final DeferredHolder<Item, Item> NETHERRACK_PEBBLE = zPebbles
                                .registerSimpleItem("netherrack_pebble");
                public static final DeferredHolder<Item, Item> ENDSTONE_PEBBLE = zPebbles
                                .registerSimpleItem("endstone_pebble");

                public static final DeferredHolder<Item, Item> BAUXITE = zDeposits
                                .registerSimpleItem("bauxite_deposit");
                public static final DeferredHolder<Item, Item> CYLINDRITE = zDeposits
                                .registerSimpleItem("cylindrite_deposit");
                public static final DeferredHolder<Item, Item> AURICUPRIDE = zDeposits
                                .registerSimpleItem("auricupride_deposit");
                public static final DeferredHolder<Item, Item> PENTLANDITE = zDeposits
                                .registerSimpleItem("pentlandite_deposit");
                public static final DeferredHolder<Item, Item> GALENA = zDeposits
                                .registerSimpleItem("galena_deposit");
                public static final DeferredHolder<Item, Item> OSMIRIDIUM = zDeposits
                                .registerSimpleItem("osmiridium_deposit");
                public static final DeferredHolder<Item, Item> ARGENTITE = zDeposits
                                .registerSimpleItem("argentite_deposit");
                public static final DeferredHolder<Item, Item> CASSITERITE = zDeposits
                                .registerSimpleItem("cassiterite_deposit");
                public static final DeferredHolder<Item, Item> MALACHITE = zDeposits
                                .registerSimpleItem("malachite_deposit");
                public static final DeferredHolder<Item, Item> URANINITE = zDeposits
                                .registerSimpleItem("uraninite_deposit");
                public static final DeferredHolder<Item, Item> KAOLIN = zDeposits
                                .registerSimpleItem("kaolin_deposit");
                public static final DeferredHolder<Item, Item> XENOTHITE = zDeposits
                                .registerSimpleItem("xenothite_deposit");
                public static final DeferredHolder<Item, Item> QUARTZITE = zDeposits
                                .registerSimpleItem("quartzite_deposit");
                public static final DeferredHolder<Item, Item> CHALCOPYRITE = zDeposits
                                .registerSimpleItem("chalcopyrite_deposit");
                public static final DeferredHolder<Item, Item> PYROLITE = zDeposits
                                .registerSimpleItem("pyrolite_deposit");
                public static final DeferredHolder<Item, Item> LIGNITE = zDeposits
                                .registerSimpleItem("lignite_deposit");
                public static final DeferredHolder<Item, Item> HEMATITE = zDeposits
                                .registerSimpleItem("hematite_deposit");

                public static final DeferredHolder<Item, Item> METAL_BOLTS = zSimple
                                .registerSimpleItem("metal_bolts");
                public static final DeferredHolder<Item, Item> RAW_SILICON = zSimple
                                .registerSimpleItem("raw_silicon");
                public static final DeferredHolder<Item, Item> SILICON_GEM = zSimple
                                .registerSimpleItem("silicon_gem");
                public static final DeferredHolder<Item, Item> SILICON_SHARD = zSimple
                                .registerSimpleItem("silicon_shard");

                public static final DeferredHolder<Item, Item> COPPER_FOIL = zFoils
                                .registerSimpleItem("copper_foil");

                public static final DeferredHolder<Item, Item> GOLD_FOIL = zFoils
                                .registerSimpleItem("gold_foil");

                public static final DeferredHolder<Item, Item> IRON_FOIL = zFoils
                                .registerSimpleItem("iron_foil");

                public static final DeferredHolder<Item, Item> ADVANCED_ALLOY_NUGGET = zNuggets
                                .registerSimpleItem("advanced_alloy_nugget");

                public static final DeferredHolder<Item, Item> WROUGHT_IRON_NUGGET = zNuggets
                                .registerSimpleItem("wrought_iron_nugget");

                public static final DeferredHolder<Item, Item> STEEL_NUGGET = zNuggets
                                .registerSimpleItem("steel_nugget");

                public static final DeferredHolder<Item, Item> COPPER_COIL = zCoils
                                .registerSimpleItem("copper_coil");

                public static final DeferredHolder<Item, Item> IRON_COIL = zCoils
                                .registerSimpleItem("iron_coil");

                public static final DeferredHolder<Item, Item> GOLD_COIL = zCoils
                                .registerSimpleItem("gold_coil");

                public static final DeferredHolder<Item, Item> WOODEN_GEAR = zGears
                                .registerSimpleItem("wooden_gear");

                public static final DeferredHolder<Item, Item> COPPER_GEAR = zGears
                                .registerSimpleItem("copper_gear");

                public static final DeferredHolder<Item, Item> GOLD_GEAR = zGears
                                .registerSimpleItem("gold_gear");

                public static final DeferredHolder<Item, Item> IRON_GEAR = zGears
                                .registerSimpleItem("iron_gear");

                public static final DeferredHolder<Item, Item> STEEL_GEAR = zGears
                                .registerSimpleItem("steel_gear");

        }
