package com.devdyna.cakesticklib.setup.registry;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.function.Supplier;

import com.devdyna.cakesticklib.setup.registry.builders.dynamite.DynamiteEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LibEntities {
        public static void register(IEventBus bus) {
                zEntities.register(bus);
        }

        public static final DeferredRegister.Entities zEntities = DeferredRegister.createEntities(MODULE_ID);

        public static final Supplier<EntityType<DynamiteEntity>> DYNAMITE = zEntities.registerEntityType(
                        "dynamite", DynamiteEntity::new, MobCategory.MISC,
                        b -> b.noLootTable()
                                        .sized(0.25F, 0.25F)
                                        .clientTrackingRange(4)
                                        .updateInterval(10));

}