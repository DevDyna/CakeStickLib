package com.devdyna.cakesticklib.api.gui;

import java.awt.Color;

import com.devdyna.cakesticklib.api.utils.ColorUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public interface ClientUtils {

    final Color defaultToolTipColor = ColorUtils.color(64, 64, 64,255);

    default boolean hasShiftDown() {
        return Minecraft.getInstance().hasShiftDown();
    }

    default void drawCenteredString(GuiGraphicsExtractor pGuiGraphics, Font font, Component text, int x, int y,
            int color, boolean dropShadow) {
        var f = text.getVisualOrderText();
        pGuiGraphics.text(font, f, x - font.width(f) / 2, y, color, dropShadow);
    }

}
