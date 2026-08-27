package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.datagen.RecipeGenerators;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.common.recipes.item_replace.ReplaceItemBuilder;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationBuilder;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.OxidationStatus;
import com.devdyna.cakesticklib.setup.common.recipes.tool_durability.DurabilityConsumeBuilder;
import com.devdyna.cakesticklib.setup.common.recipes.upgrade_application.UpgradeApplicationBuilder;
import com.devdyna.cakesticklib.setup.registry.*;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataRecipe extends RecipeProvider implements RecipeGenerators {

        protected DataRecipe(Provider registries, RecipeOutput output) {
                super(registries, output);
        }

        @Override
        protected void buildRecipes() {

                ShapedRecipeBuilder.shaped(items, RecipeCategory.FOOD, LibItems.CAKE_STICK.get())
                                .pattern(" C")
                                .pattern("S ")
                                .define('C', Items.CAKE)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(Items.CAKE),
                                                has(Items.CAKE))
                                .save(output);

                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.TOOLS, LibItems.REDSTONE_ACID.get())
                                .requires(Items.GLASS_BOTTLE)
                                .requires(Tags.Items.DUSTS_REDSTONE)
                                .requires(LibItems.PATINA.get())
                                .unlockedBy("has_patina", has(LibItems.PATINA.get()))
                                .save(output);

                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.TOOLS, LibItems.HONEY_SOLUTION.get())
                                .requires(Items.GLASS_BOTTLE)
                                .requires(Items.HONEYCOMB)
                                .requires(Items.DANDELION)
                                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
                                .save(output);

                CopperOxidationBuilder.of(registries)
                                .type(OxidationStatus.OXIDIZING)
                                .catalyst(LibTags.Items.OXIDIZER)
                                .unlockedBy()
                                .save(output);

                CopperOxidationBuilder.of(registries)
                                .type(OxidationStatus.WAXING)
                                .catalyst(LibTags.Items.WAXING)
                                .unlockedBy()
                                .save(output);

                CopperOxidationBuilder.of(registries)
                                .type(OxidationStatus.SCRAPPING)
                                .catalyst(ItemTags.AXES)
                                .unlockedBy()
                                .save(output);

                CopperOxidationBuilder.of(registries)
                                .type(OxidationStatus.UNWAXING)
                                .catalyst(ItemTags.AXES)
                                .unlockedBy()
                                .save(output);

                ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, LibItems.CHISEL.get())
                                .pattern("  I")
                                .pattern(" I ")
                                .pattern("S  ")
                                .define('S', Items.STICK)
                                .define('I', Tags.Items.INGOTS_IRON)
                                .unlockedBy(getHasName(Tags.Items.INGOTS_IRON), has(Tags.Items.INGOTS_IRON))
                                .save(output);

                ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, LibItems.GLASS_CUTTER.get())
                                .pattern("  N")
                                .pattern(" S ")
                                .pattern("S  ")
                                .define('N', Tags.Items.NUGGETS_IRON)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(Tags.Items.NUGGETS_IRON), has(Tags.Items.NUGGETS_IRON))
                                .save(output);

                ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, LibItems.WRENCH.get())
                                .pattern(" I ")
                                .pattern(" LI")
                                .pattern("C  ")
                                .define('I', Tags.Items.INGOTS_IRON)
                                .define('C', LibTags.Items.CARBON_DUST)
                                .define('L', Tags.Items.GEMS_LAPIS)
                                .unlockedBy(getHasName(LibTags.Items.CARBON_DUST), has(LibTags.Items.CARBON_DUST))
                                .save(output);

                simpleCooking(output, LibTags.Items.FLOUR, Items.BREAD);

                shaped(RecipeCategory.MISC, Items.PAPER, 6)
                                .define('#', LibTags.Items.SAWDUST)
                                .pattern("###")
                                .unlockedBy("has_sawdust", has(LibTags.Items.SAWDUST))
                                .save(output, MODULE_ID + ":"
                                                + getConversionRecipeName(
                                                                Items.PAPER,
                                                                LibItems.SAWDUST.get()));

                shaped(RecipeCategory.MISC, LibItems.RESISTOR.get(), 4)
                                .pattern(" MN")
                                .pattern("MRM")
                                .pattern("NM ")
                                .define('N', LibTags.Items.WROUGHT_IRON_NUGGET)
                                .define('M', Tags.Items.GEMS_LAPIS)
                                .define('R', Tags.Items.DUSTS_REDSTONE)
                                .unlockedBy(getHasName(LibTags.Items.WROUGHT_IRON_NUGGET),
                                                has(LibTags.Items.WROUGHT_IRON_NUGGET))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.RESISTOR.get(), 8)
                                .pattern(" MN")
                                .pattern("MRM")
                                .pattern("NM ")
                                .define('N', LibTags.Items.ADVANCED_ALLOY_NUGGET)
                                .define('M', Tags.Items.GEMS_LAPIS)
                                .define('R', LibTags.Items.PLASTIC)
                                .unlockedBy(getHasName(LibTags.Items.PLASTIC), has(LibTags.Items.PLASTIC))
                                .save(output, asRecipeID(LibItems.RESISTOR.get()));

                shaped(RecipeCategory.MISC, LibItems.CHIP.get(), 4)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', LibTags.Items.STEEL_PLATE)
                                .define('N', Tags.Items.NUGGETS_IRON)
                                .define('Q', Tags.Items.GEMS_QUARTZ)
                                .unlockedBy(getHasName(LibTags.Items.STEEL_PLATE), has(LibTags.Items.STEEL_PLATE))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.CHIP.get(), 8)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', LibTags.Items.SILICON)
                                .define('N', LibTags.Items.ADVANCED_ALLOY_NUGGET)
                                .define('Q', LibTags.Items.PLASTIC)
                                .unlockedBy(getHasName(LibTags.Items.PLASTIC), has(LibTags.Items.PLASTIC))
                                .save(output, asRecipeID(LibItems.CHIP.get()));

                shaped(RecipeCategory.MISC, LibItems.CONDENSER.get(), 4)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', Tags.Items.NUGGETS_IRON)
                                .define('G', LibTags.Items.STEEL_PLATE)
                                .define('M', LibTags.Items.CARBON_FIBER)
                                .define('I', Tags.Items.INGOTS_IRON)
                                .unlockedBy(getHasName(LibTags.Items.STEEL_PLATE), has(LibTags.Items.STEEL_PLATE))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.CONDENSER.get(), 8)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', LibTags.Items.ADVANCED_ALLOY_NUGGET)
                                .define('G', LibTags.Items.PLASTIC)
                                .define('M', LibItems.SILICON_GEM.get())
                                .define('I', LibTags.Items.ADVANCED_ALLOY_INGOT)
                                .unlockedBy(getHasName(LibTags.Items.ADVANCED_ALLOY_INGOT),
                                                has(LibTags.Items.ADVANCED_ALLOY_INGOT))
                                .save(output, asRecipeID(LibItems.CONDENSER.get()));

                shaped(RecipeCategory.MISC, LibItems.BLUE_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RCR")
                                .pattern(" R ")
                                .define('S', Tags.Items.DUSTS_REDSTONE)
                                .define('R', LibItems.RESISTOR.get())
                                .define('C', LibItems.CONDENSER.get())
                                .unlockedBy(getHasName(LibItems.RESISTOR.get()), has(LibItems.RESISTOR.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.GREEN_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('S', Tags.Items.DUSTS_REDSTONE)
                                .define('R', Tags.Items.SLIME_BALLS)
                                .define('H', LibItems.CONDENSER.get())
                                .unlockedBy(getHasName(LibItems.CONDENSER.get()), has(LibItems.CONDENSER.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.HAMMER.get())
                                .pattern(" IS")
                                .pattern(" TI")
                                .pattern("T  ")
                                .define('S', Tags.Items.STRINGS)
                                .define('T', Items.STICK)
                                .define('I', Tags.Items.INGOTS_IRON)
                                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                                .save(output);

                twoByTwoPacker(output, LibItems.CARBON_FIBER.get(), LibTags.Items.COAL_DUST);
                twoByTwoPacker(output, LibItems.CARBON_PLATE.get(), LibTags.Items.CARBON_FIBER);

                UpgradeApplicationBuilder.of(registries)
                                .energy(-50)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', LibTags.Items.STEEL_PLATE)
                                .define('R', LibItems.RESISTOR.get())
                                .define('B', Tags.Items.DUSTS_REDSTONE)
                                .define('C', LibItems.CHIP.get())
                                .unlockedBy(getHasName(LibTags.Items.STEEL_PLATE), has(LibTags.Items.STEEL_PLATE))
                                .output(LibItems.ENERGY_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of(registries)
                                .energy(125)
                                .speed(20)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', LibTags.Items.STEEL_PLATE)
                                .define('R', LibItems.CONDENSER.get())
                                .define('B', Tags.Items.GEMS_DIAMOND)
                                .define('C', LibItems.CHIP.get())
                                .unlockedBy(getHasName(LibTags.Items.STEEL_PLATE), has(LibTags.Items.STEEL_PLATE))
                                .output(LibItems.SPEED_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of(registries)
                                .energy(150)
                                .luck(15)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', LibTags.Items.STEEL_PLATE)
                                .define('R', LibTags.Items.ELECTRON_TUBES)
                                .define('B', Tags.Items.GEMS_LAPIS)
                                .define('C', LibItems.CHIP.get())
                                .unlockedBy(getHasName(LibTags.Items.STEEL_PLATE), has(LibTags.Items.STEEL_PLATE))
                                .output(LibItems.LUCK_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of(registries)
                                .energy(150)
                                .fluid(-20)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', LibTags.Items.STEEL_PLATE)
                                .define('R', LibItems.METAL_BOLTS.get())
                                .define('B', Tags.Items.DUSTS_GLOWSTONE)
                                .define('C', LibItems.CHIP.get())
                                .unlockedBy(getHasName(LibTags.Items.STEEL_PLATE), has(LibTags.Items.STEEL_PLATE))
                                .output(LibItems.FLUID_UPGRADE.get())
                                .save(output);

                packUnpack(output, LibItems.STONE_PEBBLE.get(), Items.COBBLESTONE, true);
                packUnpack(output, LibItems.NETHERRACK_PEBBLE.get(), Items.NETHERRACK, true);
                simplePacked(output, LibItems.ENDSTONE_PEBBLE.get(), Items.END_STONE, false);

                doubleSmelt(output, LibItems.RAW_SILICON.get(), LibItems.SILICON_GEM.get());

                shaped(RecipeCategory.MISC, LibItems.MIXED_INGOT.get(), 3)
                                .define('T', LibTags.Items.COPPER_PLATE)
                                .define('C', LibTags.Items.GOLD_PLATE)
                                .define('B', LibTags.Items.IRON_PLATE)
                                .pattern("T")
                                .pattern("C")
                                .pattern("B")
                                .unlockedBy(getHasName(LibTags.Items.IRON_PLATE), has(LibTags.Items.IRON_PLATE))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.ELECTRON_TUBE.get())
                                .define('G', LibTags.Items.GLASS_DUST)
                                .define('A', LibTags.Items.ADVANCED_ALLOY_NUGGET)
                                .define('D', LibItems.RESISTOR.get())
                                .define('C', Tags.Items.NUGGETS_COPPER)
                                .pattern(" G ")
                                .pattern("DAD")
                                .pattern(" C ")
                                .unlockedBy(getHasName(LibTags.Items.IRON_PLATE), has(LibTags.Items.IRON_PLATE))
                                .save(output);

                doubleSmelt(output, LibItems.MIXED_INGOT.get(), LibItems.ADVANCED_ALLOY_INGOT.get());

                shapeless(RecipeCategory.MISC, LibItems.WROUGHT_IRON_INGOT.get())
                                .requires(LibTags.Items.CARBON_DUST)
                                .requires(LibTags.Items.CARBON_DUST)
                                .requires(Tags.Items.INGOTS_IRON)
                                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                                .save(output);

                doubleSmelt(output, LibTags.Items.WROUGHT_IRON_INGOT, LibItems.STEEL_INGOT.get());

                packUnpack(output, LibItems.SILICON_SHARD.get(), LibItems.RAW_SILICON.get(), false);

                ingot(Tags.Items.RAW_MATERIALS_IRON,
                                Items.IRON_INGOT, Tags.Items.INGOTS_IRON,
                                LibItems.IRON_DUST.get(), LibTags.Items.IRON_DUST,
                                LibItems.IRON_PLATE.get(), LibTags.Items.IRON_PLATE,
                                LibItems.IRON_GEAR.get(), LibTags.Items.IRON_GEAR,
                                LibItems.IRON_FOIL.get(), LibTags.Items.IRON_FOIL,
                                LibItems.IRON_COIL.get(), LibTags.Items.IRON_COIL);

                ingot(Tags.Items.RAW_MATERIALS_GOLD,
                                Items.GOLD_INGOT, Tags.Items.INGOTS_GOLD,
                                LibItems.GOLD_DUST.get(), LibTags.Items.GOLD_DUST,
                                LibItems.GOLD_PLATE.get(), LibTags.Items.GOLD_PLATE,
                                LibItems.GOLD_GEAR.get(), LibTags.Items.GOLD_GEAR,
                                LibItems.GOLD_FOIL.get(), LibTags.Items.GOLD_FOIL,
                                LibItems.GOLD_COIL.get(), LibTags.Items.GOLD_COIL);

                ingot(Tags.Items.RAW_MATERIALS_COPPER,
                                Items.COPPER_INGOT, Tags.Items.INGOTS_COPPER,
                                LibItems.COPPER_DUST.get(), LibTags.Items.COPPER_DUST,
                                LibItems.COPPER_PLATE.get(), LibTags.Items.COPPER_PLATE,
                                LibItems.COPPER_GEAR.get(), LibTags.Items.COPPER_GEAR,
                                LibItems.COPPER_FOIL.get(), LibTags.Items.COPPER_FOIL,
                                LibItems.COPPER_COIL.get(), LibTags.Items.COPPER_COIL);

                gem(Tags.Items.GEMS_AMETHYST, LibItems.AMETHYST_DUST.get());
                gem(ItemTags.COALS, LibItems.CARBON_DUST.get());
                gem(Tags.Items.GEMS_DIAMOND, LibItems.DIAMOND_DUST.get());
                gem(Tags.Items.GEMS_EMERALD, LibItems.EMERALD_DUST.get());
                gem(Tags.Items.GEMS_LAPIS, LibItems.LAPIS_DUST.get());
                gem(Tags.Items.GEMS_QUARTZ, LibItems.QUARTZ_DUST.get());

                gem(Items.RESIN_BRICK, Items.RESIN_CLUMP);

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.HAMMER.get())
                                .add(Items.WHEAT)
                                .output(LibItems.FLOUR)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.HAMMER.get())
                                .add(ItemTags.LOGS)
                                .output(LibItems.SAWDUST, 4)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.HAMMER.get())
                                .add(Tags.Items.GLASS_BLOCKS)
                                .output(LibItems.GLASS_DUST)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(Items.SHEARS, 4)
                                .add(ItemTags.WOOL)
                                .output(Items.STRING, 4)
                                .unlockedBy()
                                .save(output, "_from_wool");

                DurabilityConsumeBuilder.of(registries)
                                .tool(Items.SHEARS, 3)
                                .add(ItemTags.WOOL_CARPETS)
                                .output(Items.STRING, 2)
                                .unlockedBy()
                                .save(output, "_from_wool_carpets");

                DurabilityConsumeBuilder.of(registries)
                                .tool(Items.SHEARS, 12)
                                .add(Items.DRIED_GHAST)
                                .output(Items.GHAST_TEAR, 4)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.REDSTONE_ACID.get(), 1)
                                .add(ItemTags.CANDLES)
                                .output(Items.CANDLE, 1)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.REDSTONE_ACID.get(), 1)
                                .add(ItemTags.TERRACOTTA)
                                .output(Items.TERRACOTTA, 1)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.REDSTONE_ACID.get(), 1)
                                .add(Tags.Items.GLASS_BLOCKS_CHEAP)
                                .output(Items.GLASS, 1)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.REDSTONE_ACID.get(), 1)
                                .add(Tags.Items.GLASS_PANES)
                                .output(Items.GLASS_PANE, 1)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(ItemTags.SHOVELS, 10)
                                .add(Tags.Items.GRAVELS)
                                .output(Items.FLINT, 1)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(ItemTags.PICKAXES, 10)
                                .add(Items.AMETHYST_BLOCK)
                                .output(Items.AMETHYST_SHARD, 4)
                                .unlockedBy()
                                .save(output);

                Map.ofEntries(
                                Map.entry(Items.OAK_LOG, Items.STRIPPED_OAK_LOG),
                                Map.entry(Items.SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG),
                                Map.entry(Items.BIRCH_LOG, Items.STRIPPED_BIRCH_LOG),
                                Map.entry(Items.JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG),
                                Map.entry(Items.ACACIA_LOG, Items.STRIPPED_ACACIA_LOG),
                                Map.entry(Items.DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG),
                                Map.entry(Items.MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG),
                                Map.entry(Items.CHERRY_LOG, Items.STRIPPED_CHERRY_LOG),
                                Map.entry(Items.PALE_OAK_LOG, Items.STRIPPED_PALE_OAK_LOG),
                                Map.entry(Items.BAMBOO_BLOCK, Items.STRIPPED_BAMBOO_BLOCK),

                                Map.entry(Items.OAK_WOOD, Items.STRIPPED_OAK_WOOD),
                                Map.entry(Items.SPRUCE_WOOD, Items.STRIPPED_SPRUCE_WOOD),
                                Map.entry(Items.BIRCH_WOOD, Items.STRIPPED_BIRCH_WOOD),
                                Map.entry(Items.JUNGLE_WOOD, Items.STRIPPED_JUNGLE_WOOD),
                                Map.entry(Items.ACACIA_WOOD, Items.STRIPPED_ACACIA_WOOD),
                                Map.entry(Items.DARK_OAK_WOOD, Items.STRIPPED_DARK_OAK_WOOD),
                                Map.entry(Items.MANGROVE_WOOD, Items.STRIPPED_MANGROVE_WOOD),
                                Map.entry(Items.CHERRY_WOOD, Items.STRIPPED_CHERRY_WOOD),
                                Map.entry(Items.PALE_OAK_WOOD, Items.STRIPPED_PALE_OAK_WOOD),

                                Map.entry(Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM),
                                Map.entry(Items.CRIMSON_HYPHAE, Items.STRIPPED_CRIMSON_HYPHAE),
                                Map.entry(Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM),
                                Map.entry(Items.WARPED_HYPHAE, Items.STRIPPED_WARPED_HYPHAE))
                                .forEach((log, stripped) -> DurabilityConsumeBuilder.of(registries)
                                                .tool(ItemTags.AXES, 1)
                                                .add(log)
                                                .output(stripped)
                                                .unlockedBy()
                                                .save(output));

                DurabilityConsumeBuilder.of(registries)
                                .tool(ItemTags.HOES, 1)
                                .add(LibTags.Items.TURN_INTO_FARMLAND)
                                .output(Items.FARMLAND)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(ItemTags.SHOVELS, 1)
                                .add(LibTags.Items.TURN_INTO_DIRT_PATH)
                                .output(Items.DIRT_PATH)
                                .unlockedBy()
                                .save(output);

                DurabilityConsumeBuilder.of(registries)
                                .tool(ItemTags.HOES, 1)
                                .add(LibTags.Items.TURN_INTO_DIRT)
                                .output(Items.DIRT)
                                .unlockedBy()
                                .save(output);

                ReplaceItemBuilder.of(registries)
                                .replace(Items.JACK_O_LANTERN, Items.TORCH)
                                .output(Items.CARVED_PUMPKIN)
                                .unlockedBy()
                                .save(output);

                ReplaceItemBuilder.of(registries)
                                .replace(Items.CAMPFIRE, Items.CHARCOAL)
                                .add(ItemTags.SOUL_FIRE_BASE_BLOCKS)
                                .output(Items.SOUL_CAMPFIRE)
                                .unlockedBy()
                                .save(output);

                ReplaceItemBuilder.of(registries)
                                .replace(Items.SOUL_CAMPFIRE, Items.SOUL_SOIL)
                                .add(ItemTags.COALS)
                                .output(Items.CAMPFIRE)
                                .unlockedBy()
                                .save(output);

                Map.of(
                                Items.OAK_CHEST_BOAT, Items.OAK_BOAT,
                                Items.BIRCH_CHEST_BOAT, Items.BIRCH_BOAT,
                                Items.SPRUCE_CHEST_BOAT, Items.SPRUCE_BOAT,
                                Items.JUNGLE_CHEST_BOAT, Items.JUNGLE_BOAT,
                                Items.ACACIA_CHEST_BOAT, Items.ACACIA_BOAT,
                                Items.DARK_OAK_CHEST_BOAT, Items.DARK_OAK_BOAT,
                                Items.MANGROVE_CHEST_BOAT, Items.MANGROVE_BOAT,
                                Items.CHERRY_CHEST_BOAT, Items.CHERRY_BOAT,
                                Items.BAMBOO_CHEST_RAFT, Items.BAMBOO_RAFT,
                                Items.PALE_OAK_CHEST_BOAT, Items.PALE_OAK_BOAT)
                                .forEach((chest, boat) -> ReplaceItemBuilder.of(registries)
                                                .replace(chest, boat)
                                                .output(Items.CHEST)
                                                .unlockedBy()
                                                .save(output, "_from_" + x.name(chest)));

                Map.of(
                                Items.CHEST_MINECART, Items.CHEST,
                                Items.TNT_MINECART, Items.TNT,
                                Items.FURNACE_MINECART, Items.FURNACE,
                                Items.HOPPER_MINECART, Items.HOPPER)
                                .forEach((cart, result) -> ReplaceItemBuilder.of(registries)
                                                .replace(cart, Items.MINECART)
                                                .output(result)
                                                .unlockedBy()
                                                .save(output));

                List.of(
                                Items.ANGLER_POTTERY_SHERD,
                                Items.ARCHER_POTTERY_SHERD,
                                Items.ARMS_UP_POTTERY_SHERD,
                                Items.BLADE_POTTERY_SHERD,
                                Items.BREWER_POTTERY_SHERD,
                                Items.BURN_POTTERY_SHERD,
                                Items.DANGER_POTTERY_SHERD,
                                Items.EXPLORER_POTTERY_SHERD,
                                Items.FLOW_POTTERY_SHERD,
                                Items.FRIEND_POTTERY_SHERD,
                                Items.GUSTER_POTTERY_SHERD,
                                Items.HEART_POTTERY_SHERD,
                                Items.HEARTBREAK_POTTERY_SHERD,
                                Items.HOWL_POTTERY_SHERD,
                                Items.MINER_POTTERY_SHERD,
                                Items.MOURNER_POTTERY_SHERD,
                                Items.PLENTY_POTTERY_SHERD,
                                Items.PRIZE_POTTERY_SHERD,
                                Items.SCRAPE_POTTERY_SHERD,
                                Items.SHEAF_POTTERY_SHERD,
                                Items.SHELTER_POTTERY_SHERD,
                                Items.SKULL_POTTERY_SHERD,
                                Items.SNORT_POTTERY_SHERD)
                                .forEach(sherd -> ReplaceItemBuilder.of(registries)
                                                .replace(sherd, sherd)
                                                .add(Items.BRICK)
                                                .add(LibItems.PATINA)
                                                .output(sherd)
                                                .unlockedBy()
                                                .save(output));

                Map.of(
                                Items.COD_BUCKET, Items.COD,
                                Items.SALMON_BUCKET, Items.SALMON,
                                Items.PUFFERFISH_BUCKET, Items.PUFFERFISH,
                                Items.TROPICAL_FISH_BUCKET, Items.TROPICAL_FISH)
                                .forEach((bucket, fish) -> ReplaceItemBuilder.of(registries)
                                                .replace(bucket, Items.WATER_BUCKET)
                                                .output(fish)
                                                .unlockedBy()
                                                .save(output));

                ReplaceItemBuilder.of(registries)
                                .replace(Items.GLOW_ITEM_FRAME, Items.GLOW_INK_SAC)
                                .output(Items.ITEM_FRAME)
                                .unlockedBy()
                                .save(output);

                // shapeless(RecipeCategory.MISC, Items.BOOK)
                //                 .requires(LibTags.Items.WRITABLE_BOOKS)
                //                 .unlockedBy(getHasName(LibTags.Items.WRITABLE_BOOKS), has(LibTags.Items.WRITABLE_BOOKS))
                //                 .save(output);

                shapeless(RecipeCategory.MISC, Items.CLAY_BALL, 4)
                                .requires(Items.CLAY)
                                .unlockedBy(getHasName(Items.CLAY), has(Items.CLAY))
                                .save(output);

                plate(output, LibTags.Items.STEEL_INGOT, LibItems.STEEL_PLATE.get());
                plate(output, LibTags.Items.WROUGHT_IRON_INGOT, LibItems.WROUGHT_IRON_PLATE.get());
                plate(output, LibTags.Items.ADVANCED_ALLOY_INGOT, LibItems.ADVANCED_ALLOY_PLATE.get());

                nuggetIngotBlock(output, LibItems.STEEL_NUGGET.get(), LibItems.STEEL_INGOT.get(),
                                LibBlocks.STEEL_BLOCK.get());
                nuggetIngotBlock(output, LibItems.WROUGHT_IRON_NUGGET.get(), LibItems.WROUGHT_IRON_INGOT.get(),
                                LibBlocks.WROUGHT_IRON_BLOCK.get());
                nuggetIngotBlock(output, LibItems.ADVANCED_ALLOY_NUGGET.get(), LibItems.ADVANCED_ALLOY_INGOT.get(),
                                LibBlocks.ADVANCED_ALLOY_BLOCK.get());

                gear(output, LibTags.Items.STEEL_INGOT, LibItems.STEEL_GEAR.get());
                gear(output, ItemTags.PLANKS, LibItems.WOODEN_GEAR.get());

                twoByTwoPacker(output, LibItems.BLANK_MOLD.get(), LibTags.Items.STEEL_PLATE);

                twoByTwoPacker(output, LibItems.METAL_BOLTS.get(), LibTags.Items.METAL_NUGGETS);

                LibItems.zMolds.getEntries().stream().map(DeferredHolder::get)
                                .forEach(i -> {
                                        stonecutter(output, i, LibItems.BLANK_MOLD.get());
                                        stonecutter(output, i, LibTags.Items.MOLDS);
                                });

        }

        public static final class RecipeRunner extends RecipeProvider.Runner {
                public RecipeRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
                        super(output, lookupProvider);
                }

                @Override
                protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(
                                HolderLookup.Provider lookupProvider,
                                RecipeOutput output) {
                        return new DataRecipe(lookupProvider, output);
                }

                @Override
                public String getName() {
                        return "CakeStickLib";
                }
        }

        public void ingot(
                        TagKey<Item> raw,
                        Item ingot, TagKey<Item> ingot_tag,
                        Item dust, TagKey<Item> dust_tag,
                        Item plate, TagKey<Item> plate_tag,
                        Item gear, TagKey<Item> gear_tag,
                        Item foil, TagKey<Item> foil_tag,
                        Item coil, TagKey<Item> coil_tag) {

                doubleSmelt(output, dust_tag, ingot);

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.HAMMER.get(), 2)
                                .add(raw)
                                .output(dust, 2)
                                .unlockedBy()
                                .save(output, "_from_raw");

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.HAMMER.get(), 1)
                                .add(ingot_tag)
                                .output(dust)
                                .unlockedBy()
                                .save(output, "_from_ingot");

                gear(output, ingot_tag, gear);
                foil(output, plate_tag, foil);
                plate(output, ingot_tag, plate);
                coil(output, foil_tag, coil);

        }

        public void gem(
                        TagKey<Item> gem,
                        Item dust) {

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.HAMMER.get(), 1)
                                .add(gem)
                                .output(dust)
                                .unlockedBy()
                                .save(output);

        }

        public void gem(
                        Item gem,
                        Item dust) {

                DurabilityConsumeBuilder.of(registries)
                                .tool(LibItems.HAMMER.get(), 1)
                                .add(gem)
                                .output(dust)
                                .unlockedBy()
                                .save(output);

        }

        public String getHasNameGen(ItemLike i) {
                return getHasName(i);
        }

        @Override
        public Provider getProvider() {
                return this.registries;
        }

        @Override
        public HolderGetter<Item> getItems() {
                return this.items;
        }

        @Override
        public String getModName() {
                return MODULE_ID;
        }

}