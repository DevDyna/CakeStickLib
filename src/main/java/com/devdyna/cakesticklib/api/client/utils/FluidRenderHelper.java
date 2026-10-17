package com.devdyna.cakesticklib.api.client.utils;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidRenderHelper {

    private TextureAtlasSprite texture;
    private int color = 0xFFFFFF;
    private float x = 0;
    private float y = 0;
    private float z = 0;
    private float amount = 1000;
    private float height = 1.0f;
    private float width = 1.0f;
    private float scale = 1.0f;
    private Consumer<PoseStack> modified = p -> {
        p.scale(scale, scale, scale);
        p.translate(x, y, z);
    };

    public FluidRenderHelper() {
    }

    public static FluidRenderHelper of() {
        return new FluidRenderHelper();
    }

    // /**
    // * define the texture of the render<br/>
    // * <br/>
    // * REQUIRED
    // */
    // public FluidRenderHelper texture(Identifier rl) {
    // return texture(Minecraft.getInstance().getAtlasManager().get(new
    // SpriteId(AtlasIds.BLOCKS, rl)));
    // }

    /**
     * define the texture of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper texture(FluidStack fluid) {
        return texture(Minecraft.getInstance()
                .getModelManager()
                .getFluidStateModelSet()
                .get(fluid.getFluid().defaultFluidState()).stillMaterial().sprite());
    }

    /**
     * define the texture of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper textureAndColor(FluidStack f) {
        color(f);
        return texture(f);
    }

    /**
     * define the texture of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper texture(TextureAtlasSprite sp) {
        texture = sp;
        return this;
    }

    /**
     * define the color of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper color(int c) {
        color = c;
        return this;
    }

    /**
     * define the color of the render<br/>
     * <br/>
     * use the default fluid color<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper color(FluidStack f) {

        var fluidModel = Minecraft.getInstance()
                .getModelManager()
                .getFluidStateModelSet()
                .get(f.getFluid().defaultFluidState());

        return color((fluidModel.fluidTintSource() != null
                ? fluidModel.fluidTintSource().colorAsStack(
                        com.devdyna.cakesticklib.api.utils.x.fluid(f.getFluid()))
                : -1) | 0xFF000000);
    }

    /**
     * define the fluid amount<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper amount(float a) {
        amount = a;
        return this;
    }

    /**
     * define the offset of the entire render<br/>
     * <br/>
     * Default = 0
     */
    public FluidRenderHelper offset(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * multiply the scale<br/>
     * <br/>
     * Default = x1.0f
     */
    @Deprecated
    public FluidRenderHelper scaleMultiplier(float h, float w) {
        this.height = h;
        this.width = w;
        return this;
    }

    /**
     * multiply the scale using the same scale factor<br/>
     * <br/>
     * Default = x1.0f
     */
    public FluidRenderHelper scaleMultiplier(float v) {
        this.scale = v;
        return this;
    }

    /**
     * multiply the scale using the same scale factor<br/>
     * <br/>
     * Default = x1.0f
     */
    public FluidRenderHelper modify(Consumer<PoseStack> m) {
        this.modified = m;
        return this;
    }

    public void build(PoseStack stack, SubmitNodeCollector node, int packedLight) {

        if (this.texture == null)
            throw new NullPointerException(
                    "FluidRenderHelper/Texture: You need to set the texture sprite BEFORE build it!");

        stack.pushPose();

        modified.accept(stack);

        float faceSize = 14 / 16f;
        float fluidHeight = amount * faceSize;
        float inset = 1 / 16f;

        TextureRenderUtil.renderDirectionalFace(Direction.NORTH, stack, node, texture,
                inset, inset, inset,
                width * faceSize, height * fluidHeight,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.SOUTH, stack, node, texture,
                inset, inset, inset,
                width * faceSize, height * fluidHeight,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.EAST, stack, node, texture,
                inset, inset, inset,
                width * faceSize, height * fluidHeight,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.WEST, stack, node, texture,
                inset, inset, inset,
                width * faceSize, height * fluidHeight,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.UP, stack, node, texture,
                inset, inset, inset + fluidHeight,
                width * faceSize, height * faceSize,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.DOWN, stack, node, texture,
                inset, inset, 1 - inset,
                width * faceSize, height * faceSize,
                color, packedLight);

        stack.popPose();
    }

}
