package com.devdyna.cakesticklib.api.factories.plants;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public interface Harvestable {

    List<ItemStack> getItemResult(Level level, BlockState state, BlockPos pos, Player player, ItemStack tool);

    int maxAge();

    boolean canBeHarvested(BlockState state);

    IntegerProperty getPublicAgeProperty();

    default boolean harvestCrop(Level level, BlockState state, BlockPos pos, Player player, ItemStack tool) {
        if (!level.isClientSide() && canBeHarvested(state) //&& !Common.DISABLE_HARVESTABLE_ACTION.get() TODO
        ) {

            getItemResult(level, state, pos, player, tool)
                    .forEach(item -> player.addItem(item));

            level.setBlockAndUpdate(pos,
                    state.setValue(getPublicAgeProperty(),level.getRandom().nextInt(maxAge() - 2)));
            return true;
        }
        return false;
    }

}
