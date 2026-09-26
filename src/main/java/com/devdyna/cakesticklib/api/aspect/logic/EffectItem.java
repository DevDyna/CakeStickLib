package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.ArrayList;
import java.util.List;
import com.devdyna.cakesticklib.api.utils.ColorUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;

public interface EffectItem {

    List<Component> getEffectToolTip();

    default List<Component> defaultEffectTooltip(List<MobEffectInstance> effects) {
        var list = new ArrayList<Component>();

        for (var instance : effects) {
            var effect = instance.getEffect().value();

            var result = Component.empty()
                    .append(effect.getDisplayName());

            if (instance.getAmplifier() > 0)
                result.append(" ")
                        .append(Component.translatable(
                                "potion.potency." + instance.getAmplifier()));

            result.append(" (")
                    .append(MobEffectUtil.formatDuration(
                            instance, 1.0F,
                            Minecraft.getInstance().level
                                    .tickRateManager()
                                    .tickrate()))
                    .append(")");

            result.withStyle(style -> style.withColor(switch (effect.getCategory()) {
                case BENEFICIAL -> ColorUtils.rgb(85, 85, 255);
                case HARMFUL -> ColorUtils.rgb(255, 85, 85);
                case NEUTRAL -> ColorUtils.rgb(170, 0, 170);
            }));

            list.add(result);
        }

        return list;
    }
}