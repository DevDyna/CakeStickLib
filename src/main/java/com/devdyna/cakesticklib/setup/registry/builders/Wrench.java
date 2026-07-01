package com.devdyna.cakesticklib.setup.registry.builders;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;

public class Wrench extends Item {

    public Wrench(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {

        var pos = ctx.getClickedPos();
        var level = ctx.getLevel();
        var state = level.getBlockState(pos);
        var player = ctx.getPlayer();

        var faceProp = List.of(
                BlockStateProperties.FACING,
                BlockStateProperties.FACING_HOPPER,
                BlockStateProperties.HORIZONTAL_FACING);

        for (var p : faceProp)
            if (state.hasProperty(p))
                return success(level, pos, state.cycle(p), player);

        var axisProp = List.of(
                BlockStateProperties.AXIS,
                BlockStateProperties.HORIZONTAL_AXIS);

        for (var p : axisProp)
            if (state.hasProperty(p))
                return success(level, pos, state.cycle(p), player);

        if (state.hasProperty(BlockStateProperties.SLAB_TYPE)) {
            var newstate = state.cycle(BlockStateProperties.SLAB_TYPE);

            if (newstate.getValue(BlockStateProperties.SLAB_TYPE).equals(SlabType.DOUBLE))
                newstate = newstate.cycle(BlockStateProperties.SLAB_TYPE);

            return success(level, pos, newstate, player);
        }

        if (state.hasProperty(BlockStateProperties.HALF))
            return success(level, pos, state.cycle(BlockStateProperties.HALF), player);

        if (state.hasProperty(BlockStateProperties.ORIENTATION))
            return success(level, pos, state.cycle(BlockStateProperties.ORIENTATION), player);

        var newstate = state.rotate(level, pos, Rotation.CLOCKWISE_90);

        if (newstate != state)
            return success(level, pos, newstate, player);

        return super.useOn(ctx);

    }

    public InteractionResult success(Level level, BlockPos pos, BlockState state, Player player) {
        level.setBlockAndUpdate(pos, state);
        ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.CRIT, UniformInt.of(1, 2));
        level.playSound(player, pos, SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
        return InteractionResult.SUCCESS_SERVER;
    }

}