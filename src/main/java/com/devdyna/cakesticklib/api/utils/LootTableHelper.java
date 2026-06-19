package com.devdyna.cakesticklib.api.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LootTableHelper {

    /**
     * mainly useful to define valid blocks on Datagen SubLootBlocksProvider
     */
    public List<Block> getValidBlocks(DeferredRegister.Blocks... blocks) {
        List<Block> result = new ArrayList<>();
        List.of(blocks).forEach(t -> result.addAll(t.getEntries()
                .stream()
                .map(DeferredHolder::get)
                .toList()));
        return result;
    }

    public static LootTable getLootTable(Level level, Identifier rl) {
        return level.getServer().reloadableRegistries()
                .getLootTable(ResourceKey
                        .create(Registries.LOOT_TABLE, rl));
    }

    public static List<ItemStack> getItemStackFromLootTable(ServerLevel level, Identifier rl) {
        return getLootTable(level, rl).getRandomItems(new LootParams.Builder(level).create(LootContextParamSets.EMPTY));
    }

    public static List<ItemStack> getItemStackFromLootTable(ServerLevel level, Identifier rl, LootParams p) {
        return getLootTable(level, rl).getRandomItems(p);
    }

    public static LootTable getLootTable(Level level, ResourceKey<LootTable> rk) {
        return level.getServer().reloadableRegistries()
                .getLootTable(rk);
    }

    public static List<ItemStack> getItemStackFromLootTable(ServerLevel level, ResourceKey<LootTable> rk) {
        return getLootTable(level, rk).getRandomItems(new LootParams.Builder(level).create(LootContextParamSets.EMPTY));
    }

    public static List<ItemStack> getItemStackFromLootTable(ServerLevel level, ResourceKey<LootTable> rk,
            LootParams p) {
        return getLootTable(level, rk).getRandomItems(p);
    }

}
