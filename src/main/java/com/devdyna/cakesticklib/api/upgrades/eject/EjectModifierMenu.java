package com.devdyna.cakesticklib.api.upgrades.eject;


import com.devdyna.cakesticklib.setup.registry.LibContainer;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class EjectModifierMenu extends AbstractContainerMenu {

    private final BlockPos pos;

    public EjectModifierMenu(int id, Inventory inv, RegistryFriendlyByteBuf b) {
        this(id, inv, b.readBlockPos());
    }

    public EjectModifierMenu(int id, Inventory inventory, BlockPos pos) {
        super(LibContainer.EJECT_MODIFIER.get(), id);
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        return false;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
