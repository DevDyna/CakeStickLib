package com.devdyna.cakesticklib.api.compat.jei;

import static com.devdyna.cakesticklib.Main.MODULE_ID;

import java.awt.Color;

import org.jetbrains.annotations.Nullable;

import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtil;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;
/**
 * A generic recipe category to show hardcoded implementations without a defined dependency
 */
public abstract class BaseCategory<T> implements IRecipeCategory<T> {

    protected IGuiHelper helper;

    public final Font font = Minecraft.getInstance().font;

    public BaseCategory(IGuiHelper h) {
        this.helper = h;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
    }

    protected final Color defaultToolTipColor = ColorUtil.color(64, 64, 64);

    /**
     * Define the category title
     * <br/><br/>
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

    public abstract String setBackGround();

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

    public void background(GuiGraphicsExtractor graphics) {
        ImageJei.of()
                .rl(MODULE_ID,this.setBackGround())
                .size(this.getWidth(), this.getHeight())
                .render(helper, graphics);
    }

    @Override
    public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX,
            double mouseY) {
        background(guiGraphics);
    }

}