package com.devdyna.cakesticklib.setup;

import com.devdyna.cakesticklib.setup.registry.*;

import net.neoforged.bus.api.IEventBus;

public class RegistryHub {
    /**
         * DON'T OVERRIDE OR IT WILL LOSE ANY RECIPE TYPES AND HANDLERS!
         */
        public static void register(IEventBus bus) {
                zHandlers.register(bus);
                zItems.register(bus);
                zComponents.register(bus);
                zRecipeTypes.register(bus);
                zItemTags.register(bus);
                zCreativeTabs.register(bus);
                zBlocks.register(bus);
                zBlockTags.register(bus);
        }
}
