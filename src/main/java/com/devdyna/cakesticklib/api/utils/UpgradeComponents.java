package com.devdyna.cakesticklib.api.utils;

import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors.Modifiers;
import com.devdyna.cakesticklib.setup.registry.LibComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record UpgradeComponents(
        Optional<Integer> speed,
        Optional<Integer> energy,
        Optional<Integer> luck,
        Optional<Integer> fluid_usage,
        Optional<Direction> eject) {
    public static final Codec<UpgradeComponents> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.INT.optionalFieldOf("speed").forGetter(UpgradeComponents::speed),
            Codec.INT.optionalFieldOf("energy").forGetter(UpgradeComponents::energy),
            Codec.INT.optionalFieldOf("luck").forGetter(UpgradeComponents::luck),
            Codec.INT.optionalFieldOf("fluid").forGetter(UpgradeComponents::fluid_usage),
            Direction.CODEC.optionalFieldOf("eject").forGetter(UpgradeComponents::eject))
            .apply(i, UpgradeComponents::new));

    public static final StreamCodec<FriendlyByteBuf, UpgradeComponents> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::speed,
            ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::energy,
            ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::luck,
            ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::fluid_usage,
            ByteBufCodecs.optional(Direction.STREAM_CODEC), UpgradeComponents::eject,
            UpgradeComponents::new);

    public static final UpgradeComponents EMPTY = new UpgradeComponents(Optional.empty(), Optional.empty(),
            Optional.empty(), Optional.empty(), Optional.empty());

    public static final boolean isEmpty(UpgradeComponents c) {
        return c.speed().isEmpty() &&
                c.energy().isEmpty() &&
                c.luck().isEmpty() &&
                c.fluid_usage().isEmpty() &&
                c.eject().isEmpty();
    }

    public static final boolean has(UpgradeComponents c, UpgradeType type) {
        return c == null ? false : !getAll(c).get(type.value()).isEmpty();
    }

    public static final boolean has(ItemStack i, UpgradeType type) {
        return has(i.get(LibComponents.UPGRADE_COMPONENTS), type);
    }

    public static final <T> T get(UpgradeComponents c, UpgradeType type) {
        return (T) switch (type) {
            case SPEED -> c.speed().orElse(null);
            case ENERGY -> c.energy().orElse(null);
            case LUCK -> c.luck().orElse(null);
            case FLUID -> c.fluid_usage().orElse(null);
            case EJECT -> c.eject().orElse(null);
            default -> null;
        };
    }

    public static final <T> T get(ItemStack i, UpgradeType type) {
        return get(i.get(LibComponents.UPGRADE_COMPONENTS), type);
    }

    public static final List<Optional<?>> getAll(UpgradeComponents c) {
        return List.of(c.speed(), c.energy(), c.luck(), c.fluid_usage(), c.eject());
    }

    /**
     * Value 0 will set Optional.empty()
     */
    public static final UpgradeComponents builder(int speed, int energy, int luck, int fluid, Direction eject) {
        return new UpgradeComponents(
                speed == 0 ? Optional.empty() : Optional.of(speed),
                energy == 0 ? Optional.empty() : Optional.of(energy),
                luck == 0 ? Optional.empty() : Optional.of(luck),
                fluid == 0 ? Optional.empty() : Optional.of(fluid),
                Optional.ofNullable(eject));
    }

    

    public static final ItemStack modify(ItemStack i, UpgradeType type, Object value) {
        i.set(LibComponents.UPGRADE_COMPONENTS,modify(i.get(LibComponents.UPGRADE_COMPONENTS), type, value));
        return i;
    }
   
        public static final UpgradeComponents modify(UpgradeComponents c, UpgradeType type, Object value) {

        if (c == null)
            c = EMPTY;

        return switch (type) {
            case SPEED -> new UpgradeComponents(
                    Optional.ofNullable((Integer) value),
                    c.energy(),
                    c.luck(),
                    c.fluid_usage(),
                    c.eject());

            case ENERGY -> new UpgradeComponents(
                    c.speed(),
                    Optional.ofNullable((Integer) value),
                    c.luck(),
                    c.fluid_usage(),
                    c.eject());

            case LUCK -> new UpgradeComponents(
                    c.speed(),
                    c.energy(),
                    Optional.ofNullable((Integer) value),
                    c.fluid_usage(),
                    c.eject());

            case FLUID -> new UpgradeComponents(
                    c.speed(),
                    c.energy(),
                    c.luck(),
                    Optional.ofNullable((Integer) value),
                    c.eject());

            case EJECT -> new UpgradeComponents(
                    c.speed(),
                    c.energy(),
                    c.luck(),
                    c.fluid_usage(),
                    Optional.ofNullable((Direction) value));
        };
    }

    /**
     * Value 0 will set Optional.empty()
     */
    @Deprecated
    public static ItemStack create(Item i, int speed, int energy, int luck, int fluid, Direction eject) {
        var item = x.item(i);
        item.set(LibComponents.UPGRADE_COMPONENTS, builder(speed, energy, luck, fluid, eject));
        return item;
    }

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
    }

    public static String getColoredTip(boolean condition, boolean fallback, boolean positivity) {
        return fallback
                ? Modifiers.NEUTRAL
                : (condition
                        ? (Modifiers.POSITIVE + (positivity ? "+" : ""))
                        : (Modifiers.NEGATIVE + (positivity ? "" : "+")));
    }

}