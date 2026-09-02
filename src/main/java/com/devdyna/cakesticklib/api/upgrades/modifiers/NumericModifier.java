package com.devdyna.cakesticklib.api.upgrades.modifiers;

import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record NumericModifier(Integer value) implements BaseModifier {

    public static NumericModifier of(Integer value) {
        return new NumericModifier(value);
    }

    @Override
    public Codec<? extends BaseModifier> getCodec() {
        return CODEC;
    }

    @Override
    public StreamCodec<FriendlyByteBuf, ? extends BaseModifier> getStreamCodec() {
        return STREAM_CODEC;
    }

    public static final Codec<NumericModifier> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("value").forGetter(NumericModifier::value))
            .apply(i, NumericModifier::new));

    public static final StreamCodec<FriendlyByteBuf, NumericModifier> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, NumericModifier::value,
            NumericModifier::new);
}
