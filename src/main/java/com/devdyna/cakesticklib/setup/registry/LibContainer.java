package com.devdyna.cakesticklib.setup.registry;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.function.Supplier;

import com.devdyna.cakesticklib.api.upgrades.eject.EjectModifierMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LibContainer {
        public static void register(IEventBus bus) {
                zMenu.register(bus);
        }

        public static final DeferredRegister<MenuType<?>> zMenu = DeferredRegister.create(Registries.MENU, MODULE_ID);

        public static final Supplier<MenuType<EjectModifierMenu>> EJECT_MODIFIER = zMenu.register("eject_modifier_menu",
                        () -> IMenuTypeExtension.create(EjectModifierMenu::new));

}