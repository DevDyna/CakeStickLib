package com.devdyna.cakesticklib.api.gui.buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ItemButton extends Button {

    protected ItemStack item = ItemStack.EMPTY;
    protected List<Component> components = new ArrayList<>();
    private boolean default_rendering = false;
    private boolean default_press = false;
    private boolean press = default_press;
    private Consumer<GuiGraphicsExtractor> onPressRender = _ -> {
    };

    public ItemButton(int x, int y, int width, int height, OnPress onPress, Component name) {
        super(x, y, width, height, name, onPress, DEFAULT_NARRATION);
    }

    public void setStack(ItemStack i) {
        this.item = i;
    }

    public void setStack(BlockState s) {
        this.item = x.item(s);
    }

    public void setComponents(List<Component> c) {
        this.components = c;
    }

    public boolean isClicked() {
        return press;
    }

    public void reset() {
        setPress(default_press);
    }

    public void setPress(boolean b) {
        this.press = b;
    }

    public void setDefaultPressState(boolean s) {
        this.default_press = s;
    }

    public void onButtonPress(Consumer<GuiGraphicsExtractor> f) {
        this.onPressRender = f;
    }

    public void renderImage(GuiGraphicsExtractor graphics, Identifier rl) {
        graphics.blit(RenderPipelines.GUI_TEXTURED,
                rl,
                getX(), getY(),
                0, 0,
                getWidth(), getHeight(), getWidth(), getHeight());
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        if (default_rendering)
            extractDefaultSprite(graphics);

        onPressRender.accept(graphics);

        if (!item.isEmpty()) {
            int itemX = getX() + (width - 16) / 2;
            int itemY = getY() + (height - 16) / 2;

            graphics.item(item, itemX, itemY);
        }

        if (isMouseOver(mouseX, mouseY) && !components.isEmpty())
            graphics.setComponentTooltipForNextFrame(Minecraft.getInstance().font, components, mouseX, mouseY);

    }

    public void enableDefaultRender() {
        this.default_rendering = true;
    }

}