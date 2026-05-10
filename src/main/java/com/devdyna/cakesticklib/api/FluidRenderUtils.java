package com.devdyna.cakesticklib.api;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.block.FluidModel.Unbaked;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.client.fluid.FluidTintSource;

public class FluidRenderUtils {

    public class MOLTEN {
        public static final Material STILL = new Material(x.rl(MODULE_ID, "block/fluids/molten/still"));
        public static final Material FLOW = new Material(x.rl(MODULE_ID, "block/fluids/molten/flow"));
        public static final Material OVERLAY = STILL;
    }

    public class BLAZING {
        public static final Material STILL = new Material(x.rl(MODULE_ID, "block/fluids/blazing/still"));
        public static final Material FLOW = new Material(x.rl(MODULE_ID, "block/fluids/blazing/flow"));
        public static final Material OVERLAY = STILL;
    }

    public class WATER {
        public static final Material STILL = new Material(x.rl("minecraft", "block/water_still"));
        public static final Material FLOW = new Material(x.rl("minecraft", "block/water_flow"));
        public static final Material OVERLAY = new Material(x.rl("minecraft", "block/water_overlay"));
    }

    public static Unbaked createWaterModel(FluidTintSource tint) {
        return new FluidModel.Unbaked(WATER.STILL, WATER.FLOW, WATER.OVERLAY, tint);
    }

    public static Unbaked createMoltenModel(FluidTintSource tint) {
        return new FluidModel.Unbaked(MOLTEN.STILL, MOLTEN.FLOW, MOLTEN.OVERLAY, tint);
    }

    public static Unbaked createBlazingModel(FluidTintSource tint) {
        return new FluidModel.Unbaked(BLAZING.STILL, BLAZING.FLOW, BLAZING.OVERLAY, tint);
    }

    // maybe overkill?
    public static void updateRender(BlockPos pos) {
        Minecraft.getInstance().levelRenderer.setBlocksDirty(
                pos.getX(), pos.getY(), pos.getZ(),
                pos.getX(), pos.getY(), pos.getZ());
    }
}
