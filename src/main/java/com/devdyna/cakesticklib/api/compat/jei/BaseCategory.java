package com.devdyna.cakesticklib.api.compat.jei;

import java.awt.Color;

import org.jetbrains.annotations.Nullable;

import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtil;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ItemLike;

/**
 * A generic recipe category to show hardcoded implementations without a defined
 * dependency
 */
public abstract class BaseCategory<T> implements IRecipeCategory<T> {

    protected IGuiHelper helper;

    public final Font font = Minecraft.getInstance().font;

    protected final ImageJei backgroundImage;

    public BaseCategory(IGuiHelper h) {
        this.helper = h;
        this.backgroundImage = ImageJei.of()
                .rl(this.setBackGround())
                .size(this.getWidth(), this.getHeight());
    }

    protected final Color defaultToolTipColor = ColorUtil.color(64, 64, 64);

    /**
     * Define the category title
     * <br/>
     * <br/>
     * It must be <code>MODID.jei.KEY</code>
     */
    public abstract String getTraslationKey();

    public abstract ItemLike getIconItem();

    /**
     * Set Size of all category
     * <br/>
     * <br/>
     * If the background image doesn't fit , you need to override
     * <code>background(GuiGraphics)</code>
     */
    public abstract Size setXY();

    public abstract Identifier setBackGround();

    @Override
    public Component getTitle() {
        return Component.translatable(getTraslationKey());
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableItemLike(getIconItem());
    }

    @Override
    public int getWidth() {
        return setXY().getX();
    }

    @Override
    public int getHeight() {
        return setXY().getY();
    }

    /**
     * Don't override without super it!
     */
    public void drawBackground(GuiGraphicsExtractor graphics) {
        this.backgroundImage.render(helper, graphics);
    }

    public void drawCentredStringFixed(GuiGraphicsExtractor g, Font font, Component text, int x, int y, int color,
            boolean shadow) {
        var f = text.getVisualOrderText();
        g.text(font, f, x - font.width(f) / 2, y, color, shadow);
    }

}