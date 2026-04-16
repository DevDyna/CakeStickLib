package com.devdyna.cakesticklib.api.utils;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class ModAddonUtil {

    /**
     * simple check to define when a mod is loaded
     */
    public static boolean checkMod(String s) {
        return ModList.get().isLoaded(s);
    }

    /**
     * useable on DATAGEN-RECIPES
     */
    public static ICondition hasMod(String s) {
        return new ModLoadedCondition(s);
    }
}
