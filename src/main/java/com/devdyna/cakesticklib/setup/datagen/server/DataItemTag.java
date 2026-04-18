package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.registry.types.zItemTags;
import com.devdyna.cakesticklib.setup.registry.types.zItems;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class DataItemTag extends ItemTagsProvider {

    public DataItemTag(PackOutput output, CompletableFuture<Provider> lookupProvider) {
        super(output, lookupProvider, MODULE_ID);
    }

    @Override
    protected void addTags(Provider p) {
        tag(zItemTags.OXIDIZER).add(zItems.REDSTONE_ACID.get());
        tag(zItemTags.WAXING).add(zItems.HONEY_SOLUTION.get(), Items.HONEYCOMB);
    }

}
