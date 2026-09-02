package com.devdyna.cakesticklib.api.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidUtils {
    
    /**
     * Mainly used on ContainerData client-server
     */
    public static int getFluidToID(Fluid f) {
        return BuiltInRegistries.FLUID.getId(f);
    }

    /**
     * Mainly used on ContainerData client-server
     */
    public static int getFluidToID(FluidStack f) {
        return getFluidToID(f.getFluid());
    }

    /**
     * Mainly used on ContainerData client-server
     */
    public static Fluid getFluidFromID(int id) {
        return BuiltInRegistries.FLUID.byId(id);
    }
}
