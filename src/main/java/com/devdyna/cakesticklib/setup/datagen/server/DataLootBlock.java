package com.devdyna.cakesticklib.setup.datagen.server;

import java.util.*;

import com.devdyna.cakesticklib.api.utils.LootTableHelper;
import com.devdyna.cakesticklib.setup.registry.LibBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class DataLootBlock extends BlockLootSubProvider {

        public DataLootBlock(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                return LootTableHelper.getValidBlocks(LibBlocks.zBlockItem);
        }

        @Override
        protected void generate() {

                LootTableHelper.getValidBlocks(LibBlocks.zBlockItem).forEach(this::dropSelf);

        }

}
