package com.devdyna.cakesticklib.setup.common.events;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.builders.CakeStick;
import com.devdyna.cakesticklib.setup.registry.builders.Chisel;
import com.devdyna.cakesticklib.setup.registry.types.zLibComponents;
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
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".setup.cakestick.tip"));

        if (item.has(zLibComponents.UPGRADE_COMPONENTS)) {
            var nbt = item.get(zLibComponents.UPGRADE_COMPONENTS);

            if (nbt != null && !UpgradeComponents.isEmpty(nbt)) {
                tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".upgrades.title"));

                if (UpgradeComponents.has(nbt, UpgradeType.ENERGY)) {
                    var energy = UpgradeComponents.get(nbt, UpgradeType.ENERGY);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.energy",
                            ((energy < 0 ? "§a" : "§c+") + energy + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.SPEED)) {
                    var speed = UpgradeComponents.get(nbt, UpgradeType.SPEED);
                    tip.add(3, Component.translatable(MODULE_ID + ".upgrades.modifier.speed",
                            ((speed >= 0 ? "§a+" : "§c") + speed + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.LUCK)) {
                    var luck = UpgradeComponents.get(nbt, UpgradeType.LUCK);
                    tip.add(4, Component.translatable(MODULE_ID + ".upgrades.modifier.luck",
                            ((luck > 0 ? "§a+" : "§c") + luck + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.FLUID)) {
                    var fluid = UpgradeComponents.get(nbt, UpgradeType.FLUID);
                    tip.add(5, Component.translatable(MODULE_ID + ".upgrades.modifier.fluid",
                            ((fluid < 0 ? "§a" : "§c+") + fluid + "%")));
                }

            }
        }

        if (item.getItem() instanceof Chisel)
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(MODULE_ID + ".info.identifier.desc"));

        if (item.has(zLibComponents.IDENTIFIER)) {
            var nbt = item.get(zLibComponents.IDENTIFIER);

            if (nbt != null)
                tip.add(2, Component.translatable(MODULE_ID + ".info.identifier.hold")
                        .append(Component.translatable(x.get(nbt).getDescriptionId())
                                .withStyle(ChatFormatting.GREEN)));

        }

    }
}
