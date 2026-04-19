package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.registry.types.zLibItemTags;
import com.devdyna.cakesticklib.setup.registry.types.zLibItems;

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
        tag(zLibItemTags.OXIDIZER).add(zLibItems.REDSTONE_ACID.get());
        tag(zLibItemTags.WAXING).add(zLibItems.HONEY_SOLUTION.get(), Items.HONEYCOMB);
    }

}
