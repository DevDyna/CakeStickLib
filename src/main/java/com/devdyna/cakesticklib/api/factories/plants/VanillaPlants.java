package com.devdyna.cakesticklib.api.factories.plants;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.primitive.QueueUtil;
import com.devdyna.cakesticklib.setup.Config;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * Utility class to harvest most of plants safetly
 * 
 * Credits : @DevDyna
 */

public class VanillaPlants {

    static int treeHarvestingBlockLimit = Config.TREE_CUTTING_LIMIT.get();

    public static List<List<Integer>> getTreeDirections() {
        ArrayList<List<Integer>> coordinates = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0)
                        continue;
                    coordinates.add(List.of(x, y, z));
                }
            }
        }
        return coordinates;

    }

    public static List<IntegerProperty> allCropProperties = List.of(
            BlockStateProperties.AGE_1,
            BlockStateProperties.AGE_15,
            BlockStateProperties.AGE_2,
            BlockStateProperties.AGE_25,
            BlockStateProperties.AGE_3,
            BlockStateProperties.AGE_4,
            BlockStateProperties.AGE_5,
            BlockStateProperties.AGE_7);

    public static List<ItemStack> checkReplant(Level level, BlockPos pos, @Nullable Player player,
            @Nullable InteractionHand hand) {

        var state = level.getBlockState(pos);
        var block = state.getBlock();

        if (block instanceof Harvestable harvestable) {

            var ctx = Harvestable.Context.of(level, pos, player);

            List<ItemStack> items = null;

            if (harvestable.canHarvest(ctx)) {
                items = harvestable.getItemsResult(ctx);// required to prevent to harvest AIR
                harvestable.replant(ctx);
            }

            return items;

        }

        if (block instanceof CropBlock crop) {

            if (crop.isMaxAge(state)) {
                // synergy custom crops
                // if (crop instanceof PlantHandler handler) {
                // level.setBlockAndUpdate(pos, state.setValue(handler.getProperty(), 0));
                // if (level.isClientSide())
                // onClientClick(level, player, hand, pos);
                // else
                // return Block.getDrops(state, (ServerLevel) level, pos, null);

                // } else {

                try {
                    // vanilla crops
                    if (block instanceof BeetrootBlock)
                        level.setBlockAndUpdate(pos, state.setValue(BeetrootBlock.AGE, 0));
                    else
                        level.setBlockAndUpdate(pos, state.setValue(CropBlock.AGE, 0));

                    if (level.isClientSide())
                        onClientClick(level, player, hand, pos);
                    else
                        return Block.getDrops(state, (ServerLevel) level, pos, null);

                } catch (Exception e1) {
                    // crops with different properties
                    for (IntegerProperty p : allCropProperties) {

                        if (state.hasProperty(p)) {
                            try {
                                level.setBlockAndUpdate(pos, state.setValue(p, 0));
                                if (level.isClientSide())
                                    onClientClick(level, player, hand, pos);
                                else
                                    return Block.getDrops(state, (ServerLevel) level, pos, null);
                            } catch (Exception e2) {
                            }
                        }
                    }

                }

            }
        }

        if (block instanceof NetherWartBlock) {
            if (state.getValue(BlockStateProperties.AGE_3).intValue() == 3) {
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_3, 0));
                if (level.isClientSide())
                    onClientClick(level, player, hand, pos);
                else
                    return Block.getDrops(state, (ServerLevel) level, pos, null);
            }
        }

        // doesn't suppress -> duplicate result
        if (block instanceof SweetBerryBushBlock || block instanceof CaveVines) {
            return null;
        }

        if (block instanceof CocoaBlock) {
            if (state.getValue(BlockStateProperties.AGE_2).intValue() == 2) {
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_2, 0));
                if (level.isClientSide())
                    onClientClick(level, player, hand, pos);
                else
                    return Block.getDrops(state, (ServerLevel) level, pos, null);
            }
        }

        return null;

    }

    public static List<ItemStack> checkNoReplant(Level level, BlockPos pos) {
        var state = level.getBlockState(pos);
        var block = state.getBlock();

        if (block instanceof PumpkinBlock || state.is(Blocks.MELON)) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            return Block.getDrops(state, (ServerLevel) level, pos, null);
        }
        return null;
    }

    public static void onClientClick(Level level, Player player, InteractionHand hand, BlockPos pos) {
        if (player != null && hand != null)
            player.swing(hand);
        if (player != null)
            level.playSound(player, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 3.0F, 2F);

    }

    public static List<ItemStack> checkTree(Level level, BlockPos pos, boolean simulate) {
        return checkTree(level, pos, simulate, (s, p) -> false);
    }

    public static List<ItemStack> checkTree(Level level, BlockPos pos) {
        return checkTree(level, pos, false);
    }

    public static List<ItemStack> checkTree(Level level, BlockPos pos, boolean simulate,
            BiFunction<BlockState, BlockPos, Boolean> tool) {

        var state = level.getBlockState(pos);

        var validTree = false;

        if (state.is(BlockTags.COMPLETES_FIND_TREE_TUTORIAL))
            for (Direction dir : Direction.values())
                if (level.getBlockState(pos.relative(dir)).is(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)) {
                    validTree = true;
                    break;
                }

        if (!validTree)
            return null;

        List<ItemStack> items = new ArrayList<>();
        List<SoundEvent> sounds = new ArrayList<>();

        sounds.add(state.getSoundType(level, pos, null).getBreakSound());

        QueueUtil.of(pos)
                .limit(treeHarvestingBlockLimit)
                .define((queue, current) -> {

                    for (List<Integer> off : getTreeDirections()) {
                        var offset = current.offset(off.get(0), off.get(1), off.get(2));

                        var offstate = level.getBlockState(offset);

                        if (!offstate.is(BlockTags.COMPLETES_FIND_TREE_TUTORIAL))
                            continue;

                        Block.getDrops(offstate, (ServerLevel) level, offset, null)
                                .forEach(items::add);

                        if (!simulate)
                            level.setBlockAndUpdate(offset, Blocks.AIR.defaultBlockState());

                        var sound = offstate.getSoundType(level, offset, null).getBreakSound();

                        if (!sounds.contains(sound))
                            sounds.add(sound);

                        if (tool.apply(offstate, offset))
                            return QueueUtil.QueueStatus.SUCCESS;

                        queue.add(offset);
                    }

                    return QueueUtil.QueueStatus.CONTINUE;
                })
                .run();

        Block.getDrops(state, (ServerLevel) level, pos, null)
                .forEach(items::add);

        if (!simulate)
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

        if (!simulate)
            sounds.forEach(sound -> level.playSound(null, pos, sound, SoundSource.BLOCKS));

        return items;
    }

    public static List<ItemStack> checkBigPlant(Level level, BlockPos pos) {

        var state = level.getBlockState(pos);
        var block = state.getBlock();

        if (block instanceof CactusBlock || block instanceof SugarCaneBlock || block instanceof BambooStalkBlock) {
            var tempPos = pos;
            ArrayList<ItemStack> list = new ArrayList<>();
            while (level.getBlockState(tempPos.above()).is(block)) {
                tempPos = tempPos.above();
                level.setBlockAndUpdate(tempPos, Blocks.AIR.defaultBlockState());
                Block.getDrops(state, (ServerLevel) level, pos, null).forEach(t -> list.add(t));
            }
            return list;

        }
        return null;
    }

}