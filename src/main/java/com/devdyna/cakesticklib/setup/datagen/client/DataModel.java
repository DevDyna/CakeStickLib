package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.setup.registry.types.zLibItems;

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

        itemModels.generateFlatItem(zLibItems.CAKE_STICK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(zLibItems.REDSTONE_ACID.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(zLibItems.HONEY_SOLUTION.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(zLibItems.CHISEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(zLibItems.PATINA.get(), ModelTemplates.FLAT_ITEM);

    }

}
