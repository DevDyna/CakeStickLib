package com.devdyna.cakesticklib.setup.registry;

import com.devdyna.cakesticklib.api.utils.ColorUtil;
import com.devdyna.cakesticklib.setup.registry.types.zItems;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;


public class CakeStick extends Item {

    public CakeStick() {
        super(new Properties().setId(zItems.CAKE_STICK.getKey()).stacksTo(1).durability(7));
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var pos = c.getClickedPos();
        var dir = c.getClickedFace();
        var level = c.getLevel();
        var item = c.getItemInHand();
        var state = level.getBlockState(pos);
        var offset = level.getBlockState(pos.relative(dir));
        var player = c.getPlayer();
        var hand = c.getHand();

        // found a cake
        if (state.getBlock() instanceof CakeBlock) {
            var bites = state.getValue(BlockStateProperties.BITES);
            if (bites != 0) {
                bites--;
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.BITES, bites));

                player.swing(hand);
                item.hurtAndBreak(1, player, player.getUsedItemHand());

                if (!level.isClientSide())
                    ParticleUtils.spawnParticlesOnBlockFaces(level, pos.relative(dir), new DustParticleOptions(16711680, 1f),
                        UniformInt.of(3, 5));

                return InteractionResult.SUCCESS;
            }
        }

        // valid *cake place*
        if (offset.canBeReplaced()
                && state.isSolidRender()) {
            level.setBlockAndUpdate(pos.relative(dir),
                    Blocks.CAKE.defaultBlockState().setValue(BlockStateProperties.BITES, 6));

            player.swing(hand);
            item.hurtAndBreak(1, player, player.getUsedItemHand());

            if (!level.isClientSide())
                ParticleUtils.spawnParticlesOnBlockFaces(level, pos.relative(dir), new DustParticleOptions(16711680, 1f),
                        UniformInt.of(3, 5));
                    
            return InteractionResult.SUCCESS;
        }

        return super.useOn(c);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(getDescriptionId()).withColor(ColorUtil.rgbColor());
    }

}