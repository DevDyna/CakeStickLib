package com.devdyna.cakesticklib.setup.registry;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.RegistryUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zCreativeTabs {
        
        public static void register(IEventBus bus) {
                zCreative.register(bus);
        }

        public static final DeferredRegister<CreativeModeTab> zCreative = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, MODULE_ID);

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> INGREDIENTS = RegistryUtils
                        .createCreativeTab(MODULE_ID, "resources", () -> zItems.CHIP.get(),
                                        zCreativeTabs.zCreative);

}
