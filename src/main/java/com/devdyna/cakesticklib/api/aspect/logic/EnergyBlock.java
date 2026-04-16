package com.devdyna.cakesticklib.api.aspect.logic;

import net.minecraft.world.inventory.ContainerData;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;

public interface EnergyBlock {

    /**
     * Something like "new SimpleContainerData(getMaxFE())"
     */
    ContainerData getContainerData();

    EnergyHandler getEnergyStorage();

    int getMaxEnergy();

    /*
     * define max energy can be insered AND extracted
     */
    default int getMaxTransferEnergy(){
        return getMaxEnergy();
    }

    /*
     * define max energy can be extracted
     */
    default int getMaxExtractEnergy(){
        return getMaxTransferEnergy();
    }

    /*
     * define max energy can be insered
     */
    default int getMaxInsertEnergy(){
        return getMaxTransferEnergy();
    }

    /*
     * define the base FE value stored (mainly for creative content)
     */
    default int getBaseEnergyStored(){
        return 0;
    }

    /**
     * FE not empty / full
     * REQUIRE SERVER ONLY
     */
    default boolean canExtract() {
        return getEnergyStorage().getAmountAsInt() > 0;
    }

    /**
     * FE not empty / full
     * REQUIRE SERVER ONLY
     */
    default boolean hasEnergy(int v) {
        return getEnergyStorage().getAmountAsInt() >= v;
    }

    /**
     * FE not full / empty
     */
    default boolean canReceive() {
        return getEnergyStorage().getAmountAsInt() < getEnergyStorage().getCapacityAsInt();
    }

}