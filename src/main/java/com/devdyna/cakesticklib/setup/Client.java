package com.devdyna.cakesticklib.setup;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;
import com.devdyna.cakesticklib.api.client.ItemRenderDecorator;
import com.devdyna.cakesticklib.api.datagen.selectors.EjectTypeProperty;
import com.devdyna.cakesticklib.api.upgrades.eject.EjectModifierScreen;
import com.devdyna.cakesticklib.api.client.HudRenderable;
import com.devdyna.cakesticklib.api.utils.ModAddonUtil;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibContainer;
import com.devdyna.cakesticklib.setup.registry.LibItems;

import net.minecraft.world.item.crafting.RecipeMap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

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
    public static void registerSelectProperties( RegisterSelectItemModelPropertyEvent event) {
        event.register(x.rl(MODULE_ID, "eject"), EjectTypeProperty.TYPE);
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