package com.devdyna.cakesticklib.setup.datagen.server;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.registry.*;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class DataBlockTag extends BlockTagsProvider {

        public DataBlockTag(PackOutput output, CompletableFuture<Provider> lookupProvider) {
                super(output, lookupProvider, MODULE_ID);
        }

        @Override
        protected void addTags(Provider p) {

                tag(LibTags.Blocks.ADVANCED_ALLOY_BLOCK).add(LibBlocks.ADVANCED_ALLOY_BLOCK.get());
                tag(LibTags.Blocks.STEEL_BLOCK).add(LibBlocks.STEEL_BLOCK.get());
                tag(LibTags.Blocks.WROUGHT_IRON_BLOCK).add(LibBlocks.WROUGHT_IRON_BLOCK.get());

                tag(Tags.Blocks.STORAGE_BLOCKS)
                                .addTags(
                                                LibTags.Blocks.WROUGHT_IRON_BLOCK,
                                                LibTags.Blocks.STEEL_BLOCK,
                                                LibTags.Blocks.WROUGHT_IRON_BLOCK);

                tag(LibTags.Blocks.HARVESTABLE_BLACKLIST).add();
        }

}
