package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.datagen.ModelUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;

public class DataModel extends ModelProvider {

        public DataModel(PackOutput output) {
                super(output, MODULE_ID);
        }

        @Override
        protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

                itemModels.generateFlatItem(zItems.CAKE_STICK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
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

                ModelUtils.itemSubFolder(zItems.zDusts, "item/dust/", "_dust", itemModels);
                ModelUtils.itemSubFolder(zItems.zChunks, "item/chunk/", "_chunk", itemModels);
                ModelUtils.itemSubFolder(zItems.zDeposits, "item/deposit/", "_deposit", itemModels);
                ModelUtils.itemSubFolder(zItems.zIngots, "item/ingot/", "_ingot", itemModels);
                ModelUtils.itemSubFolder(zItems.zMolds, "item/mold/", "_mold", itemModels);
                ModelUtils.itemSubFolder(zItems.zPebbles, "item/pebble/", "_pebble", itemModels);
                ModelUtils.itemSubFolder(zItems.zPlates, "item/plate/", "_plate", itemModels);

                zItems.zUpgrade.getEntries().forEach(i -> itemModels.itemModelOutput.accept(i.get(),
                                ItemModelUtils.plainModel(
                                                ModelTemplates.FLAT_ITEM.create(
                                                                i.get(),
                                                                new TextureMapping().put(TextureSlot.LAYER0,
                                                                                new Material(x
                                                                                                .rl(MODULE_ID, "item/upgrade/"
                                                                                                                + i.getId().getPath()
                                                                                                                                .replace("_upgrade",
                                                                                                                                                "")))),
                                                                itemModels.modelOutput))

                ));

        }

}
