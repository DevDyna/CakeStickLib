package com.devdyna.cakesticklib.setup.common.events;

import java.util.Arrays;

import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.Config;
import com.devdyna.cakesticklib.setup.registry.types.zItems;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopper.WeatherState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.level.BlockEvent;

public class PatinaDropEvent {

    @SubscribeEvent
    public static void patinaDropEvent(BlockEvent.BlockToolModificationEvent event) {

        if (event.getItemAbility() != ItemAbilities.AXE_SCRAPE)
            return;

        if (!(event.getLevel() instanceof Level level))
            return;

        if (level.isClientSide())
            return;

        if (!(event.getState().getBlock() instanceof WeatheringCopper oxidize))
            return;

        var age = Arrays.asList(WeatherState.values()).indexOf(oxidize.getAge());

        if (age <= 0)
            return;

        if (Config.DISABLE_PATINA_DROP_EVENT.get())
            return;

        if (level.getRandom().nextBoolean())
            Block.popResource(level, event.getPos().relative(event.getContext().getClickedFace()),
                    x.item(zItems.PATINA.get(),
                            level.getRandom().nextInt(1) + 1 + (age > 1 ? level.getRandom().nextInt(1) : 0)));

    }
}