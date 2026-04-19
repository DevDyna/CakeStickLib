package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.setup.registry.zLibrary.*;
import com.mojang.logging.LogUtils;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.core.registries.BuiltInRegistries;
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

        List.of(zItems.AMETHYST_DUST.get(),
                zItems.CARBON_DUST.get(),
                zItems.COPPER_DUST.get(),
                zItems.DIAMOND_DUST.get(),
                zItems.EMERALD_DUST.get(),
                zItems.GOLD_DUST.get(),
                zItems.IRON_DUST.get(),
                zItems.LAPIS_DUST.get(),
                zItems.QUARTZ_DUST.get(),
                zItems.SULFUR_DUST.get()).forEach(i ->

        itemModels.itemModelOutput.accept(i,
                ItemModelUtils.plainModel(
                        ModelTemplates.FLAT_ITEM.create(
                                BuiltInRegistries.ITEM.getKey(i).withPrefix("item/dust/"),
                                TextureMapping.layer0(i),
                                itemModels.modelOutput).withSuffix("dust/"))));

    }

}
