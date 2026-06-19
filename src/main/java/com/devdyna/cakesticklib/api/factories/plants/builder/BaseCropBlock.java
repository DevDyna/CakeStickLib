package com.devdyna.cakesticklib.api.factories.plants.builder;

import java.util.List;

import com.devdyna.cakesticklib.api.factories.plants.Harvestable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public abstract class BaseCropBlock extends CropBlock implements Harvestable {

    public BaseCropBlock(Properties p) {
        super(p.mapColor(MapColor.PLANT)
                .noCollision()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .pushReaction(PushReaction.DESTROY));
    }

    @Override
    public boolean canHarvest(Harvestable.Context ctx) {
        return isMaxAge(ctx.state());
    }

    @Override
    public List<ItemStack> getItemsResult(Harvestable.Context ctx) {

        if (ctx.level() instanceof ServerLevel server)
            return Block.getDrops(ctx.state(), server, ctx.pos(), null);

        return List.of();
    }

    @Override
    public void replant(Harvestable.Context ctx) {
        simpleAgeResetReplant(ctx);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return super.getAgeProperty();
    }

}