package com.devdyna.cakesticklib.api.aspect.logic;

// RIP TickingBE 2025-2026

public interface Tickable {

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
