package com.devdyna.cakesticklib.api.upgrades.modifiers;

import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors.Modifiers;
import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents;
import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier;
import com.devdyna.cakesticklib.setup.registry.LibComponents;

import net.minecraft.world.item.ItemStack;

public class ModifierUtils {

    public static final boolean isEmpty(UpgradeComponents c) {
        return c.speed().isEmpty() &&
                c.energy().isEmpty() &&
                c.luck().isEmpty() &&
                c.fluid_usage().isEmpty() &&
                c.eject().isEmpty();
    }

    public static final boolean itemValid(ItemStack item) {
        return item.has(LibComponents.UPGRADE_COMPONENTS) && !isEmpty(item.get(LibComponents.UPGRADE_COMPONENTS));
    }

    public static final boolean has(UpgradeComponents c, UpgradeType type) {
        return c == null ? false : !getAll(c).get(type.value()).isEmpty();
    }

    public static final boolean has(ItemStack i, UpgradeType type) {
        return has(i.get(LibComponents.UPGRADE_COMPONENTS), type);
    }

      public static final <T extends BaseModifier> T get(UpgradeComponents c, UpgradeType type) {
        return (T) switch (type) {
            case SPEED -> c.speed().orElse(null);
            case ENERGY -> c.energy().orElse(null);
            case LUCK -> c.luck().orElse(null);
            case FLUID -> c.fluid_usage().orElse(null);
            case EJECT -> c.eject().orElse(null);
            default -> null;
        };
    }

    public static final <T extends BaseModifier> T get(ItemStack i, UpgradeType type) {
        return get(i.get(LibComponents.UPGRADE_COMPONENTS), type);
    }

    public static final List<Optional<? extends BaseModifier>> getAll(UpgradeComponents c) {
        return List.of(c.speed(), c.energy(), c.luck(), c.fluid_usage(), c.eject());
    }


    public static final UpgradeComponents create(int speed, int energy, int luck, int fluid, DirectionalModifier eject) {
        return new UpgradeComponents(
                speed == 0 ? Optional.empty() : Optional.of(NumericModifier.of(speed)),
                energy == 0 ? Optional.empty() : Optional.of(NumericModifier.of(energy)),
                luck == 0 ? Optional.empty() : Optional.of(NumericModifier.of(luck)),
                fluid == 0 ? Optional.empty() : Optional.of(NumericModifier.of(fluid)),
                Optional.ofNullable(eject));
    }

    public static final ItemStack modify(ItemStack i, UpgradeType type, Object value) {
        i.set(LibComponents.UPGRADE_COMPONENTS, modify(i.get(LibComponents.UPGRADE_COMPONENTS), type, value));
        return i;
    }

    public static final UpgradeComponents modify(UpgradeComponents c, UpgradeType type, Object value) {

        if (c == null)
            c = UpgradeComponents.EMPTY;

        return switch (type) {
            case SPEED -> new UpgradeComponents(
                    Optional.ofNullable((NumericModifier) value),
                    c.energy(),
                    c.luck(),
                    c.fluid_usage(),
                    c.eject());

            case ENERGY -> new UpgradeComponents(
                    c.speed(),
                    Optional.ofNullable((NumericModifier) value),
                    c.luck(),
                    c.fluid_usage(),
                    c.eject());

            case LUCK -> new UpgradeComponents(
                    c.speed(),
                    c.energy(),
                    Optional.ofNullable((NumericModifier) value),
                    c.fluid_usage(),
                    c.eject());

            case FLUID -> new UpgradeComponents(
                    c.speed(),
                    c.energy(),
                    c.luck(),
                    Optional.ofNullable((NumericModifier) value),
                    c.eject());

            case EJECT -> new UpgradeComponents(
                    c.speed(),
                    c.energy(),
                    c.luck(),
                    c.fluid_usage(),
                    Optional.ofNullable((DirectionalModifier) value));
        };
    }


    public static String getColoredTip(boolean condition, boolean fallback, boolean positivity) {
        return fallback
                ? Modifiers.NEUTRAL
                : (condition
                        ? (Modifiers.POSITIVE + (positivity ? "+" : ""))
                        : (Modifiers.NEGATIVE + (positivity ? "" : "+")));
    }


   
}
