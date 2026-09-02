package com.devdyna.cakesticklib.api.upgrades;

import java.util.Optional;

import com.devdyna.cakesticklib.api.upgrades.modifiers.*;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier.UseType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record UpgradeComponents(
        Optional<NumericModifier> speed, Optional<NumericModifier> energy,
        Optional<NumericModifier> luck, Optional<NumericModifier> fluid_usage,
        Optional<DirectionalModifier> eject) {

    public static final Codec<UpgradeComponents> CODEC = RecordCodecBuilder.create(i -> i.group(
            NumericModifier.CODEC.optionalFieldOf("speed").forGetter(UpgradeComponents::speed),
            NumericModifier.CODEC.optionalFieldOf("energy").forGetter(UpgradeComponents::energy),
            NumericModifier.CODEC.optionalFieldOf("luck").forGetter(UpgradeComponents::luck),
            NumericModifier.CODEC.optionalFieldOf("fluid").forGetter(UpgradeComponents::fluid_usage),
            DirectionalModifier.CODEC.optionalFieldOf("eject").forGetter(UpgradeComponents::eject))
            .apply(i, UpgradeComponents::new));

    public static final StreamCodec<FriendlyByteBuf, UpgradeComponents> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.optional(NumericModifier.STREAM_CODEC), UpgradeComponents::speed,
            ByteBufCodecs.optional(NumericModifier.STREAM_CODEC), UpgradeComponents::energy,
            ByteBufCodecs.optional(NumericModifier.STREAM_CODEC), UpgradeComponents::luck,
            ByteBufCodecs.optional(NumericModifier.STREAM_CODEC), UpgradeComponents::fluid_usage,
            ByteBufCodecs.optional(DirectionalModifier.STREAM_CODEC), UpgradeComponents::eject,
            UpgradeComponents::new);

    public static final UpgradeComponents EMPTY = new UpgradeComponents(
            Optional.empty(), Optional.empty(),
            Optional.empty(), Optional.empty(),
            Optional.empty());

    public enum UpgradeType {

        SPEED(0),
        ENERGY(1),
        LUCK(2),
        FLUID(3),
        EJECT(4);

        private int i;

        UpgradeType(int i) {
            this.i = i;
        }

        public int value() {
            return i;
        }

        public static final Codec<UpgradeType> CODEC = Codec.INT.xmap(
                value -> {
                    for (UpgradeType type : values())
                        if (type.i == value)
                            return type;

                    throw new IllegalArgumentException("Unknown UpgradeType: " + value);
                },
                UpgradeType::value);

        public static final StreamCodec<ByteBuf, UpgradeType> STREAM_CODEC = ByteBufCodecs.INT.map(
                value -> {
                    for (UpgradeType type : values())
                        if (type.i == value)
                            return type;

                    throw new IllegalArgumentException("Unknown UpgradeType: " + value);
                },
                UpgradeType::value);
    }

    public static class Builder {

        NumericModifier energy = null;
        NumericModifier speed = null;
        NumericModifier luck = null;
        NumericModifier fluid = null;
        DirectionalModifier eject = null;

        public Builder() {

        }

        public static Builder of() {
            return new Builder();
        }

        public Builder speed(int v) {
            this.speed = NumericModifier.of(v);
            return this;
        }

        public Builder energy(int v) {
            this.energy = NumericModifier.of(v);
            return this;
        }

        public Builder luck(int v) {
            this.luck = NumericModifier.of(v);
            return this;
        }

        public Builder fluid(int v) {
            this.fluid = NumericModifier.of(v);
            return this;
        }

        public Builder eject(Direction d, UseType type) {
            this.eject = DirectionalModifier.of(d, type);
            return this;
        }

        public DirectionalModifier getEject() {
            return eject;
        }

        public NumericModifier getEnergy() {
            return energy;
        }

        public NumericModifier getFluid() {
            return fluid;
        }

        public NumericModifier getLuck() {
            return luck;
        }

        public NumericModifier getSpeed() {
            return speed;
        }

        public UpgradeComponents create() {
            return new UpgradeComponents(
                    Optional.ofNullable(speed),
                    Optional.ofNullable(energy),
                    Optional.ofNullable(luck),
                    Optional.ofNullable(fluid),
                    Optional.ofNullable(eject));

        }
    }

}