package com.devdyna.cakesticklib.setup.registry;

import com.devdyna.cakesticklib.CakeStickLib;
import com.devdyna.cakesticklib.setup.RecipeRegister;
import com.devdyna.cakesticklib.setup.common.recipes.hammering.HammeringRecipe;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationRecipe;
import com.devdyna.cakesticklib.setup.common.recipes.upgrade_application.UpgradeApplication;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LibRecipeTypes {
                public static void register(IEventBus bus) {
                        SERIALIZERS.register(bus);
                        TYPES.register(bus);
                }

                public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
                                .create(Registries.RECIPE_SERIALIZER, CakeStickLib.MODULE_ID);
                public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(
                                Registries.RECIPE_TYPE,
                                CakeStickLib.MODULE_ID);

                public static final RecipeRegister<CopperOxidationRecipe> COPPER_OXIDATION = RecipeRegister.of(
                                "copper_oxidation",
                                () -> CopperOxidationRecipe.serializer());

                public static final RecipeRegister<HammeringRecipe> HAMMERING = RecipeRegister.of(
                                "hammering",
                                () -> HammeringRecipe.serializer());

                public static final RecipeRegister<UpgradeApplication> UPGRADE_APPLICATION = RecipeRegister.of(
                                "upgrade_application",
                                () -> UpgradeApplication.serializer());

        }
