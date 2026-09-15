package com.devdyna.cakesticklib.setup.common.events;

import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

public class ItemStorageBreak {

    @SubscribeEvent
    public static void inventoryDestroy(BreakBlockEvent event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var be = level.getBlockEntity(pos);
        var entity = event.getPlayer();

        try {
            if (be instanceof ItemStorageBlock storage)
                if (entity != null && entity instanceof Player player)
                    if (storage.dropOnBreak(player))
                        for (int i = 0; i < storage.getSlots(); i++)
                            Block.popResource((Level) level, pos, storage.getStackInSlot(i));
        } catch (Exception e) {
            // it must prevent persistent broken storage blocks on break
        }
    }

}
