package com.devdyna.cakesticklib.api.gui.buttons;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;

public class DirectionalItemButton extends ItemButton {

    private final Direction direction;
    public DirectionalItemButton(int x, int y, int width, int height, Direction dir, boolean flag, OnPress onPress) {
        super(x, y, width, height, onPress, Component.translatable(MODULE_ID + ".widgets.button." + dir.name()));

        this.direction = dir;
        setPress(flag);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        if (isClicked())
            graphics.blit(RenderPipelines.GUI_TEXTURED,
                    x.rl(MODULE_ID, "textures/gui/modifier/buttons/on.png"),
                    getX(), getY(),
                    0, 0,
                    getWidth(), getHeight(), getWidth(), getHeight());
        else
            graphics.blit(RenderPipelines.GUI_TEXTURED,
                    x.rl(MODULE_ID, "textures/gui/modifier/buttons/off.png"),
                    getX(), getY(),
                    0, 0,
                    getWidth(), getHeight(), getWidth(), getHeight());

        if (!item.isEmpty())
            graphics.item(item, getX(), getY());

        setComponents(List.of(
                Component.translatable(item.getItem().getDescriptionId()),
                Component.literal(direction.name())));

        super.extractContents(graphics, mouseX, mouseY, partialTick);

    }

}