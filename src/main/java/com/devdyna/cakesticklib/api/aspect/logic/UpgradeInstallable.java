package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.setup.registry.zLibrary.zComponents;
import com.devdyna.cakesticklib.setup.Config;
import com.devdyna.cakesticklib.setup.registry.builders.IndustrialUpgrade;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.transfer.item.ItemStackResourceHandler;

public interface UpgradeInstallable {

    boolean tryAddUpgrade(ItemStack item);

    Level getLevel();

    ItemStackResourceHandler getUpgradeItemStorage();

    List<Integer> getUpgradeSlots();

    default List<ItemStack> getUpgradeInstalled() {
        return getUpgradeSlots().stream()
                .map(s -> getUpgradeItemStorage().getResource(s).toStack(getUpgradeItemStorage().getAmountAsInt(s)))
                .filter(i -> i.getItem() instanceof IndustrialUpgrade)
                .filter(i -> i.get(zComponents.UPGRADE_COMPONENTS) != null)
                .toList();
    }

    default List<Integer> getValues(UpgradeType type) {
        List<ItemStack> upgrades = getUpgradeInstalled().stream()
                .filter(i -> UpgradeComponents.has(i, type))
                .toList();

        List<Integer> validSlots = new ArrayList<>();
        int maxRoll = getTypeLimiter(type);

        for (int i = 0; i < upgrades.size() && validSlots.size() < maxRoll; i++)
            for (int j = 0; j < upgrades.get(i).getCount() && validSlots.size() < maxRoll; j++)
                validSlots.add(UpgradeComponents.get(upgrades.get(i), type));

        return validSlots;
    }

    default int getTypeLimiter(UpgradeType type) {
        if (type.equals(UpgradeType.SPEED))
            return Config.MACHINE_MAX_SPEED_UPGRADES_TYPE.get();
        if (type.equals(UpgradeType.ENERGY))
            return Config.MACHINE_MAX_ENERGY_UPGRADES_TYPE.get();
        if (type.equals(UpgradeType.LUCK))
            return Config.MACHINE_MAX_LUCK_UPGRADES_TYPE.get();
        if (type.equals(UpgradeType.FLUID))
            return Config.MACHINE_MAX_FLUID_UPGRADES_TYPE.get();
        return Integer.MAX_VALUE;
    }

    default int calculateMaxProgress(int base) {
        var upgrades = getValues(UpgradeType.SPEED);
        float sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_SPEED_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_TICK_DELAY.get(),
                        (int) (base - (base * sum / 100)));
    }

    default int calculateFEUsage(int base) {
        var upgrades = getValues(UpgradeType.ENERGY);
        float sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_ENERGY_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_FE_COST.get(),
                        (int) (base + (base * sum / 100)));
    }

    default int calculateMBUsage(int base) {
        var upgrades = getValues(UpgradeType.FLUID);
        float sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_FLUID_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_FLUID_COST.get(),
                        (int) (base + (base * sum / 100f)));
    }

    default boolean calculateSecondarySuccess(float base) {
        var upgrades = getValues(UpgradeType.LUCK);
        float sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_LUCK_UPGRADES_TYPE.get() == 0 ? false
                : getLevel().getRandom().nextFloat() < Math.min(
                        Config.MACHINE_MAXIMAL_LUCK.get(),
                        (base + (sum / 100)));
    }

}
