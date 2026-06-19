package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.ArrayList;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public interface NoGuiStorage {

    default InteractionResult itemUseOn(Player player, Level level, BlockPos pos, InteractionHand hand) {

        var stack = player.getItemInHand(hand);

        if (hand.equals(InteractionHand.MAIN_HAND) && level != null
                && !player.isShiftKeyDown()) {
            var be = level.getBlockEntity(pos);

            if (be instanceof NoGuiStorage storage) {

                // If holding item -> try insert
                if (!stack.isEmpty() && !extractOnly() && insertFilter(stack) && requiredToInsert(stack)) {

                    player.swing(hand);
                    if (!level.isClientSide()) {
                        ItemStack remaining = storage.insertItem(stack);
                        player.setItemInHand(hand, remaining);
                    }
                    setChanged();
                    return InteractionResult.SUCCESS_SERVER;
                }

                if (stack.isEmpty() && !insertOnly()) {

                    player.swing(hand);
                    // If empty hand -> extract one item
                    ItemStack extracted = storage.extractItem();

                    if (!extracted.isEmpty()) {
                        player.addItem(extracted);
                        // ItemHandlerHelper.giveItemToPlayer(player, extracted);
                        setChanged();
                        return InteractionResult.SUCCESS_SERVER;
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }

    abstract ItemStack extractItem();

    abstract ItemStack insertItem(ItemStack stack);

    default boolean extractOnly() {
        return false;
    }

    /**
     * insert only specific itemstacks
     */
    default boolean insertFilter(ItemStack i) {
        return true;
    }

    /**
     * insert only when holding a specific itemstack<br/>
     * <br/>
     * NOTE : it will consume the item used!
     */
    default boolean requiredToInsert(ItemStack i) {
        return true;
    }

    default boolean insertOnly() {
        return false;
    }

    abstract void setChanged();

    default void dropInWorldResult(ItemStack output, Level level, BlockPos pos) {

        var validDir = new ArrayList<Direction>();

        for (Direction dir : new Direction[] { Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST })
            if (!canPlaceItem(level, pos, dir))
                validDir.add(dir);

        var outputPos = pos;

        if (!validDir.isEmpty())
            outputPos = pos.relative(validDir.get(level.getRandom().nextInt(validDir.size())));
        else if (canPlaceItem(level, pos, Direction.UP))
            outputPos = pos.relative(Direction.UP);

        spawnItemEntity(level, outputPos, output);

    }

    private boolean canPlaceItem(Level l, BlockPos p, Direction d) {
        return l.getBlockState(p.relative(d)).isSolidRender();
    }

    default void spawnItemEntity(Level l, BlockPos p, ItemStack s) {
        l.addFreshEntity(new ItemEntity(
                l,
                p.getX() + 0.5,
                p.getY() + 0.5,
                p.getZ() + 0.5,
                s.copy()));
    }

    ItemStacksResourceHandler getItemStorage();

    default ItemStack simpleInsertItem(ItemStack stack) {

        var inserted = 0;

        try (Transaction tx = Transaction.openRoot()) {
            inserted = getItemStorage().insert(0, ItemResource.of(stack), stack.getCount(), tx);
            tx.commit();
        }

        return x.item(stack.getItem(), stack.getCount() - inserted);
    }

    default ItemStack simpleExtractItem() {

        var resource = getItemStorage().getResource(0);

        if (resource.isEmpty())
            return ItemStack.EMPTY;

        try (Transaction tx = Transaction.openRoot()) {

            var extracted = getItemStorage()
                    .extract(0, resource, getItemStorage().getAmountAsInt(0), tx);
            tx.commit();

            return resource.toStack(extracted);
        }
    }

}
