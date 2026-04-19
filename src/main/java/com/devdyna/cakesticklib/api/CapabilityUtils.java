package com.devdyna.cakesticklib.api;

import com.devdyna.cakesticklib.api.aspect.logic.EnergyBlock;
import com.devdyna.cakesticklib.api.aspect.logic.MachineItemAutomation;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class CapabilityUtils {

    public static void registerFluidBlocks(RegisterCapabilitiesEvent e, Block... blocks) {
        e.registerBlock(
                Capabilities.Fluid.BLOCK,
                (level, pos, state, be, side) -> {

                    if (be instanceof SimpleFluidStorage)
                        return be.getData(zHandlers.FLUID_STORAGE);

                    return (be != null) ? be.getData(zHandlers.FLUID_STORAGE) : null;
                },
                blocks);
    }

    public static void registerEnergyBlock(RegisterCapabilitiesEvent e, Block... blocks) {
        e.registerBlock(Capabilities.Energy.BLOCK,
                (level, pos, state, be, side) -> {

                    if (be != null && be instanceof EnergyBlock s)
                        return s.getEnergyStorage();
                    return null;
                },
                blocks

        );
    }

    public static void registerItemBlock(RegisterCapabilitiesEvent e, Block... blocks) {
        e.registerBlock(Capabilities.Item.BLOCK,
                (level, pos, state, be, side) -> {

                    if (be instanceof MachineItemAutomation m)
                        return m.getAutomationItemStorage();

                    return (be != null) ? be.getData(zHandlers.ITEM_STORAGE) : null;

                },
                blocks

        );
    }

    public static void registerBlockAll(RegisterCapabilitiesEvent e, Block... blocks) {
        registerEnergyBlock(e, blocks);
        registerFluidBlocks(e, blocks);
        registerItemBlock(e, blocks);
    }

}
