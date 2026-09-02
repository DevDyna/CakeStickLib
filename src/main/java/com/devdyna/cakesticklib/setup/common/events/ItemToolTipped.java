package com.devdyna.cakesticklib.setup.common.events;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.ToolTipHelper;
import com.devdyna.cakesticklib.api.aspect.logic.BlockItemKeeper;
import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.modifiers.DirectionalModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.ModifierUtils;
import com.devdyna.cakesticklib.api.upgrades.modifiers.NumericModifier;
import com.devdyna.cakesticklib.api.utils.StringUtil;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.builders.CakeStick;
import com.devdyna.cakesticklib.setup.registry.builders.Chisel;
import com.devdyna.cakesticklib.setup.registry.builders.GlassCutter;
import com.devdyna.cakesticklib.setup.registry.builders.HoneySolution;
import com.devdyna.cakesticklib.setup.registry.builders.RedstoneAcid;
import com.devdyna.cakesticklib.setup.registry.builders.Wrench;
import net.minecraft.ChatFormatting;
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

            if (nbt != null && !ModifierUtils.isEmpty(nbt)) {
                tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".upgrades.title"));

                if (ModifierUtils.has(nbt, UpgradeType.ENERGY)) {
                    int energy = ((NumericModifier)ModifierUtils.get(nbt, UpgradeType.ENERGY)).value();
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.energy",
                            (ModifierUtils.getColoredTip(energy < 0, energy == 0, false) + energy + "%")));
                }
                if (ModifierUtils.has(nbt, UpgradeType.SPEED)) {
                    int speed = ((NumericModifier)ModifierUtils.get(nbt, UpgradeType.SPEED)).value();
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.speed",
                            (ModifierUtils.getColoredTip(speed > 0, speed == 0, true) + speed + "%")));
                }
                if (ModifierUtils.has(nbt, UpgradeType.LUCK)) {
                    int luck = ((NumericModifier)ModifierUtils.get(nbt, UpgradeType.LUCK)).value();
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.luck",
                            (ModifierUtils.getColoredTip(luck > 0, luck == 0, true) + luck + "%")));
                }
                if (ModifierUtils.has(nbt, UpgradeType.FLUID)) {
                    int fluid = ((NumericModifier)ModifierUtils.get(nbt, UpgradeType.FLUID)).value();
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.fluid",
                            (ModifierUtils.getColoredTip(fluid < 0, fluid == 0, false) + fluid + "%")));
                }
                if (ModifierUtils.has(nbt, UpgradeType.EJECT)) {
                    var eject = ((DirectionalModifier)ModifierUtils.get(nbt, UpgradeType.EJECT));
                    tip.add(2, Component.translatable(MODULE_ID + ".upgrades.modifier.eject",
                            TipColors.LIGHT_BLUE + StringUtil.nameCapitalized(eject.dir().name()),TipColors.GOLD + StringUtil.nameCapitalized(eject.type().getId())));
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
