package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public interface DropCollector {

    abstract ItemStacksResourceHandler getItemStorage();

    default void adjustItemEntity(int remain, ItemEntity item) {
        if (remain <= 0) {
            item.setItem(ItemStack.EMPTY);
            item.discard();
        } else
            item.setItem(x.item(item.getItem().getItem(), remain));
    }

    default void whenItemFallOnIt(Level level, BlockPos pos, Entity entity) {

        if (!(entity instanceof ItemEntity item))
            return;

        var be = level.getBlockEntity(pos);

        var skipTransation = false;
        var copy = item.getItem().copy();

        var remain = copy.count();

        if (be instanceof NoGuiStorage gui) {
            remain = gui.insertItem(copy).count();
            skipTransation = true;
        }

        if (!skipTransation)

            try (var tx = Transaction.openRoot()) {

                if (ignoreIndex())
                    remain -= getItemStorage().insert(ItemResource.of(copy),
                            remain,
                            tx);
                else
                    for (int i : getInputSlots()) {

                        remain -= getItemStorage().insert(i, ItemResource.of(copy),
                                remain,
                                tx);

                        if (remain <= 0)
                            break;
                    }

                tx.commit();

            }

        adjustItemEntity(remain, item);

    }

    default boolean ignoreIndex() {
        return false;
    }

    default List<Integer> getInputSlots() {
        return List.of(0);
    }

}