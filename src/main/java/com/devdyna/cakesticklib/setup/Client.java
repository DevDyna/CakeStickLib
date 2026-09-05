package com.devdyna.cakesticklib.setup;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.api.client.ItemRenderDecorator;
import com.devdyna.cakesticklib.api.datagen.selectors.EjectTypeProperty;
import com.devdyna.cakesticklib.api.upgrades.eject.EjectModifierScreen;
import com.devdyna.cakesticklib.api.FluidRegister;
import com.devdyna.cakesticklib.api.FluidRenderUtils;
import com.devdyna.cakesticklib.api.client.HudRenderable;
import com.devdyna.cakesticklib.api.utils.ClazzUtil;
import com.devdyna.cakesticklib.api.utils.ModAddonUtil;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibContainer;
import com.devdyna.cakesticklib.setup.registry.LibFluids;
import com.devdyna.cakesticklib.setup.registry.LibItems;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.fluid.FluidTintSource;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(value = MODULE_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = MODULE_ID, value = Dist.CLIENT)
public class Client {

    public Client(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void renderItemDecorators(RegisterItemDecorationsEvent r) {
        r.register(LibItems.CHISEL.get(), new ItemRenderDecorator());
    }

    // TODO NYI
    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CROSSHAIR, x.rl(MODULE_ID, "hud_tooltip"), HudRenderable.LAYER);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(LibContainer.EJECT_MODIFIER.get(), EjectModifierScreen::new);
    }

    @SubscribeEvent
    public static void registerSelectProperties(RegisterSelectItemModelPropertyEvent event) {
        event.register(x.rl(MODULE_ID, "eject"), EjectTypeProperty.TYPE);
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {

        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public Identifier getRenderOverlayTexture(Minecraft mc) {
                return x.parse("textures/misc/underwater.png");
            }
        }, ClazzUtil.getAll(LibFluids.class, FluidRegister.class)
                .stream()
                .map(FluidRegister::getType)
                .map(DeferredHolder::get)
                .toArray(FluidType[]::new));

    }

    @SubscribeEvent
    public static void onRegisterFluidModels(RegisterFluidModelsEvent event) {

        var molten_fluids = List.of(
                LibFluids.MOLTEN_ANCIENT_DEBRIS,
                LibFluids.MOLTEN_COPPER,
                LibFluids.MOLTEN_GOLD,
                LibFluids.MOLTEN_IRON,
                LibFluids.MOLTEN_STEEL,
                LibFluids.MOLTEN_NETHERITE,
                LibFluids.MOLTEN_REDSTONE,
                LibFluids.MOLTEN_GLOWSTONE);

        var blazing_fluids = List.of(
                LibFluids.SULFURIC_ACID,
                LibFluids.MOLTEN_BLAZING,
                LibFluids.PLASTIC);

        molten_fluids.forEach(
                f -> event.register(
                        FluidRenderUtils.createMoltenModel(new FluidTintSource() {

                            @Override
                            public int color(FluidState state) {
                                return f.getColor();
                            }

                        }),
                        f.getSource(),
                        f.getFlowing()));

        blazing_fluids.forEach(
                f -> event.register(
                        FluidRenderUtils.createBlazingModel(new FluidTintSource() {

                            @Override
                            public int color(FluidState state) {
                                return f.getColor();
                            }

                        }),
                        f.getSource(),
                        f.getFlowing()));

        ClazzUtil.getAll(LibFluids.class, FluidRegister.class).stream()
                .filter(f -> !molten_fluids.contains(f))
                .filter(f -> !blazing_fluids.contains(f))
                .forEach(
                        f -> event.register(
                                FluidRenderUtils.createWaterModel(new FluidTintSource() {

                                    @Override
                                    public int color(FluidState state) {
                                        return f.getColor();
                                    }

                                }),
                                f.getSource(),
                                f.getFlowing()));

    }

    // Recipe collector client-side

    private static RecipeMap recipeCollector = RecipeMap.EMPTY;

    @SubscribeEvent
    public static void onRecipesSynced(RecipesReceivedEvent event) {
        if (ModAddonUtil.checkMod("jei"))
            recipeCollector = event.getRecipeMap();
    }

    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        recipeCollector = RecipeMap.EMPTY;
    }

    public static RecipeMap getRecipeCollector() {
        return recipeCollector;
    }

}