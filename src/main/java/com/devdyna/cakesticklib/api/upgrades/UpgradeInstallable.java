package com.devdyna.cakesticklib.api.upgrades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.modifiers.DirectionalModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.ModifierUtils;
import com.devdyna.cakesticklib.api.upgrades.modifiers.NumericModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier.UseType;
import com.devdyna.cakesticklib.setup.Config;
import com.devdyna.cakesticklib.setup.registry.LibComponents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.StacksResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.resource.Resource;
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
                .filter(i -> i.get(LibComponents.UPGRADE_COMPONENTS) != null)
                .toList();
    }

    default List<ItemStack> getUpgradeInstalled(UpgradeType type) {
        return getUpgradeInstalled().stream()
                .filter(i -> ModifierUtils.has(i, type))
                .toList();
    }

    default <T> List<T> getValues(UpgradeType type) {
        List<ItemStack> upgrades = getUpgradeInstalled(type);

        List<T> validSlots = new ArrayList<>();
        int maxRoll = getTypeLimiter(type);

        for (int i = 0; i < upgrades.size() && validSlots.size() < maxRoll; i++)
            for (int j = 0; j < upgrades.get(i).getCount() && validSlots.size() < maxRoll; j++)
                validSlots.add((T) ModifierUtils.get(upgrades.get(i), type));

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
        // it should be defined only as hardcoded way the limit
        // if (type.equals(UpgradeType.EJECT))
        // return 1;
        return Integer.MAX_VALUE;
    }

    default int calculateMaxProgress(int base) {
        List<NumericModifier> upgrades = getValues(UpgradeType.SPEED);
        float sum = upgrades == null ? 0
                : upgrades.stream().map(NumericModifier::value).mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_SPEED_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_TICK_DELAY.get(),
                        (int) (base - (base * sum / 100)));
    }

    default int calculateFEUsage(int base) {
        List<NumericModifier> upgrades = getValues(UpgradeType.ENERGY);
        float sum = upgrades == null ? 0
                : upgrades.stream().map(NumericModifier::value).mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_ENERGY_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_FE_COST.get(),
                        (int) (base + (base * sum / 100)));
    }

    default int calculateMBUsage(int base) {
        List<NumericModifier> upgrades = getValues(UpgradeType.FLUID);
        float sum = upgrades == null ? 0
                : upgrades.stream().map(NumericModifier::value).mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_FLUID_UPGRADES_TYPE.get() == 0 ? base
                : Math.max(Config.MACHINE_MINIMAL_FLUID_COST.get(),
                        (int) (base + (base * sum / 100f)));
    }

    default boolean calculateSecondarySuccess(float base) {
        List<NumericModifier> upgrades = getValues(UpgradeType.LUCK);
        float sum = upgrades == null ? 0
                : upgrades.stream().map(NumericModifier::value).mapToInt(Integer::intValue).sum();
        return Config.MACHINE_MAX_LUCK_UPGRADES_TYPE.get() == 0 ? false
                : getLevel().getRandom().nextFloat() < Math.min(
                        Config.MACHINE_MAXIMAL_LUCK.get(),
                        (base + (sum / 100)));
    }

    default <RESOURCE extends Resource, STACK> void tryToEject(
            StacksResourceHandler<STACK, RESOURCE> storage,
            int... indexes) {

        if (indexes == null || indexes.length == 0)
            return;

        tryToEject(storage, Arrays.stream(indexes).boxed().toList());
    }

    default <RESOURCE extends Resource, STACK> void tryToEject(
            StacksResourceHandler<STACK, RESOURCE> storage,
            List<Integer> indexes) {

        if (storage == null || indexes == null || indexes.isEmpty())
            return;

        List<DirectionalModifier> modifiers = getValues(UpgradeType.EJECT);

        if (modifiers == null || modifiers.isEmpty())
            return;

        for (var modifier : modifiers) {

            var facing = modifier.dir();

            for (var slot : indexes) {
                if (slot < 0 || slot >= storage.size())
                    continue;

                var resource = storage.getResource(slot);
                if (resource.isEmpty())
                    continue;

                var amount = storage.getAmountAsInt(slot);
                if (amount <= 0)
                    continue;

                var inserted = 0;

                if (modifier.type().equals(UseType.ITEM)) {
                    var output = getLevel().getCapability(
                            Capabilities.Item.BLOCK,
                            getBlockPos().relative(facing),
                            facing.getOpposite());

                    if (output != null)
                        try (var tx = Transaction.openRoot()) {
                            var insert = output.insert((ItemResource) resource, amount, tx);

                            if (insert > 0)
                                tx.commit();

                            inserted = insert;
                        }
                }
                if (modifier.type().equals(UseType.FLUID)) {
                    var output = getLevel().getCapability(
                            Capabilities.Fluid.BLOCK,
                            getBlockPos().relative(facing),
                            facing.getOpposite());

                    if (output != null)
                        try (var tx = Transaction.openRoot()) {
                            var insert = output.insert((FluidResource) resource, amount, tx);

                            if (insert > 0)
                                tx.commit();

                            inserted = insert;
                        }

                }

                if (inserted > 0) {
                    try (var tx = Transaction.openRoot()) {
                        storage.extract(slot, resource, inserted, tx);
                        tx.commit();
                    }
                }
            }

        }
    }

}
