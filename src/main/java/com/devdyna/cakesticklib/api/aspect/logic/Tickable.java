package com.devdyna.cakesticklib.api.aspect.logic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

// RIP TickingBE 2025-2026

public interface Tickable {

    public static <BE extends BlockEntity> void compute(Level level, BlockPos pos, BlockState state, BE t) {
        if (t instanceof Tickable be) {

            if (level == null)
                return;

            be.tickBoth();

            if (level.isClientSide())
                be.tickClient();
            else
                be.tickServer();
        }
        return;
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
    default void tickServer() {
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
    default void tickClient() {
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
    default void tickBoth() {
    }
}
