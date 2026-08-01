package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibComponents;

import net.minecraft.core.BlockPos;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.LootParams.Builder;

public interface BlockItemKeeper {

    abstract List<ItemStack> getDrops(BlockState state, Builder builder);
    abstract void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity,
            ItemStack stack);

    default List<ItemStack> getResultDrops(List<ItemStack> l, ItemLike i, BlockState s, Builder b) {
        var drops = l;
        var be = b.getParameter(LootContextParams.BLOCK_ENTITY);

        if (be instanceof ItemStorageBlock storage && !storage.isSlotsEmpty()) {
            var item = x.item(i);
            var tag = be.saveCustomOnly(b.getLevel().registryAccess());
            if (!tag.isEmpty())
                item.set(LibComponents.ITEM_CONTAINER, CustomData.of(tag));

            drops.clear();
            drops.add(item);
        }

        return drops;
    }

    default void placeBlockAndItems(Level level, BlockPos pos, BlockState state, LivingEntity entity,
            ItemStack stack) {

        if (level.isClientSide())
            return;

        if (!(entity instanceof Player))
            return;

        var be = level.getBlockEntity(pos);

        if (!(be instanceof ItemStorageBlock))
            return;

        if (!stack.has(LibComponents.ITEM_CONTAINER))
            return;

        var nbt = stack.get(LibComponents.ITEM_CONTAINER).copyTag();

        if (!nbt.isEmpty())
            be.loadCustomOnly(
                    TagValueInput.create(ProblemReporter.DISCARDING,
                            level.registryAccess(), nbt));

    }

}
