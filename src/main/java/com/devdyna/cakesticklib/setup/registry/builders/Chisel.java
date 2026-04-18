package com.devdyna.cakesticklib.setup.registry.builders;

import java.util.Optional;

import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.types.zComponents;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.block.EntityBlock;

public class Chisel extends Item {

    public Chisel(Properties properties) {
        super(properties.stacksTo(1).component(zComponents.IDENTIFIER, null));
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var level = c.getLevel();
        var pos = c.getClickedPos();
        var block = level.getBlockState(c.getClickedPos());
        var player = c.getPlayer();
        var item = c.getItemInHand();
        var blockItem = x.item(block);
        var hand = c.getHand();

        if (player.isCrouching()) {
            var id = x.rl(blockItem.getItem());
            if (id == null) {
                return InteractionResult.FAIL;
            }

            item.set(zComponents.IDENTIFIER, id);
            level.playSound(player, pos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1f, 1f);
            player.swing(hand);
        } else {
            var id = item.get(zComponents.IDENTIFIER);

            if (id == null)
                return InteractionResult.FAIL;

            var saved = x.get(id);

            Optional<RecipeHolder<StonecutterRecipe>> r = level.getServer().getRecipeManager()
                    .getRecipeFor(RecipeType.STONECUTTING,
                            new SingleRecipeInput(blockItem), level);

            if (r == null || r.isEmpty())
                return InteractionResult.FAIL;

            var recipe = r.get().value();

            var result = recipe.assemble(new SingleRecipeInput(blockItem)).copy();

            if (result.is(saved)) {

                if (saved instanceof BlockItem bi) {
                    level.setBlockAndUpdate(pos, bi.getBlock().getStateForPlacement(new BlockPlaceContext(c)));
                    if (bi.getBlock() instanceof EntityBlock) {
                        level.getBlockEntity(pos).setChanged();
                    }
                } else {
                    level.removeBlock(pos, false);
                    level.addFreshEntity(
                            new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                    x.item(saved)));
                }

                if (result.getCount() > 1) {
                    for (int j = 0; j < result.getCount() - 1; j++) {
                        level.addFreshEntity(
                                new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                        x.item(saved)));
                    }
                }

                player.swing(hand);
                level.playSound(player, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1f, 1f);

                return InteractionResult.SUCCESS;
            }

        }
        return InteractionResult.FAIL;

    }

}