package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.RegistryUtils;
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

                tag(Tags.Items.DUSTS).add(RegistryUtils.getItems(zItems.zDusts));

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

                tag(Tags.Items.INGOTS)
                                .add(RegistryUtils.getItems(zItems.zIngots));

                tag(Tags.Items.NUGGETS)
                                .add(RegistryUtils.getItems(zItems.zNuggets));

                tag(zItemTags.INGOT_WROUGHT_IRON).add(zItems.WROUGHT_IRON_INGOT.get());
                tag(zItemTags.INGOT_STEEL).add(zItems.STEEL_INGOT.get());
                tag(zItemTags.INGOT_ADVANCEDALLOY).add(zItems.ADVANCED_ALLOY_INGOT.get());
                tag(zItemTags.NUGGET_STEEL).add(zItems.STEEL_NUGGET.get());
                tag(zItemTags.NUGGET_WROUGHT_IRON).add(zItems.WROUGHT_IRON_NUGGET.get());
                tag(zItemTags.NUGGET_ADVANCEDALLOY).add(zItems.ADVANCED_ALLOY_NUGGET.get());

                tag(zItemTags.FOILS)
                                .add(RegistryUtils.getItems(zItems.zFoils));

                tag(zItemTags.FOIL_COPPER).add(zItems.COPPER_FOIL.get());
                tag(zItemTags.FOIL_GOLD).add(zItems.GOLD_FOIL.get());
                tag(zItemTags.FOIL_IRON).add(zItems.IRON_FOIL.get());

                tag(zItemTags.PLATES)
                                .add(RegistryUtils.getItems(zItems.zPlates));

                tag(zItemTags.PLATE_WROUGHT_IRON).add(zItems.WROUGHT_IRON_PLATE.get());
                tag(zItemTags.PLATE_COAL).add(zItems.CARBON_PLATE.get());
                tag(zItemTags.PLATE_COPPER).add(zItems.COPPER_PLATE.get());
                tag(zItemTags.PLATE_GOLD).add(zItems.GOLD_PLATE.get());
                tag(zItemTags.PLATE_IRON).add(zItems.IRON_PLATE.get());
                tag(zItemTags.PLATE_STEEL).add(zItems.STEEL_PLATE.get());
                tag(zItemTags.PLATE_ADVANCED_ALLOY).add(zItems.ADVANCED_ALLOY_PLATE.get());

                tag(Tags.Items.GEMS).add(zItems.SILICON_GEM.get());

                tag(zItemTags.GEMS_SILICON).add(zItems.SILICON_GEM.get());

                tag(zItemTags.SILICON).add(zItems.SILICON_GEM.get());

                tag(zItemTags.GEARS).add(RegistryUtils.getItems(zItems.zGears));
                tag(zItemTags.GEAR_WOODEN).add(zItems.WOODEN_GEAR.get());
                tag(zItemTags.GEAR_COPPER).add(zItems.COPPER_GEAR.get());
                tag(zItemTags.GEAR_GOLD).add(zItems.GOLD_GEAR.get());
                tag(zItemTags.GEAR_IRON).add(zItems.IRON_GEAR.get());
                tag(zItemTags.GEAR_STEEL).add(zItems.STEEL_GEAR.get());

                tag(zItemTags.COILS)
                                .add(RegistryUtils.getItems(zItems.zCoils));

                tag(zItemTags.COIL_COPPER).add(zItems.COPPER_COIL.get());
                tag(zItemTags.COIL_GOLD).add(zItems.GOLD_COIL.get());
                tag(zItemTags.COIL_IRON).add(zItems.IRON_COIL.get());

                tag(zItemTags.UPGRADES)
                                .add(RegistryUtils.getItems(zItems.zUpgrade));

                tag(zItemTags.MOLDS)
                                .add(RegistryUtils.getItems(zItems.zMolds));

                tag(Tags.Items.STORAGE_BLOCKS)
                                .add(zBlocks.ADVANCED_ALLOY_BLOCK.get().asItem(), zBlocks.STEEL_BLOCK.get().asItem(),
                                                zBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                tag(zItemTags.BLOCK_ADVANCED_ALLOY)
                                .add(zBlocks.ADVANCED_ALLOY_BLOCK.get().asItem());

                tag(zItemTags.BLOCK_STEEL)
                                .add(zBlocks.STEEL_BLOCK.get().asItem());

                tag(zItemTags.BLOCK_WROUGHT_IRON)
                                .add(zBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                tag(zItemTags.ORE_DEPOSITS)
                                .add(RegistryUtils.getItems(zItems.zDeposits));

                tag(zItemTags.BATTERIES)
                                .add(zItems.GREEN_BATTERY.get(), zItems.BLUE_BATTERY.get());

        }

}
