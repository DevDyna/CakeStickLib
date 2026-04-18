package com.devdyna.cakesticklib.setup.common.events;

import static com.devdyna.cakesticklib.Main.ID;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.builders.CakeStick;
import com.devdyna.cakesticklib.setup.registry.types.zComponents;
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
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".setup.cakestick.tip"));

        if (item.has(zComponents.UPGRADE_COMPONENTS)) {
            var nbt = item.get(zComponents.UPGRADE_COMPONENTS);

            if (nbt != null && !UpgradeComponents.isEmpty(nbt)) {
                tip.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".upgrades.title"));

                if (UpgradeComponents.has(nbt, UpgradeType.ENERGY)) {
                    var energy = UpgradeComponents.get(nbt, UpgradeType.ENERGY);
                    tip.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".upgrades.modifier.energy",
                            ((energy < 0 ? "§a" : "§c+") + energy + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.SPEED)) {
                    var speed = UpgradeComponents.get(nbt, UpgradeType.SPEED);
                    tip.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".upgrades.modifier.speed",
                            ((speed >= 0 ? "§a+" : "§c") + speed + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.LUCK)) {
                    var luck = UpgradeComponents.get(nbt, UpgradeType.LUCK);
                    tip.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".upgrades.modifier.luck",
                            ((luck > 0 ? "§a+" : "§c") + luck + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.FLUID)) {
                    var fluid = UpgradeComponents.get(nbt, UpgradeType.FLUID);
                    tip.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".upgrades.modifier.fluid",
                            ((fluid < 0 ? "§a" : "§c+") + fluid + "%")));
                }

            }
        }

        if (item.has(zComponents.IDENTIFIER)) {
            tip.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".info.identifier.desc"));
            var nbt = item.get(zComponents.IDENTIFIER);

            if (nbt != null)
                tip.add(OVER_THE_REGISTRY_ID, Component.translatable(ID + ".info.identifier.hold")
                        .append(Component.translatable(x.get(nbt).getDescriptionId())
                                .withStyle(ChatFormatting.GREEN)));

        }

    }
}
