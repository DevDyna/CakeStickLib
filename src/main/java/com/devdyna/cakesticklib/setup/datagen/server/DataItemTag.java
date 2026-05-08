package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.RegistryUtils;
import com.devdyna.cakesticklib.setup.registry.*;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class DataItemTag extends ItemTagsProvider {

        public DataItemTag(PackOutput output, CompletableFuture<Provider> lookupProvider,CompletableFuture<TagsProvider.TagLookup<Block>> blocks) {
                super(output, lookupProvider, MODULE_ID);
        }

        @Override
        protected void addTags(Provider p) {

                tag(LibTags.Items.OXIDIZER)
                                .add(LibItems.REDSTONE_ACID.get());

                tag(LibTags.Items.WAXING)
                                .add(LibItems.HONEY_SOLUTION.get(), Items.HONEYCOMB);

                tag(Tags.Items.DUSTS).add(RegistryUtils.getItems(LibItems.zDusts));

                tag(LibTags.Items.DUST_AMETHYST).add(LibItems.AMETHYST_DUST.get());

                tag(LibTags.Items.DUST_COAL)
                                .add(LibItems.CARBON_DUST.get());

                tag(LibTags.Items.DUST_COPPER)
                                .add(LibItems.COPPER_DUST.get());

                tag(LibTags.Items.DUST_DIAMOND)
                                .add(LibItems.DIAMOND_DUST.get());

                tag(LibTags.Items.DUST_EMERALD)
                                .add(LibItems.EMERALD_DUST.get());

                tag(LibTags.Items.DUST_GOLD)
                                .add(LibItems.GOLD_DUST.get());

                tag(LibTags.Items.DUST_IRON)
                                .add(LibItems.IRON_DUST.get());

                tag(LibTags.Items.DUST_LAPIS)
                                .add(LibItems.LAPIS_DUST.get());

                tag(LibTags.Items.DUST_QUARTZ)
                                .add(LibItems.QUARTZ_DUST.get());

                tag(LibTags.Items.SAWDUST)
                                .add(LibItems.SAWDUST.get())
                                .addOptionalTag(LibTags.Items.SAWDUST2);

                tag(ItemTags.PIGLIN_LOVED)
                                .add(LibItems.GOLD_DUST.get());

                tag(LibTags.Items.METAL_NUGGETS)
                                .addTag(Tags.Items.NUGGETS);

                tag(LibTags.Items.DUST_SULFUR)
                                .add(LibItems.SULFUR_DUST.get());

                tag(Tags.Items.SLIME_BALLS)
                                .add(LibItems.PATINA.get());

                tag(Tags.Items.INGOTS)
                                .add(RegistryUtils.getItems(LibItems.zIngots));

                tag(Tags.Items.NUGGETS)
                                .add(RegistryUtils.getItems(LibItems.zNuggets));

                tag(LibTags.Items.INGOT_WROUGHT_IRON).add(LibItems.WROUGHT_IRON_INGOT.get());
                tag(LibTags.Items.INGOT_STEEL).add(LibItems.STEEL_INGOT.get());
                tag(LibTags.Items.INGOT_ADVANCEDALLOY).add(LibItems.ADVANCED_ALLOY_INGOT.get());
                tag(LibTags.Items.NUGGET_STEEL).add(LibItems.STEEL_NUGGET.get());
                tag(LibTags.Items.NUGGET_WROUGHT_IRON).add(LibItems.WROUGHT_IRON_NUGGET.get());
                tag(LibTags.Items.NUGGET_ADVANCEDALLOY).add(LibItems.ADVANCED_ALLOY_NUGGET.get());

                tag(LibTags.Items.FOILS)
                                .add(RegistryUtils.getItems(LibItems.zFoils));

                tag(LibTags.Items.FOIL_COPPER).add(LibItems.COPPER_FOIL.get());
                tag(LibTags.Items.FOIL_GOLD).add(LibItems.GOLD_FOIL.get());
                tag(LibTags.Items.FOIL_IRON).add(LibItems.IRON_FOIL.get());

                tag(LibTags.Items.PLATES)
                                .add(RegistryUtils.getItems(LibItems.zPlates));

                tag(LibTags.Items.PLATE_WROUGHT_IRON).add(LibItems.WROUGHT_IRON_PLATE.get());
                tag(LibTags.Items.PLATE_COAL).add(LibItems.CARBON_PLATE.get());
                tag(LibTags.Items.PLATE_COPPER).add(LibItems.COPPER_PLATE.get());
                tag(LibTags.Items.PLATE_GOLD).add(LibItems.GOLD_PLATE.get());
                tag(LibTags.Items.PLATE_IRON).add(LibItems.IRON_PLATE.get());
                tag(LibTags.Items.PLATE_STEEL).add(LibItems.STEEL_PLATE.get());
                tag(LibTags.Items.PLATE_ADVANCED_ALLOY).add(LibItems.ADVANCED_ALLOY_PLATE.get());

                tag(Tags.Items.GEMS).add(LibItems.SILICON_GEM.get());

                tag(LibTags.Items.GEMS_SILICON).add(LibItems.SILICON_GEM.get());

                tag(LibTags.Items.SILICON).add(LibItems.SILICON_GEM.get());

                tag(LibTags.Items.GEARS).add(RegistryUtils.getItems(LibItems.zGears));
                tag(LibTags.Items.GEAR_WOODEN).add(LibItems.WOODEN_GEAR.get());
                tag(LibTags.Items.GEAR_COPPER).add(LibItems.COPPER_GEAR.get());
                tag(LibTags.Items.GEAR_GOLD).add(LibItems.GOLD_GEAR.get());
                tag(LibTags.Items.GEAR_IRON).add(LibItems.IRON_GEAR.get());
                tag(LibTags.Items.GEAR_STEEL).add(LibItems.STEEL_GEAR.get());

                tag(LibTags.Items.COILS)
                                .add(RegistryUtils.getItems(LibItems.zCoils));

                tag(LibTags.Items.COIL_COPPER).add(LibItems.COPPER_COIL.get());
                tag(LibTags.Items.COIL_GOLD).add(LibItems.GOLD_COIL.get());
                tag(LibTags.Items.COIL_IRON).add(LibItems.IRON_COIL.get());

                tag(LibTags.Items.UPGRADES)
                                .add(RegistryUtils.getItems(LibItems.zUpgrade));

                tag(LibTags.Items.MOLDS)
                                .add(RegistryUtils.getItems(LibItems.zMolds));

                tag(Tags.Items.STORAGE_BLOCKS)
                                .add(LibBlocks.ADVANCED_ALLOY_BLOCK.get().asItem(), LibBlocks.STEEL_BLOCK.get().asItem(),
                                                LibBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                tag(LibTags.Items.BLOCK_ADVANCED_ALLOY)
                                .add(LibBlocks.ADVANCED_ALLOY_BLOCK.get().asItem());

                tag(LibTags.Items.BLOCK_STEEL)
                                .add(LibBlocks.STEEL_BLOCK.get().asItem());

                tag(LibTags.Items.BLOCK_WROUGHT_IRON)
                                .add(LibBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                tag(LibTags.Items.ORE_DEPOSITS)
                                .add(RegistryUtils.getItems(LibItems.zDeposits));

                tag(LibTags.Items.BATTERIES)
                                .add(LibItems.GREEN_BATTERY.get(), LibItems.BLUE_BATTERY.get());

        }

}
