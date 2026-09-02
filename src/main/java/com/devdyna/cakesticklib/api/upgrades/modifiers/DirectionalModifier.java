package com.devdyna.cakesticklib.api.upgrades.modifiers;

import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.UseTypeModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record DirectionalModifier(Direction dir, UseType type) implements UseTypeModifier {

    public static DirectionalModifier of(Direction dir, UseType type) {
        return new DirectionalModifier(dir, type);
    }

    @Override
    public Codec<? extends BaseModifier> getCodec() {
        return CODEC;
    }

    @Override
    public StreamCodec<FriendlyByteBuf, ? extends BaseModifier> getStreamCodec() {
        return STREAM_CODEC;
    }

    public static final Codec<DirectionalModifier> CODEC = RecordCodecBuilder.create(i -> i.group(
            Direction.CODEC.fieldOf("dir").forGetter(DirectionalModifier::dir),
            UseType.CODEC.fieldOf("type").forGetter(DirectionalModifier::type))
            .apply(i, DirectionalModifier::new));

    public static final StreamCodec<FriendlyByteBuf, DirectionalModifier> STREAM_CODEC = StreamCodec.composite(
            Direction.STREAM_CODEC, DirectionalModifier::dir,
            UseType.STREAM_CODEC, DirectionalModifier::type,
            DirectionalModifier::new);

            
}
