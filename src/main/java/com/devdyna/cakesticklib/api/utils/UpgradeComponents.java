package com.devdyna.cakesticklib.api.utils;

import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.setup.registry.zLibrary.*;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record UpgradeComponents(
        Optional<Integer> speed,
        Optional<Integer> energy,
        Optional<Integer> luck,
        Optional<Integer> fluid_usage) {
    public static final Codec<UpgradeComponents> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.INT.optionalFieldOf("speed").forGetter(UpgradeComponents::speed),
            Codec.INT.optionalFieldOf("energy").forGetter(UpgradeComponents::energy),
            Codec.INT.optionalFieldOf("luck").forGetter(UpgradeComponents::luck),
            Codec.INT.optionalFieldOf("fluid").forGetter(UpgradeComponents::fluid_usage))
            .apply(i, UpgradeComponents::new));

    public static final StreamCodec<FriendlyByteBuf, UpgradeComponents> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::speed,
            ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::energy,
            ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::luck,
            ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::fluid_usage,
            UpgradeComponents::new);

    public static final UpgradeComponents EMPTY = new UpgradeComponents(Optional.empty(), Optional.empty(),
            Optional.empty(), Optional.empty());

    public static final boolean isEmpty(UpgradeComponents c) {
        return c.speed().isEmpty() &&
                c.energy().isEmpty() &&
                c.luck().isEmpty() &&
                c.fluid_usage().isEmpty();
    }

    public static final boolean has(UpgradeComponents c, UpgradeType type) {
        return c == null ? false : !getAll(c).get(type.value()).isEmpty();
    }

    public static final int get(UpgradeComponents c, UpgradeType type) {
        return c == null ? 0 : getAll(c).get(type.value()).get();
    }

    public static final boolean has(ItemStack i, UpgradeType type) {
        return has(i.get(zComponents.UPGRADE_COMPONENTS), type);
    }

    public static final int get(ItemStack i, UpgradeType type) {
        return get(i.get(zComponents.UPGRADE_COMPONENTS), type);
    }

    public static final int getStacked(ItemStack i, UpgradeType type) {
        var tot = 0;
        for (int j = 0; j < i.getCount(); j++)
            tot += get(i.get(zComponents.UPGRADE_COMPONENTS), type);
        return tot;
    }

    public static final List<Optional<Integer>> getAll(UpgradeComponents c) {
        return List.of(c.speed(), c.energy(), c.luck(), c.fluid_usage());
    }

    /**
     * Value 0 will set Optional.empty()
     */
    public static final UpgradeComponents builder(int speed, int energy, int luck, int fluid) {
        return new UpgradeComponents(
                speed == 0 ? Optional.empty() : Optional.of(speed),
                energy == 0 ? Optional.empty() : Optional.of(energy),
                luck == 0 ? Optional.empty() : Optional.of(luck),
                fluid == 0 ? Optional.empty() : Optional.of(fluid));
    }

    /**
     * Value 0 will set Optional.empty()
     */
    @Deprecated
    public static ItemStack create(Item i, int speed, int energy, int luck, int fluid) {
        var item = x.item(i);
        item.set(zComponents.UPGRADE_COMPONENTS, builder(speed, energy, luck, fluid));
        return item;
    }

    public enum UpgradeType {

        SPEED(0),
        ENERGY(1),
        LUCK(2),
        FLUID(3);

        private int i;

        UpgradeType(int i) {
            this.i = i;
        }

        public int value() {
            return i;
        }
    }

}