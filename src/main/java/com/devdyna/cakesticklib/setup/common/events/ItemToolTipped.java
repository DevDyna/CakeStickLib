package com.devdyna.cakesticklib.setup.common.events;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.builders.CakeStick;
import com.devdyna.cakesticklib.setup.registry.builders.Chisel;
import com.devdyna.cakesticklib.setup.registry.builders.GlassCutter;
import com.devdyna.cakesticklib.setup.registry.builders.HoneySolution;
import com.devdyna.cakesticklib.setup.registry.builders.RedstoneAcid;
import com.devdyna.cakesticklib.setup.registry.builders.Wrench;
import com.devdyna.cakesticklib.setup.registry.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ItemToolTipped {
    private static final int OVER_THE_REGISTRY_ID = 1;

    @SubscribeEvent
    public static void main(ItemTooltipEvent event) {

        var item = event.getItemStack();
        var tip = event.getToolTip();

        if (item.getItem() instanceof CakeStick)
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".cakestick.tip"));

        if (item.has(LibComponents.UPGRADE_COMPONENTS)) {
            var nbt = item.get(LibComponents.UPGRADE_COMPONENTS);

            if (nbt != null && !UpgradeComponents.isEmpty(nbt)) {
                tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".upgrades.title"));

                if (UpgradeComponents.has(nbt, UpgradeType.ENERGY)) {
                    var energy = UpgradeComponents.get(nbt, UpgradeType.ENERGY);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.energy",
                            (UpgradeComponents.getColoredTip(energy < 0, energy == 0, false) + energy + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.SPEED)) {
                    var speed = UpgradeComponents.get(nbt, UpgradeType.SPEED);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.speed",
                            (UpgradeComponents.getColoredTip(speed > 0, speed == 0, true) + speed + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.LUCK)) {
                    var luck = UpgradeComponents.get(nbt, UpgradeType.LUCK);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.luck",
                            (UpgradeComponents.getColoredTip(luck > 0, luck == 0, true) + luck + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.FLUID)) {
                    var fluid = UpgradeComponents.get(nbt, UpgradeType.FLUID);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.fluid",
                            (UpgradeComponents.getColoredTip(fluid < 0, fluid == 0, false) + fluid + "%")));
                }

            }
        }

        if (item.getItem() instanceof GlassCutter)
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".glass_cutter.tip"));

        if (item.getItem() instanceof Wrench)
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".wrench.tip"));

        if (item.getItem() instanceof Chisel)
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".info.identifier.desc"));

        if (item.has(LibComponents.IDENTIFIER)) {
            var nbt = item.get(LibComponents.IDENTIFIER);

            if (nbt != null)
                tip.add(2, Component.translatable(MODULE_ID + ".info.identifier.hold")
                        .append(Component.translatable(x.getItem(nbt).getDescriptionId())
                                .withStyle(ChatFormatting.GREEN)));

        }

        if (item.getItem() instanceof RedstoneAcid)
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".redstone_acid.tip"));

        if (item.getItem() instanceof HoneySolution)
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".honey_solution.tip"));

    }
}
