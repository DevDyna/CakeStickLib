package com.devdyna.cakesticklib.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemLogisticUtils {
    public static ArrayList<ItemStack> unifyDrops(List<ItemStack> items) {
        ArrayList<ItemStack> newItems = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {

            var check = false;
            int index = -1;
            for (ItemStack itemStack : newItems) {
                if (itemStack.getItem() == items.get(i).getItem()) {
                    if (itemStack.getCount() >= 64)
                        continue;
                    index = newItems.indexOf(itemStack);
                    check = true;
                    break;
                }
            }

            if (check) {

                newItems.set(index,
                        new ItemStack(newItems.get(index).getItem(),
                                newItems.get(index).getCount() + 1));

            } else {

                newItems.add(items.get(i));

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
