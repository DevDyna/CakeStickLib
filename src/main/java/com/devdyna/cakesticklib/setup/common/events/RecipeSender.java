package com.devdyna.cakesticklib.setup.common.events;

import com.devdyna.cakesticklib.api.utils.ModAddonUtil;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;

import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

public class RecipeSender {
    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        if (ModAddonUtil.checkMod("jei"))
            event.sendRecipes(
                    zRecipeTypes.COPPER_OXIDATION.getType(),
                    zRecipeTypes.HAMMERING.getType(),
                    zRecipeTypes.UPGRADE_APPLICATION.getType(),
                    RecipeType.CRAFTING);

    }
}