package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.datagen.RecipeGenerators;
import com.devdyna.cakesticklib.setup.common.recipes.hammering.HammeringRecipeBuilder;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationBuilder;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.OxidationStatus;
import com.devdyna.cakesticklib.setup.common.recipes.upgrade_application.UpgradeApplicationBuilder;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;

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

                ShapedRecipeBuilder.shaped(items, RecipeCategory.FOOD, zItems.CAKE_STICK.get())
                                .pattern(" C")
                                .pattern("S ")
                                .define('C', Items.CAKE)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(Items.CAKE),
                                                has(Items.CAKE))
                                .save(output);

                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.TOOLS, zItems.REDSTONE_ACID.get())
                                .requires(Items.GLASS_BOTTLE)
                                .requires(Items.REDSTONE)
                                .requires(zItems.PATINA.get())
                                .unlockedBy("has_patina", has(zItems.PATINA.get()))
                                .save(output);

                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.TOOLS, zItems.HONEY_SOLUTION.get())
                                .requires(Items.GLASS_BOTTLE)
                                .requires(Items.HONEYCOMB)
                                .requires(Items.DANDELION)
                                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
                                .save(output);

                CopperOxidationBuilder.of()
                                .type(OxidationStatus.OXIDIZING)
                                .catalyst(zItemTags.OXIDIZER, registries)
                                .unlockedBy()
                                .save(output);

                CopperOxidationBuilder.of()
                                .type(OxidationStatus.WAXING)
                                .catalyst(zItemTags.WAXING, registries)
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

                ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, zItems.CHISEL.get())
                                .pattern("  N")
                                .pattern(" I ")
                                .pattern("S  ")
                                .define('N', Items.IRON_NUGGET)
                                .define('S', Items.STICK)
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy("iron", has(Items.IRON_INGOT))
                                .save(output);

                simpleCooking(output, zItems.FLOUR.get(), Items.BREAD);

                shaped(RecipeCategory.MISC, Items.PAPER, 6)
                                .define('#', zItemTags.SAWDUST)
                                .pattern("###")
                                .unlockedBy("has_sawdust", has(zItemTags.SAWDUST))
                                .save(output, MODULE_ID + ":"
                                                + getConversionRecipeName(
                                                                Items.PAPER,
                                                                zItems.SAWDUST.get()));

                shaped(RecipeCategory.MISC, zItems.RESISTOR.get(), 4)
                                .pattern(" MN")
                                .pattern("MRM")
                                .pattern("NM ")
                                .define('N', zItems.WROUGHT_IRON_NUGGET.get())
                                .define('M', Items.LAPIS_LAZULI)
                                .define('R', Items.REDSTONE)
                                .unlockedBy(getHasName(zItems.WROUGHT_IRON_NUGGET.get()),
                                                has(zItems.WROUGHT_IRON_NUGGET.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, zItems.RESISTOR.get(), 8)
                                .pattern(" MN")
                                .pattern("MRM")
                                .pattern("NM ")
                                .define('N', zItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('M', Items.LAPIS_LAZULI)
                                .define('R', zItems.PLASTIC.get())
                                .unlockedBy(getHasName(zItems.PLASTIC.get()), has(zItems.PLASTIC.get()))
                                .save(output, asRecipeID(zItems.RESISTOR.get()));

                shaped(RecipeCategory.MISC, zItems.CHIP.get(), 4)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', zItems.STEEL_PLATE.get())
                                .define('N', Items.IRON_NUGGET)
                                .define('Q', Items.QUARTZ)
                                .unlockedBy(getHasName(zItems.STEEL_PLATE.get()), has(zItems.STEEL_PLATE.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, zItems.CHIP.get(), 8)
                                .pattern(" N ")
                                .pattern(" GN")
                                .pattern("Q  ")
                                .define('G', zItems.SILICON_GEM.get())
                                .define('N', zItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('Q', zItems.PLASTIC.get())
                                .unlockedBy(getHasName(zItems.PLASTIC.get()), has(zItems.PLASTIC.get()))
                                .save(output, asRecipeID(zItems.CHIP.get()));

                shaped(RecipeCategory.MISC, zItems.CONDENSER.get(), 4)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', Items.IRON_NUGGET)
                                .define('G', zItems.STEEL_PLATE.get())
                                .define('M', zItems.CARBON_FIBER.get())
                                .define('I', Items.IRON_INGOT)
                                .unlockedBy(getHasName(zItems.STEEL_PLATE.get()), has(zItems.STEEL_PLATE.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, zItems.CONDENSER.get(), 8)
                                .pattern("N N")
                                .pattern("GMG")
                                .pattern(" I ")
                                .define('N', zItems.ADVANCED_ALLOY_NUGGET.get())
                                .define('G', zItems.PLASTIC.get())
                                .define('M', zItems.SILICON_GEM.get())
                                .define('I', zItems.ADVANCED_ALLOY_INGOT.get())
                                .unlockedBy(getHasName(zItems.ADVANCED_ALLOY_INGOT.get()),
                                                has(zItems.ADVANCED_ALLOY_INGOT.get()))
                                .save(output, asRecipeID(zItems.CONDENSER.get()));

                shaped(RecipeCategory.MISC, zItems.BLUE_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RCR")
                                .pattern(" R ")
                                .define('S', Items.REDSTONE)
                                .define('R', zItems.RESISTOR.get())
                                .define('C', zItems.CONDENSER.get())
                                .unlockedBy(getHasName(zItems.RESISTOR.get()), has(zItems.RESISTOR.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, zItems.GREEN_BATTERY.get())
                                .pattern(" S ")
                                .pattern("RHR")
                                .pattern(" R ")
                                .define('S', Items.REDSTONE)
                                .define('R', Tags.Items.SLIME_BALLS)
                                .define('H', zItems.CONDENSER.get())
                                .unlockedBy(getHasName(zItems.CONDENSER.get()), has(zItems.CONDENSER.get()))
                                .save(output);

                zItems.zUpgrade.getEntries().forEach(i -> shapeless(RecipeCategory.MISC, i.get())
                                .requires(i.get())
                                .unlockedBy(getHasName(i.get()), has(i.get()))
                                .save(output, asRecipeID(i.get(), "_clear_nbt")));

                twoByTwoPacker(output, zItems.CARBON_FIBER.get(), zItemTags.DUST_COAL);
                twoByTwoPacker(RecipeCategory.MISC, zItems.CARBON_PLATE.get(), zItems.CARBON_FIBER.get());

                UpgradeApplicationBuilder.of()
                                .energy(-50)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', zItemTags.PLATE_STEEL, registries)
                                .define('R', zItems.RESISTOR.get())
                                .define('B', Items.REDSTONE)
                                .define('C', zItems.CHIP.get())
                                .unlockedBy(getHasName(zItems.STEEL_PLATE.get()), has(zItemTags.PLATE_STEEL))
                                .output(zItems.ENERGY_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of()
                                .energy(125)
                                .speed(20)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', zItemTags.PLATE_STEEL, registries)
                                .define('R', zItems.CONDENSER.get())
                                .define('B', Items.DIAMOND)
                                .define('C', zItems.CHIP.get())
                                .unlockedBy(getHasName(zItems.STEEL_PLATE.get()), has(zItemTags.PLATE_STEEL))
                                .output(zItems.SPEED_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of()
                                .energy(150)
                                .luck(15)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', zItemTags.PLATE_STEEL, registries)
                                .define('R', zItems.ELECTRON_TUBE.get())
                                .define('B', Items.LAPIS_LAZULI)
                                .define('C', zItems.CHIP.get())
                                .unlockedBy(getHasName(zItems.STEEL_PLATE.get()), has(zItemTags.PLATE_STEEL))
                                .output(zItems.LUCK_UPGRADE.get())
                                .save(output);

                UpgradeApplicationBuilder.of()
                                .energy(150)
                                .fluid(-20)
                                .pattern(" B ")
                                .pattern("CSC")
                                .pattern(" R ")
                                .define('S', zItemTags.PLATE_STEEL, registries)
                                .define('R', zItems.METAL_BOLTS.get())
                                .define('B', Items.GLOWSTONE_DUST)
                                .define('C', zItems.CHIP.get())
                                .unlockedBy(getHasName(zItems.STEEL_PLATE.get()), has(zItemTags.PLATE_STEEL))
                                .output(zItems.FLUID_UPGRADE.get())
                                .save(output);

                packUnpack(output, zItems.STONE_PEBBLE.get(), Items.COBBLESTONE, true);
                packUnpack(output, zItems.NETHERRACK_PEBBLE.get(), Items.NETHERRACK, true);
                simplePacked(output, zItems.ENDSTONE_PEBBLE.get(), Items.END_STONE, false);

                doubleSmelt(output, zItems.RAW_SILICON.get(), zItems.SILICON_GEM.get());

                shaped(RecipeCategory.MISC, zItems.MIXED_INGOT.get(), 3)
                                .define('T', zItemTags.PLATE_COPPER)
                                .define('C', zItemTags.PLATE_GOLD)
                                .define('B', zItemTags.PLATE_IRON)
                                .pattern("T")
                                .pattern("C")
                                .pattern("B")
                                .unlockedBy(getHasName(zItemTags.PLATE_IRON), has(zItemTags.PLATE_IRON))
                                .save(output);

                doubleSmelt(output, zItems.MIXED_INGOT.get(), zItems.ADVANCED_ALLOY_INGOT.get());

                shapeless(RecipeCategory.MISC, zItems.WROUGHT_IRON_INGOT.get())
                                .requires(zItemTags.DUST_COAL)
                                .requires(zItemTags.DUST_COAL)
                                .requires(Tags.Items.INGOTS_IRON)
                                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                                .save(output);

                doubleSmelt(output, zItems.WROUGHT_IRON_INGOT.get(), zItems.STEEL_INGOT.get());

                packUnpack(output, zItems.SILICON_SHARD.get(), zItems.RAW_SILICON.get(), false);

                ingotProcessing(Tags.Items.RAW_MATERIALS_IRON,
                                Items.IRON_INGOT,
                                zItems.IRON_DUST.get(),
                                zItems.IRON_PLATE.get(),
                                zItems.IRON_GEAR.get(),
                                zItems.IRON_FOIL.get(),
                                zItems.IRON_COIL.get());

                ingotProcessing(Tags.Items.RAW_MATERIALS_GOLD,
                                Items.GOLD_INGOT,
                                zItems.GOLD_DUST.get(),
                                zItems.GOLD_PLATE.get(),
                                zItems.GOLD_GEAR.get(),
                                zItems.GOLD_FOIL.get(),
                                zItems.GOLD_COIL.get());

                ingotProcessing(Tags.Items.RAW_MATERIALS_COPPER,
                                Items.COPPER_INGOT,
                                zItems.COPPER_DUST.get(),
                                zItems.COPPER_PLATE.get(),
                                zItems.COPPER_GEAR.get(),
                                zItems.COPPER_FOIL.get(),
                                zItems.COPPER_COIL.get());

                gemProcessing(Tags.Items.GEMS_AMETHYST, zItems.AMETHYST_DUST.get());
                gemProcessing(ItemTags.COALS, zItems.CARBON_DUST.get());
                gemProcessing(Tags.Items.GEMS_DIAMOND, zItems.DIAMOND_DUST.get());
                gemProcessing(Tags.Items.GEMS_EMERALD, zItems.EMERALD_DUST.get());
                gemProcessing(Tags.Items.GEMS_LAPIS, zItems.LAPIS_DUST.get());
                gemProcessing(Tags.Items.GEMS_QUARTZ, zItems.QUARTZ_DUST.get());

                HammeringRecipeBuilder.of()
                                .tool(zItems.HAMMER.get())
                                .add(Items.WHEAT)
                                .output(zItems.FLOUR)
                                .unlockedBy()
                                .save(output);

                HammeringRecipeBuilder.of()
                                .tool(zItems.HAMMER.get())
                                .add(ItemTags.LOGS, registries)
                                .output(zItems.SAWDUST, 4)
                                .unlockedBy()
                                .save(output);

                plate(output, zItems.STEEL_INGOT.get(), zItems.STEEL_PLATE.get());
                plate(output, zItems.WROUGHT_IRON_INGOT.get(), zItems.WROUGHT_IRON_PLATE.get());
                plate(output, zItems.ADVANCED_ALLOY_INGOT.get(), zItems.ADVANCED_ALLOY_PLATE.get());

                nuggetIngotBlock(output, zItems.STEEL_NUGGET.get(), zItems.STEEL_INGOT.get(),
                                zBlocks.STEEL_BLOCK.get());
                nuggetIngotBlock(output, zItems.WROUGHT_IRON_NUGGET.get(), zItems.WROUGHT_IRON_INGOT.get(),
                                zBlocks.WROUGHT_IRON_BLOCK.get());
                nuggetIngotBlock(output, zItems.ADVANCED_ALLOY_NUGGET.get(), zItems.ADVANCED_ALLOY_INGOT.get(),
                                zBlocks.ADVANCED_ALLOY_BLOCK.get());

                gear(output, zItems.STEEL_GEAR.get(), zItemTags.INGOT_STEEL);
                gear(output, zItems.WOODEN_GEAR.get(), ItemTags.PLANKS);

                twoByTwoPacker(output, zItems.BLANK_MOLD.get(), zItemTags.PLATE_STEEL);

                twoByTwoPacker(output, zItems.METAL_BOLTS.get(), zItemTags.METAL_NUGGETS);

                zItems.zMolds.getEntries().stream().map(DeferredHolder::get)
                                .forEach(i -> {
                                        stonecutter(output, i, zItems.BLANK_MOLD.get());
                                        stonecutter(output, i, zItemTags.MOLDS);
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
                                .tool(zItems.HAMMER.get(), 2)
                                .add(raw, registries)
                                .output(dust, 2)
                                .unlockedBy()
                                .save(output, "_from_raw");

                HammeringRecipeBuilder.of()
                                .tool(zItems.HAMMER.get(), 1)
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
                                .tool(zItems.HAMMER.get(), 1)
                                .add(gem, registries)
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