package com.devdyna.cakesticklib.api.client;

import com.devdyna.cakesticklib.api.aspect.logic.ItemResourceDecorated;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.IItemDecorator;

public class BlockRenderDecorator implements IItemDecorator {

    public BlockRenderDecorator() {
    }

    @Override
    public boolean render(GuiGraphicsExtractor guiGraphics, Font font, ItemStack item, int xOffset, int yOffset) {
        if (item.getItem() instanceof ItemResourceDecorated deco) {

            var rl = deco.getResource(item);

            if (rl == null)
                return false;

            Block block = x.getBlock(rl);

            Item renderer = Items.BARRIER;

            if (block.asItem() != null && block.asItem() != Items.AIR)
                renderer = block.asItem();

            var stack = guiGraphics.pose();

            stack.pushMatrix();

            stack.scale(0.5f, 0.5f);

            stack.translate(xOffset * 2f, yOffset * 2f);

            var pos = deco.getPos();

            guiGraphics.fakeItem(x.item(renderer), pos.getX(), pos.getY());

            stack.popMatrix();

            return true;
        }

        return false;

    }

}
