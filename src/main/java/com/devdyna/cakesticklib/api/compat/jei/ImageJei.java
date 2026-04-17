package com.devdyna.cakesticklib.api.compat.jei;

import com.devdyna.cakesticklib.api.primitive.Locator;

import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class ImageJei {

    private int x;
    private int y;

    private Locator locator;


    private int xo = 0;
    private int yo = 0;

    private int u = 0;
    private int v = 0;

    public ImageJei() {
    }

    public static ImageJei of() {
        return new ImageJei();
    }

    public ImageJei size(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ImageJei uv(int u, int v) {
        this.u = u;
        this.v = v;
        return this;
    }

    public ImageJei offset(int xo, int yo) {
        this.xo = xo;
        this.yo = yo;
        return this;
    }

    public ImageJei rl(String image) {
        this.locator = Locator.of().path(image);
        return this;
    }
    public ImageJei rl(Locator l) {
        this.locator = l;
        return this;
    }

    public void render(IGuiHelper h, GuiGraphicsExtractor g) {
        h.drawableBuilder(locator.rl(), u, v, x, y).setTextureSize(x, y).build()
                .draw(g, xo, yo);
    }

}