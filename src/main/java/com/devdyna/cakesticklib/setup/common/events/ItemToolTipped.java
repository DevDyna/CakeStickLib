package com.devdyna.cakesticklib.setup.common.events;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.ToolTipHelper;
import com.devdyna.cakesticklib.api.aspect.logic.BlockItemKeeper;
import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.utils.StringUtil;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.builders.CakeStick;
import com.devdyna.cakesticklib.setup.registry.builders.Chisel;
import com.devdyna.cakesticklib.setup.registry.builders.GlassCutter;
import com.devdyna.cakesticklib.setup.registry.builders.HoneySolution;
import com.devdyna.cakesticklib.setup.registry.builders.RedstoneAcid;
import com.devdyna.cakesticklib.setup.registry.builders.Wrench;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.SpectralArrowItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import com.devdyna.cakesticklib.setup.registry.LibComponents;

public class ItemToolTipped {

    @SubscribeEvent
    public static void main(ItemTooltipEvent event) {

        var item = event.getItemStack();
        var tip = event.getToolTip();

        if (item.getItem() instanceof CakeStick)
            tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".cakestick.tip"));

        if (item.has(LibComponents.UPGRADE_COMPONENTS)) {
            var nbt = item.get(LibComponents.UPGRADE_COMPONENTS);

            if (nbt != null && !UpgradeComponents.isEmpty(nbt)) {
                tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".upgrades.title"));

                if (UpgradeComponents.has(nbt, UpgradeType.ENERGY)) {
                    int energy = UpgradeComponents.get(nbt, UpgradeType.ENERGY);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.energy",
                            (UpgradeComponents.getColoredTip(energy < 0, energy == 0, false) + energy + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.SPEED)) {
                    int speed = UpgradeComponents.get(nbt, UpgradeType.SPEED);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.speed",
                            (UpgradeComponents.getColoredTip(speed > 0, speed == 0, true) + speed + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.LUCK)) {
                    int luck = UpgradeComponents.get(nbt, UpgradeType.LUCK);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.luck",
                            (UpgradeComponents.getColoredTip(luck > 0, luck == 0, true) + luck + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.FLUID)) {
                    int fluid = UpgradeComponents.get(nbt, UpgradeType.FLUID);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.fluid",
                            (UpgradeComponents.getColoredTip(fluid < 0, fluid == 0, false) + fluid + "%")));
                }
                if (UpgradeComponents.has(nbt, UpgradeType.EJECT)) {
                    Direction eject = UpgradeComponents.get(nbt, UpgradeType.EJECT);
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.eject",
                            TipColors.LIGHT_BLUE + StringUtil.nameCapitalized(eject.getName())));
                }

            }
        }

        if (item.getItem() instanceof GlassCutter)
            tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".glass_cutter.tip"));

        if (item.getItem() instanceof Wrench)
            tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".wrench.tip"));

        if (item.getItem() instanceof Chisel)
            tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".info.identifier.desc"));

        if (item.has(LibComponents.IDENTIFIER)) {
            var nbt = item.get(LibComponents.IDENTIFIER);

            if (nbt != null)
                tip.add(2, Component.translatable(MODULE_ID + ".info.identifier.hold")
                        .append(Component.translatable(x.getItem(nbt).getDescriptionId())
                                .withStyle(ChatFormatting.GREEN)));

        }

        if (item.getItem() instanceof RedstoneAcid)
            tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".redstone_acid.tip"));

        if (item.getItem() instanceof HoneySolution)
            tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".honey_solution.tip"));

        if (item.getItem() instanceof BlockItem bi && bi.getBlock() instanceof BlockItemKeeper)
            if (item.has(LibComponents.ITEM_CONTAINER))
                if (item.get(LibComponents.ITEM_CONTAINER) != null)
                    ToolTipHelper.add(tip, MODULE_ID + ".keep.storage.contain");
                else
                    ToolTipHelper.add(tip, MODULE_ID + ".keep.storage.tip");

        if (item.getItem() instanceof SpectralArrowItem)
            tip.add(ToolTipHelper.INDEX, Component.translatable("extra.effect.minecraft.glowing"));

    }
}
