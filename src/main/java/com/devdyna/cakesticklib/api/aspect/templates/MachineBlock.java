package com.devdyna.cakesticklib.api.aspect.templates;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.templates.storage.item.BlockStorage;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class MachineBlock extends BlockStorage {

    public MachineBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s,
            BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof MachineBE be) {
                be.tickBoth();
                if (l.isClientSide())
                    be.tickClient();
                else
                    be.tickServer();
            }
        };
    }

}
