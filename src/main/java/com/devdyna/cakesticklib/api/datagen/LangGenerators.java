package com.devdyna.cakesticklib.api.datagen;

import java.util.function.Supplier;

import com.devdyna.cakesticklib.api.compat.jei.JEIAliasesHelper;
import com.devdyna.cakesticklib.setup.mixin.LanguageProviderAccessor;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface LangGenerators {

    abstract void add(String key, String value);

    default void add(JEIAliasesHelper.Alias key, String value) {
        add(key.key(), value);
    }

    abstract void addBlock(Supplier<? extends Block> key, String name);

    abstract void add(Block key, String name);

    default String getModId() {
        return ((LanguageProviderAccessor) this).getModName();
    }

    abstract void addItem(Supplier<? extends Item> key, String name);

    default void addFluid(Fluid fluid, String value) {
        addFluid(fluid.getFluidType(), value);
    }

    default void addFluid(FluidType fluid, String value) {
        add(fluid.getDescriptionId(), value);
    }

    default void addConfigTitle(String key, String display) {
        add(getModId() + ".configuration." + key, display);
    }

    default <T extends ConfigValue<?>> void addConfigKey(T key, String display) {
        addConfigEntry(key.getPath().get(1), display);
    }

    private void addConfigEntry(String key, String display) {
        add(getModId() + ".configuration." + key, display);
    }

    //TODO rework or remove entirely moving to x.name
    default String named(DeferredHolder<?, ?> b) {

        StringBuilder result = new StringBuilder();
        for (var word : b.getRegisteredName()
                .replace(getModId() + ":", "")
                .replaceAll("_", " ")
                .split(" "))
            if (!word.isEmpty())
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
        return result.toString().trim();
    }

    default void advKey(String mod, String key, String title, String desc) {
        add(mod + ".advancement.branch." + key, title);
        add(mod + ".advancement.branch." + key + ".desc", desc);
    }

    default void advKey(String key, String title, String desc) {
        advKey(getModId(), key, title, desc);
    }

}
