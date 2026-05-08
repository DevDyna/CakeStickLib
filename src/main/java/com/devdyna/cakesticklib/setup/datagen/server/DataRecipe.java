package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.datagen.RecipeGenerators;
import com.devdyna.cakesticklib.setup.common.recipes.hammering.HammeringRecipeBuilder;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationBuilder;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.OxidationStatus;
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
                                .requires(Items.REDSTONE)
                                .requires(LibItems.PATINA.get())
                                .unlockedBy("has_patina", has(LibItems.PATINA.get()))
                                .save(output);

                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.TOOLS, LibItems.HONEY_SOLUTION.get())
                                .requires(Items.GLASS_BOTTLE)
                                .requires(Items.HONEYCOMB)
                                .requires(Items.DANDELION)
                                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
                                .save(output);

                CopperOxidationBuilder.of()
                                .type(OxidationStatus.OXIDIZING)
                                .catalyst(LibTags.Items.OXIDIZER, registries)
                                .unlockedBy()
                                .save(output);

                CopperOxidationBuilder.of()
                                .type(OxidationStatus.WAXING)
                                .catalyst(LibTags.Items.WAXING, registries)
                                .unlockedBy()
                                .save(output);

                CopperOxidationBuilder.of()
                                .type(OxidationStatus.SCRAPPING)
                                .catalyst(ItemTags.AXES, registries)
                                .unlockedBy()
                                .save(output);

                CopperOxidationBuilder.of()
                                .type(OxidationStatus.UNWAXING)
                                .catalyst(ItemTags.AXES, registries)
                                .unlockedBy()
                                .save(output);

                ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, LibItems.CHISEL.get())
                                .pattern("  N")
                                .pattern(" I ")
                                .pattern("S  ")
                                .define('N', Items.IRON_NUGGET)
                                .define('S', Items.STICK)
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy("iron", has(Items.IRON_INGOT))
                                .save(output);

                simpleCooking(output, LibItems.FLOUR.get(), Items.BREAD);

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
                                .define('N', LibItems.WROUGHT_IRON_NUGGET.get())
                                .define('M', Items.LAPIS_LAZULI)
                                .define('R', Items.REDSTONE)
                                .unlockedBy(getHasName(LibItems.WROUGHT_IRON_NUGGET.get()),
                                                has(LibItems.WROUGHT_IRON_NUGGET.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.RESISTOR.get(), 8)
                                .pattern(" MN")
                                .pattern("MRM")
                                .pattern("NM ")
                                .define('N', LibItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('M', Items.LAPIS_LAZULI)
                                .define('R', LibItems.PLASTIC.get())
                                .unlockedBy(getHasName(LibItems.PLASTIC.get()), has(LibItems.PLASTIC.get()))
                                .save(output, asRecipeID(LibItems.RESISTOR.get()));

                shaped(RecipeCategory.MISC, LibItems.CHIP.get(), 4)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', LibItems.STEEL_PLATE.get())
                                .define('N', Items.IRON_NUGGET)
                                .define('Q', Items.QUARTZ)
                                .unlockedBy(getHasName(LibItems.STEEL_PLATE.get()), has(LibItems.STEEL_PLATE.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.CHIP.get(), 8)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', LibItems.SILICON_GEM.get())
                                .define('N', LibItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('Q', LibItems.PLASTIC.get())
                                .unlockedBy(getHasName(LibItems.PLASTIC.get()), has(LibItems.PLASTIC.get()))
                                .save(output, asRecipeID(LibItems.CHIP.get()));

                shaped(RecipeCategory.MISC, LibItems.CONDENSER.get(), 4)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', Items.IRON_NUGGET)
                                .define('G', LibItems.STEEL_PLATE.get())
                                .define('M', LibItems.CARBON_FIBER.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(getHasName(LibItems.STEEL_PLATE.get()), has(LibItems.STEEL_PLATE.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.CONDENSER.get(), 8)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', LibItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('G', LibItems.PLASTIC.get())
                                .define('M', LibItems.SILICON_GEM.get())
                                .define('I', LibItems.ADVANCED_ALLOY_INGOT.get())
                                .unlockedBy(getHasName(LibItems.ADVANCED_ALLOY_INGOT.get()),
                                                has(LibItems.ADVANCED_ALLOY_INGOT.get()))
                                .save(output, asRecipeID(LibItems.CONDENSER.get()));

                shaped(RecipeCategory.MISC, LibItems.BLUE_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RCR")
                                .pattern(" R ")
                                .define('S', Items.REDSTONE)
                                .define('R', LibItems.RESISTOR.get())
                                .define('C', LibItems.CONDENSER.get())
                                .unlockedBy(getHasName(LibItems.RESISTOR.get()), has(LibItems.RESISTOR.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.GREEN_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('S', Items.REDSTONE)
                                .define('R', Tags.Items.SLIME_BALLS)
                                .define('H', LibItems.CONDENSER.get())
                                .unlockedBy(getHasName(LibItems.CONDENSER.get()), has(LibItems.CONDENSER.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.HAMMER.get())
                                .pattern(" IS")
                                .pattern(" TI")
                                .pattern("T  ")
                                .define('S', Items.STRING)
                                .define('T', Items.STICK)
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                                .save(output);

                twoByTwoPacker(output, LibItems.CARBON_FIBER.get(), LibTags.Items.DUST_COAL);
                twoByTwoPacker(RecipeCategory.MISC, LibItems.CARBON_PLATE.get(), LibItems.CARBON_FIBER.get());

                UpgradeApplicationBuilder.of()
                                .energy(-50)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', LibTags.Items.PLATE_STEEL, registries)
                                .define('R', LibItems.RESISTOR.get())
                                .define('B', Items.REDSTONE)
                                .define('C', LibItems.CHIP.get())
                                .unlockedBy(getHasName(LibItems.STEEL_PLATE.get()), has(LibTags.Items.PLATE_STEEL))
                                .output(LibItems.ENERGY_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of()
                                .energy(125)
                                .speed(20)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', LibTags.Items.PLATE_STEEL, registries)
                                .define('R', LibItems.CONDENSER.get())
                                .define('B', Items.DIAMOND)
                                .define('C', LibItems.CHIP.get())
                                .unlockedBy(getHasName(LibItems.STEEL_PLATE.get()), has(LibTags.Items.PLATE_STEEL))
                                .output(LibItems.SPEED_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of()
                                .energy(150)
                                .luck(15)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', LibTags.Items.PLATE_STEEL, registries)
                                .define('R', LibItems.ELECTRON_TUBE.get())
                                .define('B', Items.LAPIS_LAZULI)
                                .define('C', LibItems.CHIP.get())
                                .unlockedBy(getHasName(LibItems.STEEL_PLATE.get()), has(LibTags.Items.PLATE_STEEL))
                                .output(LibItems.LUCK_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of()
                                .energy(150)
                                .fluid(-20)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', LibTags.Items.PLATE_STEEL, registries)
                                .define('R', LibItems.METAL_BOLTS.get())
                                .define('B', Items.GLOWSTONE_DUST)
                                .define('C', LibItems.CHIP.get())
                                .unlockedBy(getHasName(LibItems.STEEL_PLATE.get()), has(LibTags.Items.PLATE_STEEL))
                                .output(LibItems.FLUID_UPGRADE.get())
                                .save(output);

                packUnpack(output, LibItems.STONE_PEBBLE.get(), Items.COBBLESTONE, true);
                packUnpack(output, LibItems.NETHERRACK_PEBBLE.get(), Items.NETHERRACK, true);
                simplePacked(output, LibItems.ENDSTONE_PEBBLE.get(), Items.END_STONE, false);

                doubleSmelt(output, LibItems.RAW_SILICON.get(), LibItems.SILICON_GEM.get());

                shaped(RecipeCategory.MISC, LibItems.MIXED_INGOT.get(), 3)
                                .define('T', LibTags.Items.PLATE_COPPER)
                                .define('C', LibTags.Items.PLATE_GOLD)
                                .define('B', LibTags.Items.PLATE_IRON)
                                .pattern("T")
                                .pattern("C")
                                .pattern("B")
                                .unlockedBy(getHasName(LibTags.Items.PLATE_IRON), has(LibTags.Items.PLATE_IRON))
                                .save(output);

                shaped(RecipeCategory.MISC, LibItems.ELECTRON_TUBE.get())
                                .define('G', LibItems.GLASS_DUST.get())
                                .define('A', LibItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('D', LibItems.RESISTOR.get())
                                .define('C', Tags.Items.NUGGETS_COPPER)
                                .pattern(" G ")
                                .pattern("DAD")
                                .pattern(" C ")
                                .unlockedBy(getHasName(LibTags.Items.PLATE_IRON), has(LibTags.Items.PLATE_IRON))
                                .save(output);

                doubleSmelt(output, LibItems.MIXED_INGOT.get(), LibItems.ADVANCED_ALLOY_INGOT.get());

                shapeless(RecipeCategory.MISC, LibItems.WROUGHT_IRON_INGOT.get())
                                .requires(LibTags.Items.DUST_COAL)
                                .requires(LibTags.Items.DUST_COAL)
                                .requires(Tags.Items.INGOTS_IRON)
                                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                                .save(output);

                doubleSmelt(output, LibItems.WROUGHT_IRON_INGOT.get(), LibItems.STEEL_INGOT.get());

                packUnpack(output, LibItems.SILICON_SHARD.get(), LibItems.RAW_SILICON.get(), false);

                ingotProcessing(Tags.Items.RAW_MATERIALS_IRON,
                                Items.IRON_INGOT,
                                LibItems.IRON_DUST.get(),
                                LibItems.IRON_PLATE.get(),
                                LibItems.IRON_GEAR.get(),
                                LibItems.IRON_FOIL.get(),
                                LibItems.IRON_COIL.get());

                ingotProcessing(Tags.Items.RAW_MATERIALS_GOLD,
                                Items.GOLD_INGOT,
                                LibItems.GOLD_DUST.get(),
                                LibItems.GOLD_PLATE.get(),
                                LibItems.GOLD_GEAR.get(),
                                LibItems.GOLD_FOIL.get(),
                                LibItems.GOLD_COIL.get());

                ingotProcessing(Tags.Items.RAW_MATERIALS_COPPER,
                                Items.COPPER_INGOT,
                                LibItems.COPPER_DUST.get(),
                                LibItems.COPPER_PLATE.get(),
                                LibItems.COPPER_GEAR.get(),
                                LibItems.COPPER_FOIL.get(),
                                LibItems.COPPER_COIL.get());

                gemProcessing(Tags.Items.GEMS_AMETHYST, LibItems.AMETHYST_DUST.get());
                gemProcessing(ItemTags.COALS, LibItems.CARBON_DUST.get());
                gemProcessing(Tags.Items.GEMS_DIAMOND, LibItems.DIAMOND_DUST.get());
                gemProcessing(Tags.Items.GEMS_EMERALD, LibItems.EMERALD_DUST.get());
                gemProcessing(Tags.Items.GEMS_LAPIS, LibItems.LAPIS_DUST.get());
                gemProcessing(Tags.Items.GEMS_QUARTZ, LibItems.QUARTZ_DUST.get());

                gemProcessing(Items.RESIN_BRICK, Items.RESIN_CLUMP);

                HammeringRecipeBuilder.of()
                                .tool(LibItems.HAMMER.get())
                                .add(Items.WHEAT)
                                .output(LibItems.FLOUR)
                                .unlockedBy()
                                .save(output);

                HammeringRecipeBuilder.of()
                                .tool(LibItems.HAMMER.get())
                                .add(ItemTags.LOGS, registries)
                                .output(LibItems.SAWDUST, 4)
                                .unlockedBy()
                                .save(output);

                HammeringRecipeBuilder.of()
                                .tool(LibItems.HAMMER.get())
                                .add(Tags.Items.GLASS_BLOCKS, registries)
                                .output(LibItems.GLASS_DUST)
                                .unlockedBy()
                                .save(output);

                plate(output, LibItems.STEEL_INGOT.get(), LibItems.STEEL_PLATE.get());
                plate(output, LibItems.WROUGHT_IRON_INGOT.get(), LibItems.WROUGHT_IRON_PLATE.get());
                plate(output, LibItems.ADVANCED_ALLOY_INGOT.get(), LibItems.ADVANCED_ALLOY_PLATE.get());

                nuggetIngotBlock(output, LibItems.STEEL_NUGGET.get(), LibItems.STEEL_INGOT.get(),
                                LibBlocks.STEEL_BLOCK.get());
                nuggetIngotBlock(output, LibItems.WROUGHT_IRON_NUGGET.get(), LibItems.WROUGHT_IRON_INGOT.get(),
                                LibBlocks.WROUGHT_IRON_BLOCK.get());
                nuggetIngotBlock(output, LibItems.ADVANCED_ALLOY_NUGGET.get(), LibItems.ADVANCED_ALLOY_INGOT.get(),
                                LibBlocks.ADVANCED_ALLOY_BLOCK.get());

                gear(output, LibItems.STEEL_GEAR.get(), LibTags.Items.INGOT_STEEL);
                gear(output, LibItems.WOODEN_GEAR.get(), ItemTags.PLANKS);

                twoByTwoPacker(output, LibItems.BLANK_MOLD.get(), LibTags.Items.PLATE_STEEL);

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

        public void ingotProcessing(
                        TagKey<Item> raw,
                        Item ingot,
                        Item dust,
                        Item plate,
                        Item gear,
                        Item foil,
                        Item coil) {

                doubleSmelt(output, dust, ingot);

                HammeringRecipeBuilder.of()
                                .tool(LibItems.HAMMER.get(), 2)
                                .add(raw, registries)
                                .output(dust, 2)
                                .unlockedBy()
                                .save(output, "_from_raw");

                HammeringRecipeBuilder.of()
                                .tool(LibItems.HAMMER.get(), 1)
                                .add(ingot)
                                .output(dust)
                                .unlockedBy()
                                .save(output, "_from_ingot");

                gear(output, gear, ingot);
                foil(output, plate, foil);
                plate(output, ingot, plate);
                coil(output, foil, coil);

        }

        public void gemProcessing(
                        TagKey<Item> gem,
                        Item dust) {

                HammeringRecipeBuilder.of()
                                .tool(LibItems.HAMMER.get(), 1)
                                .add(gem, registries)
                                .output(dust)
                                .unlockedBy()
                                .save(output);

        }

        public void gemProcessing(
                        Item gem,
                        Item dust) {

                HammeringRecipeBuilder.of()
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
        public String getItemNameGen(ItemLike i) {
                return getItemName(i);
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
        public String getConversionRecipeNameGen(ItemLike product, ItemLike material) {
                return getConversionRecipeName(product, material);
        }

        @Override
        public String getModName() {
                return MODULE_ID;
        }

}