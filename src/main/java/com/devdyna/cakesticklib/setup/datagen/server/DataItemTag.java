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

        public DataItemTag(PackOutput output, CompletableFuture<Provider> lookupProvider,
                        CompletableFuture<TagsProvider.TagLookup<Block>> blocks) {
                super(output, lookupProvider, MODULE_ID);
        }

        @Override
        protected void addTags(Provider p) {

                tag(LibTags.Items.OXIDIZER)
                                .add(LibItems.REDSTONE_ACID.get());

                tag(LibTags.Items.WAXING)
                                .add(LibItems.HONEY_SOLUTION.get(), Items.HONEYCOMB);

                tag(Tags.Items.DUSTS).add(RegistryUtils.getItems(LibItems.zDusts));

                tag(LibTags.Items.AMETHYST_DUST).add(LibItems.AMETHYST_DUST.get());
                tag(LibTags.Items.COAL_DUST).add(LibItems.CARBON_DUST.get());
                tag(LibTags.Items.CARBON_DUST).add(LibItems.CARBON_DUST.get());
                tag(LibTags.Items.COPPER_DUST).add(LibItems.COPPER_DUST.get());
                tag(LibTags.Items.DIAMOND_DUST).add(LibItems.DIAMOND_DUST.get());
                tag(LibTags.Items.EMERALD_DUST).add(LibItems.EMERALD_DUST.get());
                tag(LibTags.Items.GOLD_DUST).add(LibItems.GOLD_DUST.get());
                tag(LibTags.Items.IRON_DUST).add(LibItems.IRON_DUST.get());
                tag(LibTags.Items.LAPIS_DUST).add(LibItems.LAPIS_DUST.get());
                tag(LibTags.Items.QUARTZ_DUST).add(LibItems.QUARTZ_DUST.get());
                tag(LibTags.Items.GLASS_DUST).add(LibItems.GLASS_DUST.get());

                tag(LibTags.Items.SULFUR_DUST)
                                .add(LibItems.SULFUR_DUST.get());

                tag(LibTags.Items.SAWDUST)
                                .add(LibItems.SAWDUST.get())
                                .addOptionalTag(LibTags.Items.SAWDUST2);

                tag(ItemTags.PIGLIN_LOVED)
                                .addOptionalTags(
                                                LibTags.Items.GOLD_COIL,
                                                LibTags.Items.GOLD_DUST,
                                                LibTags.Items.GOLD_FOIL,
                                                LibTags.Items.GOLD_GEAR,
                                                LibTags.Items.GOLD_GEAR,
                                                LibTags.Items.GOLD_PLATE);

                tag(LibTags.Items.METAL_NUGGETS)
                                .addOptionalTag(Tags.Items.NUGGETS);

                tag(Tags.Items.SLIME_BALLS)
                                .add(LibItems.PATINA.get());

                tag(Tags.Items.INGOTS)
                                .add(RegistryUtils.getItems(LibItems.zIngots));

                tag(Tags.Items.NUGGETS)
                                .add(RegistryUtils.getItems(LibItems.zNuggets));

                tag(LibTags.Items.WROUGHT_IRON_INGOT).add(LibItems.WROUGHT_IRON_INGOT.get());
                tag(LibTags.Items.STEEL_INGOT).add(LibItems.STEEL_INGOT.get());
                tag(LibTags.Items.ADVANCED_ALLOY_INGOT).add(LibItems.ADVANCED_ALLOY_INGOT.get());
                tag(LibTags.Items.STEEL_NUGGET).add(LibItems.STEEL_NUGGET.get());
                tag(LibTags.Items.WROUGHT_IRON_NUGGET).add(LibItems.WROUGHT_IRON_NUGGET.get());
                tag(LibTags.Items.ADVANCED_ALLOY_NUGGET).add(LibItems.ADVANCED_ALLOY_NUGGET.get());

                tag(LibTags.Items.FOILS)
                                .add(RegistryUtils.getItems(LibItems.zFoils));

                tag(LibTags.Items.COPPER_FOIL).add(LibItems.COPPER_FOIL.get());
                tag(LibTags.Items.GOLD_FOIL).add(LibItems.GOLD_FOIL.get());
                tag(LibTags.Items.IRON_FOIL).add(LibItems.IRON_FOIL.get());

                tag(LibTags.Items.PLATES)
                                .add(RegistryUtils.getItems(LibItems.zPlates));

                tag(LibTags.Items.WROUGHT_IRON_PLATE).add(LibItems.WROUGHT_IRON_PLATE.get());
                tag(LibTags.Items.COAL_PLATE).add(LibItems.CARBON_PLATE.get());
                tag(LibTags.Items.COPPER_PLATE).add(LibItems.COPPER_PLATE.get());
                tag(LibTags.Items.GOLD_PLATE).add(LibItems.GOLD_PLATE.get());
                tag(LibTags.Items.IRON_PLATE).add(LibItems.IRON_PLATE.get());
                tag(LibTags.Items.STEEL_PLATE).add(LibItems.STEEL_PLATE.get());
                tag(LibTags.Items.ADVANCED_ALLOY_PLATE).add(LibItems.ADVANCED_ALLOY_PLATE.get());

                tag(LibTags.Items.PLASTIC).add(LibItems.PLASTIC.get())
                                .addOptionalTags(LibTags.Items.PLASTIC2, LibTags.Items.PLASTIC_PLATE,
                                                LibTags.Items.PLASTIC_PNEUMATICCRAFT);
                tag(LibTags.Items.PLASTIC2).add(LibItems.PLASTIC.get())
                                .addOptionalTags(LibTags.Items.PLASTIC, LibTags.Items.PLASTIC_PLATE,
                                                LibTags.Items.PLASTIC_PNEUMATICCRAFT);
                tag(LibTags.Items.PLASTIC_PLATE).add(LibItems.PLASTIC.get())
                                .addOptionalTags(LibTags.Items.PLASTIC, LibTags.Items.PLASTIC2,
                                                LibTags.Items.PLASTIC_PNEUMATICCRAFT);
                tag(LibTags.Items.PLASTIC_PNEUMATICCRAFT).add(LibItems.PLASTIC.get())
                                .addOptionalTags(LibTags.Items.PLASTIC, LibTags.Items.PLASTIC2,
                                                LibTags.Items.PLASTIC_PLATE);

                tag(Tags.Items.GEMS).add(LibItems.SILICON_GEM.get());

                tag(LibTags.Items.GEMS_SILICON).add(LibItems.SILICON_GEM.get());

                tag(LibTags.Items.SILICON).add(LibItems.SILICON_GEM.get());

                tag(LibTags.Items.GEARS).add(RegistryUtils.getItems(LibItems.zGears));
                tag(LibTags.Items.WOODEN_GEAR).add(LibItems.WOODEN_GEAR.get());
                tag(LibTags.Items.COPPER_GEAR).add(LibItems.COPPER_GEAR.get());
                tag(LibTags.Items.GOLD_GEAR).add(LibItems.GOLD_GEAR.get());
                tag(LibTags.Items.IRON_GEAR).add(LibItems.IRON_GEAR.get());
                tag(LibTags.Items.STEEL_GEAR).add(LibItems.STEEL_GEAR.get());

                tag(LibTags.Items.COILS)
                                .add(RegistryUtils.getItems(LibItems.zCoils));

                tag(LibTags.Items.COPPER_COIL).add(LibItems.COPPER_COIL.get());
                tag(LibTags.Items.GOLD_COIL).add(LibItems.GOLD_COIL.get());
                tag(LibTags.Items.IRON_COIL).add(LibItems.IRON_COIL.get());

                tag(LibTags.Items.UPGRADES)
                                .add(RegistryUtils.getItems(LibItems.zUpgrade));

                tag(LibTags.Items.MOLDS)
                                .add(RegistryUtils.getItems(LibItems.zMolds));

                tag(Tags.Items.STORAGE_BLOCKS)
                                .add(LibBlocks.ADVANCED_ALLOY_BLOCK.get().asItem(),
                                                LibBlocks.STEEL_BLOCK.get().asItem(),
                                                LibBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                tag(LibTags.Items.ADVANCED_ALLOY_BLOCK)
                                .add(LibBlocks.ADVANCED_ALLOY_BLOCK.get().asItem());

                tag(LibTags.Items.STEEL_BLOCK)
                                .add(LibBlocks.STEEL_BLOCK.get().asItem());

                tag(LibTags.Items.WROUGHT_IRON_BLOCK)
                                .add(LibBlocks.WROUGHT_IRON_BLOCK.get().asItem());

                tag(LibTags.Items.ORE_DEPOSITS)
                                .add(RegistryUtils.getItems(LibItems.zDeposits));

                tag(LibTags.Items.BATTERIES)
                                .add(LibItems.GREEN_BATTERY.get(), LibItems.BLUE_BATTERY.get());

                tag(LibTags.Items.CARBON_FIBER)
                                .addOptionalTag(LibTags.Items.CARBON_FIBER2)
                                .add(LibItems.CARBON_FIBER.get());

                tag(LibTags.Items.CARBON_FIBER2)
                                .addOptionalTag(LibTags.Items.CARBON_FIBER)
                                .add(LibItems.CARBON_FIBER.get());

                tag(LibTags.Items.FLOUR)
                                .add(LibItems.FLOUR.get());

                tag(LibTags.Items.ELECTRON_TUBES)
                                .add(LibItems.ELECTRON_TUBE.get());
        }

}
