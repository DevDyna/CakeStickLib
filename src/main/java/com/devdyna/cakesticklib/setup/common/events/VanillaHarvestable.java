package com.devdyna.cakesticklib.setup.common.events;

import java.util.*;

import com.devdyna.cakesticklib.api.factories.plants.*;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class VanillaHarvestable {
    @SubscribeEvent
    public static void harvestVanillaCrops(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var hand = event.getHand();

        List<ItemStack> check = VanillaPlants.checkReplant(level, pos);
        // no valid plants found
        if (check != null && hand.equals(InteractionHand.MAIN_HAND)) {

            player.swing(hand);

            level.playSound(player, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 2F);
            ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.COMPOSTER, UniformInt.of(3, 5));

            if (level.isClientSide())
                return;

            check.forEach(i -> player.addItem(i));
        }

    }
}