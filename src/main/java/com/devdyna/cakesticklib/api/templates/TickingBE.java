package com.devdyna.cakesticklib.api.templates;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TickingBE extends BlockEntity {

    public TickingBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /**
     * Server only ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Useful for block events
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickServer() {
    }

    /**
     * Client only ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Useful for player events
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickClient() {
    }

    /**
     * Client and Server ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Usefull for particles
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickBoth() {
    }

}
