package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.Main.MODULE_ID;

import com.devdyna.cakesticklib.setup.registry.types.zItems;

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
        itemModels.generateFlatItem(zItems.REDSTONE_ACID.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(zItems.HONEY_SOLUTION.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(zItems.CHISEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(zItems.PATINA.get(), ModelTemplates.FLAT_ITEM);

    }

}
