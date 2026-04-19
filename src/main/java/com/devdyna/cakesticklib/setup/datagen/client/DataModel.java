package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;
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

        List.of(zItems.AMETHYST_DUST,
                zItems.CARBON_DUST,
                zItems.COPPER_DUST,
                zItems.DIAMOND_DUST,
                zItems.EMERALD_DUST,
                zItems.GOLD_DUST,
                zItems.IRON_DUST,
                zItems.LAPIS_DUST,
                zItems.QUARTZ_DUST,
                zItems.SULFUR_DUST).forEach(i ->
        itemModels.itemModelOutput.accept(i.get(),
                ItemModelUtils.plainModel(
                        ModelTemplates.FLAT_ITEM.create(
                                i.get(),
                                new TextureMapping()
                                        .put(TextureSlot.LAYER0, new Material(
                                                x.rl(i.getId().getNamespace(),
                                                        i.getId().getPath().replace("_dust", ""))
                                                        .withPrefix("item/dust/"))),
                                itemModels.modelOutput))

        ));

    }

}
