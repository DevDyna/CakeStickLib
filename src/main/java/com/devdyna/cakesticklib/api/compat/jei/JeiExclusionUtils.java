package com.devdyna.cakesticklib.api.compat.jei;

import java.util.*;
import java.util.function.Function;

import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.Rect2i;

public class JeiExclusionUtils {

    public static <T extends AbstractContainerScreen<?>> void addExclusionJEIArea(IGuiHandlerRegistration r,
            Class<T> clazz, Function<T, List<Rect2i>> shape) {

        r.addGuiContainerHandler(clazz, new IGuiContainerHandler<T>() {
            @Override
            public List<Rect2i> getGuiExtraAreas(T screen) {
                return shape.apply(screen);
            }
        });

    }
}
