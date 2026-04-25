package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.datagen.ModelUtils;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class DataModel extends ModelProvider {

        public DataModel(PackOutput output) {
                super(output, MODULE_ID);
        }

        @Override
        protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

                itemModels.generateFlatItem(zItems.CAKE_STICK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(zItems.HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(zItems.REDSTONE_ACID.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(zItems.HONEY_SOLUTION.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(zItems.CHISEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(zItems.PATINA.get(), ModelTemplates.FLAT_ITEM);

                itemModels.generateFlatItem(zItems.SAWDUST.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.FLOUR.get(), ModelTemplates.FLAT_ITEM);

                itemModels.generateFlatItem(zItems.BLANK_MOLD.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.CARBON_FIBER.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.CHIP.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.CONDENSER.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.BLUE_BATTERY.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.GREEN_BATTERY.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.ELECTRON_TUBE.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.FOSSIL.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.PLASTIC.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.RESISTOR.get(), ModelTemplates.FLAT_ITEM);

                itemModels.generateFlatItem(zItems.METAL_BOLTS.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.RAW_SILICON.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.SILICON_GEM.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zItems.SILICON_SHARD.get(), ModelTemplates.FLAT_ITEM);

                ModelUtils.itemSubFolder(zItems.zDusts, "item/dust/", "_dust", itemModels);
                ModelUtils.itemSubFolder(zItems.zChunks, "item/chunk/", "_chunk", itemModels);
                ModelUtils.itemSubFolder(zItems.zDeposits, "item/deposit/", "_deposit", itemModels);
                ModelUtils.itemSubFolder(zItems.zIngots, "item/ingot/", "_ingot", itemModels);
                ModelUtils.itemSubFolder(zItems.zMolds, "item/mold/", "_mold", itemModels);
                ModelUtils.itemSubFolder(zItems.zPebbles, "item/pebble/", "_pebble", itemModels);
                ModelUtils.itemSubFolder(zItems.zPlates, "item/plate/", "_plate", itemModels);

                ModelUtils.itemSubFolder(zItems.zCoils, "item/coil/", "_coil", itemModels);
                ModelUtils.itemSubFolder(zItems.zFoils, "item/foil/", "_foil", itemModels);
                ModelUtils.itemSubFolder(zItems.zGears, "item/gear/", "_gear", itemModels);
                ModelUtils.itemSubFolder(zItems.zNuggets, "item/nugget/", "_nugget", itemModels);

                ModelUtils.itemSubFolder(zItems.zUpgrade, "item/upgrade/", "_upgrade", itemModels);

                zBlocks.zBlockItem.getEntries().forEach(b -> blockModels.createTrivialCube(b.get()));

        }

}
