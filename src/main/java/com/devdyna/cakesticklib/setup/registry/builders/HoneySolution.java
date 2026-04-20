package com.devdyna.cakesticklib.setup.registry.builders;

import com.devdyna.cakesticklib.setup.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DataMapHooks;

public class HoneySolution extends CopperReagentItem {

    public HoneySolution(Properties p) {
        super(p);
    }

    @Override
    public Block getNextBlock(Block b) {
        return DataMapHooks.getBlockWaxed(b);
    }

    @Override
    public Boolean getConfig() {
        return Config.HONEY_SOLUTION_EVENT.get();
    }

    public void getParticles(Level level, BlockPos pos) {
        ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.WAX_ON, UniformInt.of(3, 5));
    }

    public void getSound(Level level, BlockPos pos) {
        level.playLocalSound(pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F, false);
    }

}