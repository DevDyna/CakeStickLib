package com.devdyna.cakesticklib.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemLogisticUtils {

    public static ArrayList<ItemStack> unifyDrops(List<ItemStack>... itemLists) {
        ArrayList<ItemStack> newItems = new ArrayList<>();

        for (List<ItemStack> items : itemLists) {
            for (ItemStack incoming : items) {

                boolean found = false;

                for (int i = 0; i < newItems.size(); i++) {
                    ItemStack existing = newItems.get(i);

                    if (existing.getItem() == incoming.getItem() && existing.getCount() < existing.getMaxStackSize()) {

                        int newCount = Math.min(existing.getMaxStackSize(), existing.getCount() + incoming.getCount());

                        newItems.set(i, new ItemStack(existing.getItem(), newCount));
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    newItems.add(new ItemStack(incoming.getItem(), incoming.getCount()));
                }
            }
        }

        return newItems;
    }

    public static void createLazyItemEntity(ItemStack stack, Level l, BlockPos pos, int lifespan, boolean noMotion) {
        var item = new ItemEntity(l,
                pos.getX() + 0.5f,
                pos.getY() + 0.5f,
                pos.getZ() + 0.5f,
                stack);

        item.lifespan = lifespan;
        if (noMotion)
            item.setDeltaMovement(0, 0, 0);

        l.addFreshEntity(item);
    }

}
