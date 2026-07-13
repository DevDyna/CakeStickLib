package com.devdyna.cakesticklib.api.datagen;

import java.util.function.Supplier;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;

public interface LangGenerators {

    abstract void add(String key, String value);

    abstract void addBlock(Supplier<? extends Block> key, String name);

    abstract void add(Block key, String name);

    abstract void addItem(Supplier<? extends Item> key, String name);

    default void addFluid(Fluid fluid, String value) {
        addFluid(fluid.getFluidType(), value);
    }

    default void addFluid(FluidType fluid, String value) {
        add(fluid.getDescriptionId(), value);
    }


}
