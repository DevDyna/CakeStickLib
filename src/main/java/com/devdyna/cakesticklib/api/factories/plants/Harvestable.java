package com.devdyna.cakesticklib.api.factories.plants;

import java.util.List;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.setup.Config;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public interface Harvestable {

    static boolean getConfig() {
        return Config.HARVESTABLE_ACTION.get();
    }

    boolean canHarvest(Harvestable.Context ctx);

    List<ItemStack> getItemsResult(Harvestable.Context ctx);

    /**
     * Called after successfully collect all items , only to remove mature plants
     */
    void replant(Harvestable.Context ctx);

    default void simpleAgeResetReplant(Harvestable.Context ctx) {
        ctx.level().setBlockAndUpdate(ctx.pos(), ctx.state().setValue(getAgeProperty(), 0));
    }

    default void simpleAgeRedutionReplant(Harvestable.Context ctx) {
        ctx.level().setBlockAndUpdate(ctx.pos(),
                ctx.state().setValue(getAgeProperty(),
                        (int) RandomUtil.between(ctx.level(), 0, getMaxAge() - 2)));
    }

    IntegerProperty getAgeProperty();

    int getMaxAge();

    public record Context(Level level, BlockPos pos, Player player) {
        public static Context of(Level l, BlockPos p, Player e) {
            return new Context(l, p, e);
        }

        public BlockState state() {
            return level.getBlockState(pos);
        }

        public BlockState state(Direction d) {
            return level.getBlockState(pos.relative(d));
        }

        public Block block() {
            return state().getBlock();
        }

        public Block block(Direction d) {
            return state(d).getBlock();
        }

        public Context modify(BlockPos pos) {
            return modify(pos, player());
        }

        public boolean is(BlockPos pos) {
            return pos().equals(pos);
        }

        public boolean is(BlockState state) {
            return is(state.getBlock());
        }

        public boolean is(Block block) {
            return state().is(block);
        }

        public boolean is(Direction dir,Block block) {
            return state(dir).is(block);
        }

        public Context modify(Player player) {
            return modify(pos(), player);
        }

        public Context modify(BlockPos pos, Player player) {
            return of(level(), pos, player);
        }

    }
}
