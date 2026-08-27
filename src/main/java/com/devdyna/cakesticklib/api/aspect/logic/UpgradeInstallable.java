package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.setup.Config;
import com.devdyna.cakesticklib.setup.registry.LibComponents;
import com.devdyna.cakesticklib.setup.registry.builders.IndustrialUpgrade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public interface UpgradeInstallable {

    boolean tryAddUpgrade(ItemStack item);

    Level getLevel();

    BlockPos getBlockPos();

    ItemStacksResourceHandler getUpgradeItemStorage();

    List<Integer> getUpgradeSlots();

    default List<ItemStack> getUpgradeInstalled() {
        return getUpgradeSlots().stream()
                .map(s -> getUpgradeItemStorage().getResource(s).toStack(getUpgradeItemStorage().getAmountAsInt(s)))
                .filter(i -> i.getItem() instanceof IndustrialUpgrade)
                .filter(i -> i.get(LibComponents.UPGRADE_COMPONENTS) != null)
                .toList();
    }

    default <T> List<T> getValues(UpgradeType type) {
        List<ItemStack> upgrades = getUpgradeInstalled().stream()
                .filter(i -> UpgradeComponents.has(i, type))
                .toList();

        List<T> validSlots = new ArrayList<>();
        int maxRoll = getTypeLimiter(type);

        for (int i = 0; i < upgrades.size() && validSlots.size() < maxRoll; i++)
            for (int j = 0; j < upgrades.get(i).getCount() && validSlots.size() < maxRoll; j++)
                validSlots.add((T) UpgradeComponents.get(upgrades.get(i), type));

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
        if (type.equals(UpgradeType.EJECT))
            return 1;// it could break easily if has multiple directions
        return Integer.MAX_VALUE;
    }

    default int calculateMaxProgress(int base) {
        List<Integer> upgrades = getValues(UpgradeType.SPEED);
        float sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_SPEED_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_TICK_DELAY.get(),
                        (int) (base - (base * sum / 100)));
    }

    default int calculateFEUsage(int base) {
        List<Integer> upgrades = getValues(UpgradeType.ENERGY);
        float sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_ENERGY_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_FE_COST.get(),
                        (int) (base + (base * sum / 100)));
    }

    default int calculateMBUsage(int base) {
        List<Integer> upgrades = getValues(UpgradeType.FLUID);
        float sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_FLUID_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_FLUID_COST.get(),
                        (int) (base + (base * sum / 100f)));
    }

    default boolean calculateSecondarySuccess(float base) {
        List<Integer> upgrades = getValues(UpgradeType.LUCK);
        float sum = upgrades == null ? 0 : upgrades.stream().mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_LUCK_UPGRADES_TYPE.get() == 0 ? false
                : getLevel().getRandom().nextFloat() < Math.min(
                        Config.MACHINE_MAXIMAL_LUCK.get(),
                        (base + (sum / 100)));
    }

    default void tryToEject(ItemStacksResourceHandler storage, boolean dropInWorld, int... slots) {
        if (storage == null || slots == null || slots.length == 0)
            return;

        var level = getLevel();
        var facing = (Direction) getValues(UpgradeType.EJECT).getFirst();
        var outputPos = getBlockPos().relative(facing);
        var output = level.getCapability(Capabilities.Item.BLOCK, outputPos, facing.getOpposite());

        for (var slot : slots) {
            if (slot < 0 || slot >= storage.size())
                continue;

            var resource = storage.getResource(slot);
            if (resource.isEmpty())
                continue;

            var amount = storage.getAmountAsInt(slot);
            var inserted = 0;

            // insert to output
            if (output != null) 
                try (var tx = Transaction.openRoot()) {
                    inserted = output.insert(resource, amount, tx);
                    if (inserted > 0)
                        tx.commit();
                }
            

            var remaining = amount - inserted;

            // drop in world
            if (dropInWorld && remaining > 0) {
                Containers.dropItemStack( level, outputPos.getX() + 0.5D,  outputPos.getY() + 0.5D,  outputPos.getZ() + 0.5D, resource.toStack(remaining));
                inserted = amount;
            }

            // remove items inserted or dropped
            if (inserted > 0) {
                try (var tx = Transaction.openRoot()) {
                    if (storage.extract(slot, resource, inserted, tx) > 0)
                        tx.commit();
                }
            }
        }
    }
}
