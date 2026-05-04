package com.devdyna.cakesticklib.api.compat.jei;

import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.TimeUtil;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

/**
 * A basic implementation of a recipe category without requirements to use
 * methods with <code>RecipeHolder</code> and a configurable timer useful on
 * timered recipes
 */
public abstract class BaseRecipeCategory<T extends Recipe<?>>
        extends BaseCategory<RecipeHolder<T>> {

    public BaseRecipeCategory(IGuiHelper h) {
        super(h);
    }

    /**
     * Default : true
     */
    public boolean shortTicks() {
        return true;
    }

    public void renderTickDelay(T recipe, GuiGraphicsExtractor guiGraphics) {
        drawCentredStringFixed(guiGraphics, font,
                Component.literal(TimeUtil.getTimeValue(tickValue(recipe), shortTicks())), tickPos().getX(),
                tickPos().getY(), tickColor(), false);
    }
 /**
     * Default : 0
     */
    public int tickValue(T recipe) {
        return 0;
    }
 /**
     * Default : 21 | 14
     */
    public Size tickPos() {
        return Size.of(21, 14);
    }
 /**
     * Default : 0xA0A0A0 (160,160,160) Color.GRAY.getRGB()
     */
    public int tickColor() {
        return 0xA0A0A0;
    }
}