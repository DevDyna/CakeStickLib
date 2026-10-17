package com.devdyna.cakesticklib.api.client.utils;

import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.Level;

public class SimpleItemRender {

    private boolean whenOn = true;

    private Quaternionf angle = Axis.XP.rotationDegrees(0);
    private double translateX = 0.5;
    private double translateY = 0.5;
    private double translateZ = 0.5;
    private float scaleX = 1;
    private float scaleY = 1;
    private float scaleZ = 1;

    // private Item item = Items.STONE;

    private SimpleItemRender() {

    }

    public static SimpleItemRender of() {
        return new SimpleItemRender();
    }

    public SimpleItemRender whenOn(boolean whenOn) {
        this.whenOn = whenOn;
        return this;
    }

    public SimpleItemRender rotate(Quaternionf angle) {
        this.angle = new Quaternionf(this.angle).mul(angle);
        return this;
    }

    public SimpleItemRender rotateXP(float angle) {
        return rotate(Axis.XP.rotationDegrees(angle));
    }

    public SimpleItemRender rotateXN(float angle) {
        return rotate(Axis.XN.rotationDegrees(angle));
    }

    public SimpleItemRender rotateYP(float angle) {
        return rotate(Axis.YP.rotationDegrees(angle));
    }

    public SimpleItemRender rotateYN(float angle) {
        return rotate(Axis.YN.rotationDegrees(angle));
    }

    public SimpleItemRender rotateZP(float angle) {
        return rotate(Axis.ZP.rotationDegrees(angle));
    }

    public SimpleItemRender rotateZN(float angle) {
        return rotate(Axis.ZN.rotationDegrees(angle));
    }

    public SimpleItemRender move(double x, double y, double z) {
        this.translateX = x;
        this.translateY = y;
        this.translateZ = z;
        return this;
    }

    public SimpleItemRender scale(float x, float y, float z) {
        this.scaleX = x;
        this.scaleY = y;
        this.scaleZ = z;
        return this;
    }

    // public SimpleItemRender item(ItemStack i) {
    // return item(i.getItem());
    // }

    // public SimpleItemRender item(Item i) {
    // this.item = i;
    // return this;
    // }

    // public SimpleItemRender item(DeferredHolder<Item, Item> i) {
    // return item(i.get());
    // }

    /**
     * light -> {@link net.minecraft.util.LightCoordsUtil} OR {@code <BlockEntityRenderState>.lightCoords} <br/>
     * <br/>
     * overlay -> {@link net.minecraft.client.renderer.texture.OverlayTexture}<br/><br/>
     * outline -> default {@code 0}
     */
    public void build(ItemStackRenderState r, PoseStack p, SubmitNodeCollector c, Level level, int lightCoords,
            int overlayCoords, int outlineColor) {

        if (whenOn) {
            p.pushPose();
            p.translate(translateX, translateY, translateZ);
            p.mulPose(angle);
            p.scale(scaleX, scaleY, scaleZ);
            // r.renderStatic(x.item(item),
            // ItemDisplayContext.GROUND, light, overlay, p, buffer, level,
            // level.getRandom().nextInt());

            r.submit(p, c, lightCoords, overlayCoords, outlineColor);
            p.popPose();
        }
    }

    public void build(BlockEntityRenderState state, ItemStackRenderState r, PoseStack p, SubmitNodeCollector c) {
        build(r, p, c, Minecraft.getInstance().level, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
    }

}
