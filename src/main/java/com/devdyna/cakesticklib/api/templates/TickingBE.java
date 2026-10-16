package com.devdyna.cakesticklib.api.templates;

import com.devdyna.cakesticklib.api.aspect.logic.Tickable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
//replaced by Tickable BE interface
@Deprecated
public abstract class TickingBE extends BlockEntity implements Tickable {

    public TickingBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

}
