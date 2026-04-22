package com.devdyna.cakesticklib.api.datagen;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModelUtils {
    public static void itemSubFolder(DeferredRegister.Items items, String prefix, String replacer,
            ItemModelGenerators g) {
        items.getEntries().forEach(i -> g.itemModelOutput.accept(i.get(),
                ItemModelUtils.plainModel(
                        ModelTemplates.FLAT_ITEM.create(
                                i.get(),
                                new TextureMapping()
                                        .put(TextureSlot.LAYER0, new Material(
                                                x.rl(i.getId().getNamespace(),
                                                        i.getId().getPath().replace(replacer, ""))
                                                        .withPrefix(prefix))),
                                g.modelOutput))

        ));
    }
}
