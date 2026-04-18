package com.devdyna.cakesticklib.setup.registry.types;

import static com.devdyna.cakesticklib.Main.MODULE_ID;

import com.devdyna.cakesticklib.setup.RecipeRegister;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zRecipeTypes {
    // ------------------------------------------------------------------------------------------------------------------------------------//
    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
        TYPES.register(bus);
    }

    // ------------------------------------------------------------------------------------------------------------------------------------//
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, MODULE_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, MODULE_ID);
    // ------------------------------------------------------------------------------------------------------------------------------------//

    public static final RecipeRegister<CopperOxidationRecipe> COPPER_OXIDATION = RecipeRegister.of("copper_oxidation",
            () -> CopperOxidationRecipe.serializer());

    // ------------------------------------------------------------------------------------------------------------------------------------//
}