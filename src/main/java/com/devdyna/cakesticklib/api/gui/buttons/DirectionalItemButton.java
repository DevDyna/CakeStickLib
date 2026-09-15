package com.devdyna.cakesticklib.api.gui.buttons;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;
import java.util.function.BiConsumer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class DirectionalItemButton extends ItemButton {

    private final Direction direction;
    private BiConsumer<GuiGraphicsExtractor, Boolean> sprites;

    public DirectionalItemButton(int x, int y, int width, int height, Direction dir, boolean flag, OnPress onPress) {
        super(x, y, width, height, onPress, Component.translatable(MODULE_ID + ".widgets.button." + dir.name()));

        this.direction = dir;
        setPress(flag);
    }

    public Direction getDirection() {
        return direction;
    }

    public DirectionalItemButton defineSprites(BiConsumer<GuiGraphicsExtractor, Boolean> clicked) {
        this.sprites = clicked;
        return this;
    }

    public void draw(GuiGraphicsExtractor graphics, Identifier rl) {
        graphics.blit(RenderPipelines.GUI_TEXTURED,
                rl,
                getX(), getY(),
                0, 0,
                getWidth(), getHeight(), getWidth(), getHeight());
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        sprites.accept(graphics, isClicked());

        if (!item.isEmpty())
            graphics.item(item, getX(), getY());

        setComponents(List.of(
                Component.translatable(item.getItem().getDescriptionId()),
                Component.literal(direction.name())));

        super.extractContents(graphics, mouseX, mouseY, partialTick);

    }

}