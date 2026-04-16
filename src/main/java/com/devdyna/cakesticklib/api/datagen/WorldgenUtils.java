package com.devdyna.cakesticklib.api.datagen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.HolderSet.Named;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;

public class WorldgenUtils {

        public static void registerBiomeModifer(BootstrapContext<BiomeModifier> c, ResourceKey<BiomeModifier> m,
                        Named<Biome> biome, HolderSet<PlacedFeature> features, GenerationStep.Decoration step) {
                c.register(m, new AddFeaturesBiomeModifier(biome, features, step));
        }
}
