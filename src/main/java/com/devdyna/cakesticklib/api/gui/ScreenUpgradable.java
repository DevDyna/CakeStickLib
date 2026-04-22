package com.devdyna.cakesticklib.api.gui;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.*;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.utils.UpgradeSlotBuilder;
import com.devdyna.cakesticklib.setup.Config;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public interface ScreenUpgradable {

    abstract Font getFont();

    abstract UpgradeSlotBuilder getSlotBuilder();

    default void renderToolTips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        getSlotBuilder().getAll().forEach((k, v) -> {
            if (v.getItem().isEmpty())
                if (v.getPos().test(mouseX, mouseY))
                    graphics.setComponentTooltipForNextFrame(getFont(), calculateTooltipUpgrades(), mouseX,
                            mouseY);
        });
    }

    private List<Component> calculateTooltipUpgrades() {
        List<Component> result = new ArrayList<>();

        result.add(Component.translatable(MODULE_ID + ".screen.upgrades"));
        for (UpgradeType upgrade : validUpgrades())
            result.add(Component
                    .translatable(MODULE_ID + ".screen.modifier." + upgrade.name().toLowerCase(),
                            getConfigLimits(upgrade))
                    .withStyle(getConfigLimits(upgrade) > getInstalledUpgradesOnSlots(upgrade)
                            ? ChatFormatting.GREEN
                            : (getConfigLimits(upgrade) < getInstalledUpgradesOnSlots(
                                    upgrade) ? ChatFormatting.RED
                                            : ChatFormatting.YELLOW)));

        return result;
    }

    default List<UpgradeType> validUpgrades() {
        return DEFAULT_UPGRADES;
    }

    public static final List<UpgradeType> DEFAULT_UPGRADES = List.of(UpgradeType.ENERGY, UpgradeType.SPEED);

    default int getConfigLimits(UpgradeType type) {
        return switch (type) {
            case UpgradeType.ENERGY -> Config.MACHINE_MAX_ENERGY_UPGRADES_TYPE.get();
            case UpgradeType.SPEED -> Config.MACHINE_MAX_SPEED_UPGRADES_TYPE.get();
            case UpgradeType.LUCK -> Config.MACHINE_MAX_LUCK_UPGRADES_TYPE.get();
            case UpgradeType.FLUID -> Config.MACHINE_MAX_FLUID_UPGRADES_TYPE.get();
            default -> 0;
        };
    }

    default int getInstalledUpgradesOnSlots(UpgradeType type) {
        return getSlotBuilder()
                .toItems()
                .stream()
                .filter(item -> UpgradeComponents.has(item, type))
                .mapToInt(ItemStack::getCount)
                .sum();
    }

}
