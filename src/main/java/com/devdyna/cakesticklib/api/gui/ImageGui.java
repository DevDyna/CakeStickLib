package com.devdyna.cakesticklib.api.gui;

import com.devdyna.cakesticklib.CakeStickLib;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import java.awt.Color;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public class ImageGui {

    private int x = 0;
    private int y = 0;
    private String rl = "";
    private String modid = CakeStickLib.MODULE_ID;
    private int xo = 0;
    private int yo = 0;
    private int tx = x;
    private int ty = y;

    private int u = 0;
    private int v = 0;
    private int color = -1;// default

    public ImageGui() {

    }

    public static ImageGui of() {
        return new ImageGui();
    }

    public ImageGui size(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ImageGui sizeTexture(int tx, int ty) {
        this.tx = tx;
        this.ty = ty;
        return this;
    }

    public ImageGui uv(int u, int v) {
        this.u = u;
        this.v = v;
        return this;
    }

    public ImageGui offset(int xo, int yo) {
        this.xo = xo;
        this.yo = yo;
        return this;
    }

    public ImageGui color(int c) {
        this.color = c;
        return this;
    }

    public ImageGui color(int a, int r, int g, int b) {
        this.color = ColorUtils.argb(a, r, g, b);
        return this;
    }

    public ImageGui color(Color c) {
        this.color = ColorUtils.argb(c);
        return this;
    }

    public ImageGui rl(String image) {
        this.rl = image;
        return this;
    }

    public ImageGui rl(String modid, String image) {
        this.modid = modid;
        this.rl = image;
        return this;
    }

    public ImageGui rl(Identifier rl) {
        this.modid = rl.getNamespace();
        this.rl = rl.getPath();
        return this;
    }

    public void render(GuiGraphicsExtractor g) {

        g.blit(
                RenderPipelines.GUI_TEXTURED,
                com.devdyna.cakesticklib.api.utils.x.rl(modid, rl),
                xo - 1,
                yo - 1,
                u, v,
                x, y,
                tx, ty,
                color);

    }

}