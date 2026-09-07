package com.devdyna.cakesticklib.api.utils;

import java.awt.Color;

import net.minecraft.client.Minecraft;

public class ClientUtils {

    public static final Color defaultToolTipColor = ColorUtils.color(64, 64, 64,255);

    public static boolean hasShiftDown() {
        return Minecraft.getInstance().hasShiftDown();
    }
}
