package com.devdyna.cakesticklib.setup.compat.jei;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.Client;
import com.devdyna.cakesticklib.setup.compat.jei.categories.CopperOxidationCategory;
import com.devdyna.cakesticklib.setup.registry.zLibrary.*;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

@JeiPlugin
public class PluginJEI implements IModPlugin {

    @Override
    public Identifier getPluginUid() {
        return x.rl(MODULE_ID,"jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration r) {

        r.addCraftingStation(CopperOxidationCategory.TYPE, zItems.REDSTONE_ACID.get(), zItems.HONEY_SOLUTION.get());
        r.addCraftingStation(RecipeTypes.STONECUTTING, zItems.CHISEL.get());

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration r) {
        var helper = r.getJeiHelpers().getGuiHelper();

        r.addRecipeCategories(

                new CopperOxidationCategory(helper)

        );

    }

    @Override
    public void registerRecipes(IRecipeRegistration r) {

        r.addRecipes(CopperOxidationCategory.TYPE,
                getRecipes(zRecipeTypes.COPPER_OXIDATION.getType()));

    }

    private <C extends RecipeInput, T extends Recipe<C>> List<RecipeHolder<T>> getRecipes(RecipeType<T> type) {
        return List.copyOf(Client.getRecipeCollector().byType(type));
    }

}