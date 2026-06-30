package com.devdyna.cakesticklib.api.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LootTableHelper {

    /**
     * mainly useful to define valid blocks on Datagen SubLootBlocksProvider
     */
    public static List<Block> getValidBlocks(DeferredRegister.Blocks... blocks) {
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

    public static List<ItemStack> getItemsStackWithParams(BlockState state, ServerLevel server, BlockPos pos,
            ItemStack tool, @Nullable Player player, @Nullable BlockEntity be) {
        return state.getDrops(new LootParams.Builder(server)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.BLOCK_STATE, state)
                .withParameter(LootContextParams.TOOL, tool)
                .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, be));
    }

    public static List<ItemStack> getAsSilkTouch(BlockState state, ServerLevel server, BlockPos pos,
            ItemStack tool, @Nullable Player player, @Nullable BlockEntity be) {

        ItemStack copy = tool.copy();
        copy.enchant(EnchantUtil.getEnchantHolder(server, Enchantments.SILK_TOUCH), 1);

        return getItemsStackWithParams(state, server, pos, copy, player, be);

    }

}
