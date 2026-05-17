package com.devdyna.cakesticklib.api.animations;

import java.util.List;

import com.devdyna.cakesticklib.api.gui.ImageGui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

/**
 * Utility render class based on
 * {@code net.minecraft.client.gui.screens.inventory.CyclingSlotBackground}
 */
public class CyclicImageGui {

    private final int slotIndex;
    private List<ImageGui> icons = List.of();
    private int tick;
    private int iconIndex;
    private int tickDelay = 30;

    public CyclicImageGui(int slotIndex, int delay) {
        this.slotIndex = slotIndex;
        this.tickDelay = delay;
    }

    /**
     * @param newIcons must contain a list of {@code ImageGui} that should an
     *                 {@code Identifier} , SpriteSize , Offset and Texture Size
     */
    public void tick(List<ImageGui> newIcons) {
        if (!this.icons.equals(newIcons)) {
            this.icons = newIcons;
            this.iconIndex = 0;
        }

        if (!this.icons.isEmpty() && ++this.tick % tickDelay == 0)
            this.iconIndex = (this.iconIndex + 1) % this.icons.size();

    }

    public void extractRenderState(AbstractContainerMenu menu, GuiGraphicsExtractor graphics, float partialTicks) {
        Slot slot = menu.getSlot(this.slotIndex);
        if (!this.icons.isEmpty() && !slot.hasItem()) {
            float alphaProgress = (this.icons.size() > 1 && this.tick >= tickDelay)
                    ? (Math.min((this.tick % tickDelay) + partialTicks, 4.0F) / 4.0F)
                    : 1.0F;
            if (alphaProgress < 1.0F) 
                extractIcon(slot, this.icons.get(Math.floorMod(this.iconIndex - 1, this.icons.size())),
                        1.0F - alphaProgress, graphics);
            

            extractIcon(slot, this.icons.get(this.iconIndex), alphaProgress, graphics);
        }

    }

    private void extractIcon(Slot slot, ImageGui image, float alphaProgress, GuiGraphicsExtractor graphics) {
        image.color(ARGB.white(alphaProgress)).render(graphics);
    }

}