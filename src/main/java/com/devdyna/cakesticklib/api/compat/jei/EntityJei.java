package com.devdyna.cakesticklib.api.compat.jei;

import java.util.function.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class EntityJei {

    public static <ENTITY extends LivingEntity> void renderEntity(EntityType<ENTITY> type,
            GuiGraphicsExtractor g, int x, int y, int xo, int yo, double mouseX,
            double mouseY) {
        renderEntity(type, g, x, y, xo, yo, e -> e, mouseX, mouseY);
    }

    public static <ENTITY extends LivingEntity> void renderEntity(EntityType<ENTITY> type,
            GuiGraphicsExtractor g, int x, int y, int xo, int yo, Function<ENTITY, ENTITY> c, double mouseX,
            double mouseY) {

        var level = Minecraft.getInstance().level;

        if (level == null)
            return;

        var entity = type.create(level, EntitySpawnReason.EVENT);

        if (entity == null)
            return;

        entity = c.apply(entity);

        xo = Math.max(xo, 1);
        yo = Math.max(yo, 1);

        var pose = g.pose();
        var left = pose.m20();
        var top = pose.m21();

        var x1 = (int) left + x;
        var y1 = (int) top + y;
        var x2 = (int) left + xo + x;
        var y2 = (int) top + yo + y;

        var entH = entity.getBbHeight();
        var entW = entity.getBbWidth();

        var scale = Mth.clamp(
                Math.min((y2 - y1) / entH, (x2 - x1) / Mth.sqrt(entW * entW + entH * entH)), 6.0F, 18.0F);

        InventoryScreen.extractEntityInInventoryFollowsMouse(
                g,
                x1, y1, x2, y2,
                (int) scale,
                ((y2 - y1) - entH * scale) / 2 / scale,
                (int) (mouseX + left), (int) (mouseY + top),
                entity);
    }
}
