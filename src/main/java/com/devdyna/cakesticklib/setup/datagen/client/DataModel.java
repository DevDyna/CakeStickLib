package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.datagen.ModelUtils;
import com.devdyna.cakesticklib.setup.registry.*;
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

                itemModels.generateFlatItem(LibItems.CAKE_STICK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(LibItems.HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(LibItems.REDSTONE_ACID.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(LibItems.HONEY_SOLUTION.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(LibItems.CHISEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModels.generateFlatItem(LibItems.PATINA.get(), ModelTemplates.FLAT_ITEM);

                itemModels.generateFlatItem(LibItems.SAWDUST.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.FLOUR.get(), ModelTemplates.FLAT_ITEM);

                itemModels.generateFlatItem(LibItems.BLANK_MOLD.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.CARBON_FIBER.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.CHIP.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.CONDENSER.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.BLUE_BATTERY.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.GREEN_BATTERY.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.ELECTRON_TUBE.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.FOSSIL.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.PLASTIC.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.RESISTOR.get(), ModelTemplates.FLAT_ITEM);

                itemModels.generateFlatItem(LibItems.METAL_BOLTS.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.RAW_SILICON.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.SILICON_GEM.get(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(LibItems.SILICON_SHARD.get(), ModelTemplates.FLAT_ITEM);

                ModelUtils.itemSubFolder(LibItems.zDusts, "item/dust/", "_dust", itemModels);
                ModelUtils.itemSubFolder(LibItems.zChunks, "item/chunk/", "_chunk", itemModels);
                ModelUtils.itemSubFolder(LibItems.zDeposits, "item/deposit/", "_deposit", itemModels);
                ModelUtils.itemSubFolder(LibItems.zIngots, "item/ingot/", "_ingot", itemModels);
                ModelUtils.itemSubFolder(LibItems.zMolds, "item/mold/", "_mold", itemModels);
                ModelUtils.itemSubFolder(LibItems.zPebbles, "item/pebble/", "_pebble", itemModels);
                ModelUtils.itemSubFolder(LibItems.zPlates, "item/plate/", "_plate", itemModels);

                ModelUtils.itemSubFolder(LibItems.zCoils, "item/coil/", "_coil", itemModels);
                ModelUtils.itemSubFolder(LibItems.zFoils, "item/foil/", "_foil", itemModels);
                ModelUtils.itemSubFolder(LibItems.zGears, "item/gear/", "_gear", itemModels);
                ModelUtils.itemSubFolder(LibItems.zNuggets, "item/nugget/", "_nugget", itemModels);

                ModelUtils.itemSubFolder(LibItems.zUpgrade, "item/upgrade/", "_upgrade", itemModels);

                LibBlocks.zBlockItem.getEntries().forEach(b -> blockModels.createTrivialCube(b.get()));

        }

}
