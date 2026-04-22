package com.devdyna.cakesticklib.api.gui;

import java.awt.Color;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.utils.ColorUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;

public abstract class BaseScreen<T extends BaseMenu> extends AbstractContainerScreen<T> {

    public BaseScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    protected abstract Identifier background();

    protected @Nullable Identifier arrow() {
        return null;
    }

    protected boolean whenAnimateArrow() {
        return false;
    }

    protected int getScaledArrowProgress() {
        return 0;
    }

    protected final Color defaultToolTipColor = ColorUtil.color(64, 64, 64);

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI, background(), getLeftPos(), getTopPos(), 0, 0, this.imageWidth,
                this.imageHeight, 256, 256);
        renderArrow(graphics);
    }

    public boolean hasShiftDown() {
        return Minecraft.getInstance().hasShiftDown();
    }

    protected void renderArrow(GuiGraphicsExtractor guiGraphics) {
        if (arrow() != null && whenAnimateArrow())
            guiGraphics.blit(arrow(), getLeftPos() + 73, getTopPos() + 35, 0, 0, getScaledArrowProgress(), 16, 24, 16);
    }

    public void drawCenteredString(GuiGraphicsExtractor pGuiGraphics, Font font, Component text, int x, int y,
            int color, boolean dropShadow) {
        FormattedCharSequence formattedcharsequence = text.getVisualOrderText();
        pGuiGraphics.text(font, formattedcharsequence, x - font.width(formattedcharsequence) / 2, y, color, dropShadow);
    }

}
