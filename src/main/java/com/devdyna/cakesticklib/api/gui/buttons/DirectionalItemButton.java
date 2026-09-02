package com.devdyna.cakesticklib.api.gui.buttons;


import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class DirectionalItemButton extends Button {

    private final Direction direction;
    private boolean flag;
    private ItemStack item = ItemStack.EMPTY;

    public DirectionalItemButton(int x, int y, int width, int height,
            Direction direction,
            boolean flag, OnPress onPress) {
        super(x, y, width, height, Component.translatable(MODULE_ID + ".widgets.button." + direction.name()), onPress,
                DEFAULT_NARRATION);

        this.direction = direction;
        this.flag = flag;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isPressed() {
        return flag;
    }

    public void update(boolean v) {
        this.flag = v;
    }

    public void setPreviewStack(ItemStack i) {
        this.item = i;
    }

    public void setPreviewStack(BlockState s) {
        setPreviewStack(s.getBlock().asItem() == null
                && !(s.isAir() || s.is(Blocks.BARRIER))
                        ? x.item(Items.BARRIER)
                        : x.item(s));
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        if (flag)
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
            graphics.item(
                    item,
                    getX(),
                    getY());

        if (isMouseOver(mouseX, mouseY))
            graphics.setComponentTooltipForNextFrame(Minecraft.getInstance().font,
                    List.of(
                            Component.translatable(item.getItem().getDescriptionId()),
                            Component.literal(direction.name())),
                    mouseX,
                    mouseY);

    }

}