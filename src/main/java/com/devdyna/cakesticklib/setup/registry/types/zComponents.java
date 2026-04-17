package com.devdyna.cakesticklib.setup.registry.types;

import static com.devdyna.cakesticklib.Main.ID;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zComponents {
        public static void register(IEventBus bus) {
                zComponents.register(bus);

        }
        // ---------------------------------------------------------------------------------------//

        public static final DeferredRegister<DataComponentType<?>> zComponents = DeferredRegister
                        .createDataComponents(Registries.DATA_COMPONENT_TYPE, ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<UpgradeComponents>> UPGRADE_COMPONENTS = zComponents
                        .register("upgrade_components",
                                        () -> DataComponentType.<UpgradeComponents>builder()
                                                        .persistent(UpgradeComponents.CODEC)
                                                        .networkSynchronized(UpgradeComponents.STREAM_CODEC)
                                                        .build());


}
