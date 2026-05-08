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

                tag(LibTags.Blocks.BLOCK_ADVANCED_ALLOY).add(LibBlocks.ADVANCED_ALLOY_BLOCK.get());
                tag(LibTags.Blocks.BLOCK_STEEL).add(LibBlocks.STEEL_BLOCK.get());
                tag(LibTags.Blocks.BLOCK_WROUGHT_IRON).add(LibBlocks.WROUGHT_IRON_BLOCK.get());
                
                tag(Tags.Blocks.STORAGE_BLOCKS)
                                .addTags(
                                                LibTags.Blocks.BLOCK_WROUGHT_IRON,
                                                LibTags.Blocks.BLOCK_STEEL,
                                                LibTags.Blocks.BLOCK_ADVANCED_ALLOY);
        }

}
