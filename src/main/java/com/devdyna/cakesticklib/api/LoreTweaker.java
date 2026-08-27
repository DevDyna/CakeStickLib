package com.devdyna.cakesticklib.api;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;

public class LoreTweaker {
    /**
     * Add lore to this item allowing the customization of the component style
     */
    public static void advancedLore(ItemStack item, Component component,Style style) {
        List<Component> tip = List
                .of(component);

        item.set(DataComponents.LORE, new ItemLore(tip,
                Lists.transform(tip, c -> ComponentUtils.mergeStyles(c,  style))));
    }
}
