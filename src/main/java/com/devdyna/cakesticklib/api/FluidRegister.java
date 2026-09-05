package com.devdyna.cakesticklib.api;

import java.util.function.ToIntFunction;

import javax.annotation.Nullable;
import java.awt.Color;

import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.setup.registry.LibBlocks;
import com.devdyna.cakesticklib.setup.registry.LibFluids;
import com.devdyna.cakesticklib.setup.registry.LibItems;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
//TODO HEAVILY REWORK
/**
 * Utility class to create fluids
 */
public class FluidRegister {

    private String id;

    private DeferredHolder<Fluid, BaseFlowingFluid.Source> fluidsource;
    private DeferredHolder<Fluid, FlowingFluid> fluidflowing;
    private DeferredHolder<Item, BucketItem> itemBucket;
    private DeferredHolder<Block, LiquidBlock> block;
    private BaseFlowingFluid.Properties prop;
    private DeferredHolder<FluidType, ?> type;

    private int lightLevel;
    private ToIntFunction<BlockState> dynLightLevel;
    private int viscosityFluid;

    private boolean igniteWhenInside;
    private int color;

    private boolean canSwim;

    private boolean canDrown;

    private boolean canConvertToSource;

    private boolean canPushEntity;

    private int blockLightLevel;

    public FluidRegister(String id, int color, boolean noBucket, boolean ignite, boolean drown, boolean swim,
            boolean push, boolean convertToSource, int viscosity, int block_light,
            ToIntFunction<BlockState> light_level) {

        this.id = id;
        this.color = color;
        this.viscosityFluid = viscosity;// approx water
        this.dynLightLevel = light_level;
        this.blockLightLevel = block_light;
        this.canDrown = drown;
        this.canSwim = swim;
        this.canPushEntity = push;
        this.canConvertToSource = convertToSource;
        this.igniteWhenInside = ignite;

        this.type = LibFluids.zFluidTypes.register(
                id + "_type",
                p -> new FluidType(FluidType.Properties.create()
                        .lightLevel(blockLightLevel)
                        .viscosity(viscosityFluid)
                        .canDrown(canDrown)
                        .canSwim(canSwim)
                        .canPushEntity(canPushEntity)
                        .canConvertToSource(canConvertToSource)
                        .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                        .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY))

        );

        this.prop = new BaseFlowingFluid.Properties(this.type, null, null);

        this.fluidsource = LibFluids.zFluids.register(id // + "_source"
                , p -> new BaseFlowingFluid.Source(this.prop));

        this.fluidflowing = LibFluids.zFluids.register(id + "_flowing",
                p -> new BaseFlowingFluid.Flowing(this.prop) {

                    protected void entityInside(Level level, BlockPos pos, Entity entity,
                            InsideBlockEffectApplier effectApplier) {
                        if (igniteWhenInside) {
                            effectApplier.apply(InsideBlockEffectType.CLEAR_FREEZE);
                            effectApplier.apply(InsideBlockEffectType.LAVA_IGNITE);
                            effectApplier.runAfter(InsideBlockEffectType.LAVA_IGNITE, Entity::lavaHurt);
                        }
                    };

                });

        this.itemBucket = noBucket ? null
                : LibItems.zBucketItems.registerItem(id + "_bucket",
                        p -> new BucketItem(this.fluidsource.get(),
                                p.craftRemainder(Items.BUCKET).stacksTo(1)));

        this.block = LibBlocks.zBlockFluids.registerBlock(
                id,
                pr -> new LiquidBlock(this.fluidsource.value(),
                        pr.mapColor(MapColor.WATER).replaceable().noCollision()
                                .strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid()
                                .sound(SoundType.EMPTY)
                                .liquid()
                                .lightLevel(dynLightLevel)
                                .emissiveRendering((s, g, p) -> lightLevel > 0 || dynLightLevel.applyAsInt(s) > 0)) {

                    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
                            InsideBlockEffectApplier effectApplier, boolean isPrecise) {

                        if (igniteWhenInside) {
                            effectApplier.apply(InsideBlockEffectType.CLEAR_FREEZE);
                            effectApplier.apply(InsideBlockEffectType.LAVA_IGNITE);
                            effectApplier.runAfter(InsideBlockEffectType.LAVA_IGNITE, Entity::lavaHurt);
                        }
                    };

                });

        var sampleProp = new BaseFlowingFluid.Properties(
                this.type,
                this.fluidsource,
                this.fluidflowing).block(this.block);

        if (!noBucket)
            sampleProp = sampleProp
                    .bucket(this.itemBucket);

        this.prop = sampleProp;
    }

    public DeferredHolder<Block, LiquidBlock> getBlock() {
        return block;
    }

    public DeferredHolder<Fluid, FlowingFluid> getFlowing() {
        return fluidflowing;
    }

    public DeferredHolder<Fluid, BaseFlowingFluid.Source> getSource() {
        return fluidsource;
    }

    public @Nullable DeferredHolder<Item, BucketItem> getItemBucket() {
        return itemBucket;
    }

    // public Identifier getStill() {
    // return still;
    // }

    public DeferredHolder<FluidType, ?> getType() {
        return type;
    }

    // public FluidRegister setTextures(Identifier still) {
    // this.still = still;
    // return this;
    // }

    // public FluidRegister setStillTexture(Identifier rl) {
    // this.still = rl;
    // return this;
    // }

    /**
     * dont work
     */
    @Deprecated
    public FluidRegister setLight(int l) {
        this.lightLevel = l;
        return this;
    }

    @Deprecated
    public FluidRegister setLight(ToIntFunction<BlockState> l) {
        this.dynLightLevel = l;
        return this;
    }

    @Deprecated
    public FluidRegister swim() {
        // this.canSwim = true;
        return this;
    }

    @Deprecated
    public FluidRegister convertToSource() {
        // this.canConvertToSource = true;
        return this;
    }

    @Deprecated
    public FluidRegister drown() {
        // this.canDrown = true;
        return this;
    }

    @Deprecated
    public FluidRegister pushEntity() {
        // this.canPushEntity = true;
        return this;
    }

    /**
     * Default value: 1000
     */
    @Deprecated
    public FluidRegister setViscosity(int v) {
        // this.viscosity = v;
        return this;
    }

    public String getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public Fluid getFluid() {
        return getSource().get();
    }

    public static FluidRegister simple(String id, int color) {
        return new FluidRegister(id, color, false, false, false, false, false, false, 1000, 0, s -> 0);
    }

    public static FluidRegister simple(String id, Color color) {
        return simple(id, ColorUtils.argb(color));
    }

    public static FluidRegister heavy(String id, int color) {
        return new FluidRegister(id, color, false, false, true, false, true, false, 2500, 0, s -> 0);
    }

    public static FluidRegister heavy(String id, Color color) {
        return heavy(id, ColorUtils.argb(color));
    }

    public static FluidRegister molten(String id, int color) {
        return new FluidRegister(id, color, false, true, false, false, true, false, 1000, 5, s -> 5);
    }

    public static FluidRegister molten(String id, Color color) {
        return molten(id, ColorUtils.argb(color));
    }

}