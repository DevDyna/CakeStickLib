package com.devdyna.cakesticklib.setup.registry.types;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zLibComponents {
        public static void register(IEventBus bus) {
                zComponents.register(bus);

        }
        // ---------------------------------------------------------------------------------------//

        public static final DeferredRegister<DataComponentType<?>> zComponents = DeferredRegister
                        .createDataComponents(Registries.DATA_COMPONENT_TYPE, MODULE_ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<UpgradeComponents>> UPGRADE_COMPONENTS = zComponents
                        .register("upgrade_components",
                                        () -> DataComponentType.<UpgradeComponents>builder()
                                                        .persistent(UpgradeComponents.CODEC)
                                                        .networkSynchronized(UpgradeComponents.STREAM_CODEC)
                                                        .build());

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<Identifier>> IDENTIFIER = zComponents
                        .register("identifier",
                                        () -> DataComponentType.<Identifier>builder()
                                                        .persistent(Identifier.CODEC)
                                                        .networkSynchronized(Identifier.STREAM_CODEC)
                                                        .build());


}
