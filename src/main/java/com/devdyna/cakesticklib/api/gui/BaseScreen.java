package com.devdyna.cakesticklib.api.gui;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public abstract class BaseScreen<T extends BaseMenu> extends AbstractContainerScreen<T> implements ClientUtils {

    public BaseScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    protected abstract Identifier background();

    protected @Nullable Identifier arrow() {
        return null;
    }

    public static final Identifier furnace_arrow = x.mcLoc("container/furnace/burn_progress");

    protected boolean whenAnimateArrow() {
        return false;
    }

    protected int getScaledArrowProgress() {
        return 0;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI, background(), getLeftPos(), getTopPos(), 0, 0, this.imageWidth,
                this.imageHeight, 256, 256);
        renderArrow(graphics);
    }

    protected void renderArrow(GuiGraphicsExtractor guiGraphics) {
        if (arrow() != null && whenAnimateArrow())
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                    arrow(),
                    24, 16,
                    0, 0,
                    getLeftPos() + 73, getTopPos() + 35,
                    getScaledArrowProgress(), 16);
    }

}
