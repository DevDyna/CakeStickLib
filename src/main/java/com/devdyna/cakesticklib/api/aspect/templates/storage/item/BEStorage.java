package com.devdyna.cakesticklib.api.aspect.templates.storage.item;

import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.templates.menu.BEMenu;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public abstract class BEStorage extends BEMenu implements ItemStorageBlock {

    public BEStorage(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    /**
     * Default value : 1
     */
    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        getItemStorage().serialize(output);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        getItemStorage().deserialize(input);
        super.loadAdditional(input);
    }

}
