package com.devdyna.cakesticklib.api.recipe.recipeOutput;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.neoforged.neoforge.fluids.FluidStackTemplate;

public class ChanceOutput {

        /**
         * @param chance must be 0.0 -> 1.0 [ 1.0 == 100% ]
         */
        public record Item(ItemStackTemplate item, float chance) {

                public static final Codec<ChanceOutput.Item> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                                ItemStackTemplate.CODEC.fieldOf("item").forGetter(ChanceOutput.Item::item),
                                Codec.floatRange(0, 1).fieldOf("chance").forGetter(ChanceOutput.Item::chance))
                                .apply(inst, ChanceOutput.Item::new));

                public static final StreamCodec<RegistryFriendlyByteBuf, ChanceOutput.Item> STREAM_CODEC = StreamCodec
                                .composite(
                                                ItemStackTemplate.STREAM_CODEC, ChanceOutput.Item::item,
                                                ByteBufCodecs.FLOAT, ChanceOutput.Item::chance,
                                                ChanceOutput.Item::new);

                public static final ChanceOutput.Item of(ItemStackTemplate stack, float chance) {
                        return new ChanceOutput.Item(stack, chance);
                }

                public static final Optional<ChanceOutput.Item> optional(ChanceOutput.Item t) {
                        return t != null ? Optional.of(t) : Optional.empty();
                }

                public static final boolean valid(ChanceOutput.Item t) {
                        return itemValid(t) && t.chance > 0f && t.chance <= 1f;
                }

                public static final boolean itemValid(ChanceOutput.Item t) {
                        return t != null && t.item != null && t.item.item().value() != null;
                }
        }

        public record Fluid(FluidStackTemplate fluid, float chance) {

                public static final Codec<ChanceOutput.Fluid> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                                FluidStackTemplate.CODEC.fieldOf("fluid").forGetter(ChanceOutput.Fluid::fluid),
                                Codec.floatRange(0, 1).fieldOf("chance").forGetter(ChanceOutput.Fluid::chance))
                                .apply(inst, ChanceOutput.Fluid::new));

                public static final StreamCodec<RegistryFriendlyByteBuf, ChanceOutput.Fluid> STREAM_CODEC = StreamCodec
                                .composite(
                                                FluidStackTemplate.STREAM_CODEC, ChanceOutput.Fluid::fluid,
                                                ByteBufCodecs.FLOAT, ChanceOutput.Fluid::chance,
                                                ChanceOutput.Fluid::new);

                public static final ChanceOutput.Fluid of(FluidStackTemplate fluid, float chance) {
                        return new ChanceOutput.Fluid(fluid, chance);
                }

                public static final Optional<ChanceOutput.Fluid> optional(ChanceOutput.Fluid t) {
                        return t != null ? Optional.of(t) : Optional.empty();
                }

                public static final boolean valid(ChanceOutput.Fluid t) {
                        return fluidValid(t) && t.chance > 0f && t.chance <= 1f;
                }

                public static final boolean fluidValid(ChanceOutput.Fluid t) {
                        return t != null && t.fluid != null && t.fluid.fluid().value() != null;
                }
        }
}