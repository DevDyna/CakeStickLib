package com.devdyna.cakesticklib.setup.registry;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.function.Function;
import java.util.function.Supplier;

import com.devdyna.cakesticklib.CakeStickLib;
import com.devdyna.cakesticklib.api.FluidStorageTank;
import com.devdyna.cakesticklib.api.RegistryUtils;
import com.devdyna.cakesticklib.api.aspect.logic.*;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.setup.RecipeRegister;
import com.devdyna.cakesticklib.setup.common.recipes.hammering.HammeringRecipe;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationRecipe;
import com.devdyna.cakesticklib.setup.common.recipes.upgrade_application.UpgradeApplication;
import com.devdyna.cakesticklib.setup.registry.builders.*;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class zLibrary {

        /**
         * DON'T OVERRIDE OR IT WILL LOSE ANY RECIPE TYPES AND HANDLERS!
         */
        public static void register(IEventBus bus) {
                zHandlers.register(bus);
                zItems.register(bus);
                zComponents.register(bus);
                zRecipeTypes.register(bus);
                zItemTags.register(bus);
                zCreativeTabs.register(bus);
                zBlocks.register(bus);
                zBlockTags.register(bus);
        }

        public class zComponents {
                public static void register(IEventBus bus) {
                        zComponents.register(bus);

                }

                public static final DeferredRegister<DataComponentType<?>> zComponents = DeferredRegister
                                .createDataComponents(Registries.DATA_COMPONENT_TYPE, MODULE_ID);

                public static final DeferredHolder<DataComponentType<?>, DataComponentType<UpgradeComponents>> UPGRADE_COMPONENTS = zComponents
                                .register("upgrade_components",
                                                () -> DataComponentType.<UpgradeComponents>builder()
                                                                .persistent(UpgradeComponents.CODEC)
                                                                .networkSynchronized(UpgradeComponents.STREAM_CODEC)
                                                                .build());

                public static final DeferredHolder<DataComponentType<?>, DataComponentType<Identifier>> IDENTIFIER = zComponents
                                .register("identifier",
                                                () -> DataComponentType.<Identifier>builder()
                                                                .persistent(Identifier.CODEC)
                                                                .networkSynchronized(Identifier.STREAM_CODEC)
                                                                .build());

        }

        public class zHandlers {
                public static void register(IEventBus bus) {
                        zHandler.register(bus);
                }

                public static final DeferredRegister<AttachmentType<?>> zHandler = DeferredRegister.create(
                                Keys.ATTACHMENT_TYPES,
                                MODULE_ID);

                public static final Supplier<AttachmentType<ItemStacksResourceHandler>> ITEM_STORAGE = zHandler
                                .register(
                                                "item_storage", () -> AttachmentType.serializable(h -> {
                                                        if (h instanceof ItemStorageBlock be)
                                                                return new ItemStacksResourceHandler(be.getSlots());
                                                        if (h instanceof MachineItemAutomation be)
                                                                return be.getAutomationItemStorage();
                                                        return null;
                                                }).build());

                public static final Supplier<AttachmentType<FluidStorageTank>> FLUID_STORAGE = zHandler.register(
                                "fluid_storage", () -> AttachmentType.serializable(h -> {
                                        // if (h instanceof MachineFluidAutomation be)
                                        // return new FluidStorageTank((BlockEntity) be, be.getTanks(),
                                        // be.getTankCapacity());
                                        if (h instanceof SimpleFluidStorage be)
                                                return new FluidStorageTank((BlockEntity) be, be.getTanks(),
                                                                be.getTankCapacity());
                                        return null;
                                }).build());

                public static final Supplier<AttachmentType<SimpleEnergyHandler>> ENERGY_STORAGE = zHandler.register(
                                "energy_storage", () -> AttachmentType.serializable(h -> {
                                        if (h instanceof EnergyBlock be)
                                                return new SimpleEnergyHandler(be.getMaxEnergy(),
                                                                be.getMaxInsertEnergy(),
                                                                be.getMaxExtractEnergy(), be.getBaseEnergyStored());
                                        return null;
                                }).build());

        }

        public class zItems {
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
                public static final DeferredHolder<Item, Item> HAMMER = zSimple.registerSimpleItem("hammer",
                                p -> p.durability(1024)
                                                .pickaxe(ToolMaterial.IRON, 6.0F, -3.1F)
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

        public class zItemTags {

                public static void register(IEventBus bus) {
                }

                @Deprecated
                public static final TagKey<Item> COAL_LIKE = RegistryUtils.tagItem("c", "coal_like");

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

                public static final TagKey<Item> OXIDIZER = RegistryUtils.tagItem(MODULE_ID, "oxidizer");
                public static final TagKey<Item> WAXING = RegistryUtils.tagItem(MODULE_ID, "waxing");

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
                                .tagItem(MODULE_ID, "upgrades");

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

        public class zRecipeTypes {
                public static void register(IEventBus bus) {
                        SERIALIZERS.register(bus);
                        TYPES.register(bus);
                }

                public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
                                .create(Registries.RECIPE_SERIALIZER, MODULE_ID);
                public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(
                                Registries.RECIPE_TYPE,
                                MODULE_ID);

                public static final RecipeRegister<CopperOxidationRecipe> COPPER_OXIDATION = RecipeRegister.of(
                                "copper_oxidation",
                                () -> CopperOxidationRecipe.serializer());

                public static final RecipeRegister<HammeringRecipe> HAMMERING = RecipeRegister.of(
                                "hammering",
                                () -> HammeringRecipe.serializer());

                public static final RecipeRegister<UpgradeApplication> UPGRADE_APPLICATION = RecipeRegister.of(
                                "upgrade_application",
                                () -> UpgradeApplication.serializer());

        }

        public class zBlocks {
                public static void register(IEventBus bus) {
                        zBlockItem.register(bus);
                }

                public static final DeferredRegister.Blocks zBlockItem = DeferredRegister
                                .createBlocks(CakeStickLib.MODULE_ID);

                public static final DeferredHolder<Block, Block> WROUGHT_IRON_BLOCK = registerItemBlock(
                                "wrought_iron_block",
                                p -> p.sound(SoundType.METAL)
                                                .strength(2.5f)
                                                .mapColor(MapColor.RAW_IRON));

                public static final DeferredHolder<Block, Block> ADVANCED_ALLOY_BLOCK = registerItemBlock(
                                "advanced_alloy_block",
                                p -> p.sound(SoundType.METAL)
                                                .strength(2f)
                                                .mapColor(MapColor.METAL));

                public static final DeferredHolder<Block, Block> STEEL_BLOCK = registerItemBlock(
                                "steel_block",
                                p -> p.sound(SoundType.METAL)
                                                .strength(2f)
                                                .mapColor(MapColor.METAL));

        }

        public class zBlockTags {
                public static void register(IEventBus bus) {
                }

                public static final TagKey<Block> BLOCK_STEEL = RegistryUtils
                                .tagBlock("c", "storage_blocks/steel");

                public static final TagKey<Block> BLOCK_ADVANCED_ALLOY = RegistryUtils
                                .tagBlock("c", "storage_blocks/advanced_alloy");

                public static final TagKey<Block> BLOCK_WROUGHT_IRON = RegistryUtils
                                .tagBlock("c", "storage_blocks/wrought_iron");
        }

        public class zCreativeTabs {
                public static void register(IEventBus bus) {
                        zCreative.register(bus);
                }

                public static final DeferredRegister<CreativeModeTab> zCreative = DeferredRegister
                                .create(Registries.CREATIVE_MODE_TAB, MODULE_ID);

                public static final DeferredHolder<CreativeModeTab, CreativeModeTab> INGREDIENTS = RegistryUtils
                                .createCreativeTab(MODULE_ID, "resources", () -> zItems.CHIP.get(),
                                                zCreativeTabs.zCreative);

        }

        private static DeferredHolder<Block, Block> registerItemBlock(String blockname,
                        Function<Properties, Properties> sup) {
                DeferredHolder<Block, Block> block = zBlocks.zBlockItem.registerBlock(blockname,
                                p -> new Block(sup.apply(p)));
                zItems.zBlockItem.registerSimpleBlockItem(block);
                return block;
        }
}
