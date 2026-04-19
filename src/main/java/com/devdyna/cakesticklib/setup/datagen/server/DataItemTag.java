package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.registry.zLibrary.*;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class DataItemTag extends ItemTagsProvider {

    public DataItemTag(PackOutput output, CompletableFuture<Provider> lookupProvider) {
        super(output, lookupProvider, MODULE_ID);
    }

    @Override
    protected void addTags(Provider p) {

        tag(zItemTags.OXIDIZER)
                .add(zItems.REDSTONE_ACID.get());

        tag(zItemTags.WAXING)
                .add(zItems.HONEY_SOLUTION.get(), Items.HONEYCOMB);

        tag(zItemTags.COAL_LIKE).add(Items.COAL, Items.CHARCOAL);

        tag(Tags.Items.DUSTS).add(
                zItems.AMETHYST_DUST.get(),
                zItems.CARBON_DUST.get(),
                zItems.COPPER_DUST.get(),
                zItems.DIAMOND_DUST.get(),
                zItems.EMERALD_DUST.get(),
                zItems.GOLD_DUST.get(),
                zItems.IRON_DUST.get(),
                zItems.LAPIS_DUST.get(),
                zItems.QUARTZ_DUST.get(),
                zItems.SAWDUST.get(),
                zItems.SULFUR_DUST.get());

        tag(zItemTags.DUST_AMETHYST).add(zItems.AMETHYST_DUST.get());

        tag(zItemTags.DUST_COAL)
                .add(zItems.CARBON_DUST.get());

        tag(zItemTags.DUST_COPPER)
                .add(zItems.COPPER_DUST.get());

        tag(zItemTags.DUST_DIAMOND)
                .add(zItems.DIAMOND_DUST.get());

        tag(zItemTags.DUST_EMERALD)
                .add(zItems.EMERALD_DUST.get());

        tag(zItemTags.DUST_GOLD)
                .add(zItems.GOLD_DUST.get());

        tag(zItemTags.DUST_IRON)
                .add(zItems.IRON_DUST.get());

        tag(zItemTags.DUST_LAPIS)
                .add(zItems.LAPIS_DUST.get());

        tag(zItemTags.DUST_QUARTZ)
                .add(zItems.QUARTZ_DUST.get());

        tag(zItemTags.SAWDUST)
                .add(zItems.SAWDUST.get())
                .addOptionalTag(zItemTags.SAWDUST2);

        tag(ItemTags.PIGLIN_LOVED)
                .add(zItems.GOLD_DUST.get());

        tag(zItemTags.METAL_NUGGETS)
                .addTag(Tags.Items.NUGGETS);

        tag(zItemTags.DUST_SULFUR)
                .add(zItems.SULFUR_DUST.get());

        tag(Tags.Items.SLIME_BALLS)
                .add(zItems.PATINA.get());

    }

}
