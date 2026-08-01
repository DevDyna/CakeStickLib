package com.devdyna.cakesticklib.api;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ToolTipHelper {
    public static final int INDEX = 1;

    public static void addToggle(List<Component> t, boolean c, String... k) {
        if (c)
            if (!Minecraft.getInstance().hasShiftDown())
                add(t, MODULE_ID + ".hold.shift");
            else
                add(t, k);

    }

    public static void add(List<Component> t, String... k) {
        for (String s : k)
            t.add(INDEX, Component.translatable(s));
    }

}
