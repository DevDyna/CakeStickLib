package com.devdyna.cakesticklib.api.upgrades;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.*;

import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.modifiers.DirectionalModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.ModifierUtils;
import com.devdyna.cakesticklib.api.upgrades.modifiers.NumericModifier;
import com.devdyna.cakesticklib.api.utils.LogicOperator;
import com.devdyna.cakesticklib.api.utils.UpgradeSlotBuilder;
import com.devdyna.cakesticklib.setup.Config;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public interface ScreenUpgradable {

    abstract Font getFont();

    abstract UpgradeSlotBuilder getSlotBuilder();

    default boolean hideStatsWhenHoldUpgrade() {
        return false;
    }

    abstract <T extends AbstractContainerMenu> T getMenu();

    @Deprecated
    default void renderToolTips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        renderToolTips(graphics, mouseX, mouseY, true);
    }

    default void renderToolTips(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean enableWhen) {

        getSlotBuilder().getAll().forEach((k, v) -> {
            if (enableWhen)
                if (v.getPos().test(mouseX, mouseY))
                    if (v.getItem().isEmpty())
                        graphics.setComponentTooltipForNextFrame(getFont(), (((hideStatsWhenHoldUpgrade()
                                ? (getItem().isEmpty() || !ModifierUtils.itemValid(getItem().get()))
                                : true)) ? calculateTooltipUpgrades() : calculateTooltipStatsHolder()), mouseX, mouseY);

        });
    }

    private Optional<ItemStack> getItem() {
        return Optional.ofNullable(getMenu().getCarried());
    }

    default boolean defineOverflow(UpgradeType upgrade) {
        return getConfigLimits(upgrade) < getInstalledUpgradesOnSlots(upgrade) + Math.min(4, getItem().get().count());
    }

    default String getStatsValue(UpgradeType upgrade) {
        var item = getItem().get();
        var modifier = ModifierUtils.get(item, upgrade);
        return (modifier instanceof DirectionalModifier)
                ? TipColors.GREEN + "+" + item.count() + " Eject"// TODO rework
                : ModifierUtils.getColoredTip(
                        (whenInvertColorStats(upgrade))
                                ? (((NumericModifier) modifier).value() > 0)
                                : (((NumericModifier) modifier).value() < 0),
                        ((NumericModifier) modifier).value() == 0, whenInvertColorStats(upgrade))
                        + ((NumericModifier) modifier).value() * Math.min(4, item.count()) + "%";
    }

    default boolean whenInvertColorStats(UpgradeType u) {
        return u.is(LogicOperator.OR, UpgradeType.LUCK, UpgradeType.SPEED);
    }

    default List<Component> calculateTooltipStatsHolder() {
        var item = getItem().get();
        List<Component> result = new ArrayList<>();

        for (var upgrade : validUpgrades())
            if (ModifierUtils.has(item, upgrade))
                result.add(defineOverflow(upgrade)
                        ? Component
                                .translatable(MODULE_ID + ".screen.modifier.overflow." + upgrade.name().toLowerCase())
                        : Component
                                .translatable(MODULE_ID + ".screen.modifier." + upgrade.name().toLowerCase(),
                                        getStatsValue(upgrade)));
        return result;

    }

    default List<Component> calculateTooltipUpgrades() {
        List<Component> result = new ArrayList<>();

        result.add(Component.translatable(MODULE_ID + ".screen.upgrades"));
        for (UpgradeType upgrade : validUpgrades())
            result.add(Component
                    .translatable(MODULE_ID + ".screen.modifier." + upgrade.name().toLowerCase(),
                            getConfigLimits(upgrade))
                    .withStyle(getConfigLimits(upgrade) > getInstalledUpgradesOnSlots(upgrade)
                            ? ChatFormatting.GREEN
                            : (getConfigLimits(upgrade) < getInstalledUpgradesOnSlots(upgrade)
                                    ? ChatFormatting.RED
                                    : ChatFormatting.YELLOW)));

        return result;
    }

    default List<UpgradeType> validUpgrades() {
        return DEFAULT_UPGRADES;
    }

    public static final List<UpgradeType> DEFAULT_UPGRADES = List.of(UpgradeType.ENERGY, UpgradeType.SPEED,
            UpgradeType.EJECT);

    default int getConfigLimits(UpgradeType type) {
        return switch (type) {
            case UpgradeType.ENERGY -> Config.MACHINE_MAX_ENERGY_UPGRADES_TYPE.get();
            case UpgradeType.SPEED -> Config.MACHINE_MAX_SPEED_UPGRADES_TYPE.get();
            case UpgradeType.LUCK -> Config.MACHINE_MAX_LUCK_UPGRADES_TYPE.get();
            case UpgradeType.FLUID -> Config.MACHINE_MAX_FLUID_UPGRADES_TYPE.get();
            case UpgradeType.EJECT -> 16;
            default -> 0;
        };
    }

    default int getInstalledUpgradesOnSlots(UpgradeType type) {
        return getSlotBuilder()
                .toItems()
                .stream()
                .filter(item -> ModifierUtils.has(item, type))
                .mapToInt(ItemStack::getCount)
                .sum();
    }

}
