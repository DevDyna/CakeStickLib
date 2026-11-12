package com.devdyna.cakesticklib.api.gui;

import java.util.List;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.aspect.logic.EnergyBlock;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.ResourceRestricted;
import com.devdyna.cakesticklib.api.primitive.Range;
import com.devdyna.cakesticklib.api.utils.SlotBuilder;
import com.devdyna.cakesticklib.api.utils.SlotBuilder.ResourceType;
import com.devdyna.cakesticklib.setup.registry.LibComponents;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public abstract class BaseMenu extends AbstractContainerMenu {

    public abstract BlockEntity getBlockEntity();

    public abstract Level getLevel();

    @Deprecated
    public enum BlockMenuType {
        CONTAINER,
        MACHINE;
    }

    public int MACHINE_SLOT = 0;
    public ContainerData energyData;
    public List<Integer> inputSlotIndex;
    public List<Integer> outputSlotIndex;
    public BlockMenuType type;
    private Container container;

    protected BaseMenu(MenuType<?> menuType, int containerId, BlockEntity blockEntity) {
        super(menuType, containerId);

        if (blockEntity instanceof ItemStorageBlock be) {
            this.type = BlockMenuType.CONTAINER;
            this.MACHINE_SLOT = be.getSlots();
            this.container = new SimpleContainer(MACHINE_SLOT);
        }

        if (blockEntity instanceof ResourceRestricted.Item be) {
            this.type = BlockMenuType.MACHINE;
            this.MACHINE_SLOT = be.getMachineSlots();
            this.inputSlotIndex = be.getInputSlotIndex();
            this.outputSlotIndex = be.getOutputSlotIndex();
            this.container = new SimpleContainer(MACHINE_SLOT);
        }

        if (blockEntity instanceof EnergyBlock enel) {
            energyData = enel.getContainerData();
            if (enel != null)
                addDataSlots(energyData);
        }
    }

    protected void addPlayerSlots(Inventory inventory) {
        addPlayerSlots(inventory, 0, 0);
    }

    protected void addPlayerSlots(Inventory inventory, int xf, int yf) {
        addInventory(inventory, xf, yf);
        addHotbar(inventory, xf, yf);
    }

    protected void addInventory(Inventory inventory, int xf, int yf) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                addSlot(new Slot(inventory, l + i * 9 + 9, (8 + l * 18) + xf, (84 + i * 18) + yf));
            }
        }
    }

    protected void addHotbar(Inventory inventory, int xf, int yf) {
        for (int i = 0; i < 9; ++i) {
            addSlot(new Slot(inventory, i, (8 + i * 18) + xf, 142 + yf));
        }
    }

    protected void addInventory(Inventory inventory) {
        addInventory(inventory, 0, 0);
    }

    protected void addHotbar(Inventory inventory) {
        addHotbar(inventory, 0, 0);
    }

    protected void addMachineSlot(ItemStacksResourceHandler h, int index, int x, int y) {
        addSlot(new ResourceHandlerSlot(h, h::set, index, x, y));
    }

    protected void addMachineOutputSlot(ItemStacksResourceHandler h, int id, int x, int y) {
        addSlot(new ResourceHandlerSlot(h, h::set, id, x, y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
    }

    protected void addMachineInputSlot(ItemStacksResourceHandler h, int id, int x, int y) {
        addSlot(new ResourceHandlerSlot(h, h::set, id, x, y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return true;
            }

            @Override
            public boolean mayPickup(Player playerIn) {
                return true;
            }
        });
    }

    protected void addMachineSlot(Function<ItemStack, Boolean> mayPlace, ItemStacksResourceHandler h, int id, int limit,
            int x,
            int y) {
        addSlot(new ResourceHandlerSlot(h, h::set, id, x, y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return mayPlace.apply(stack);
            }

            @Override
            public boolean mayPickup(Player playerIn) {
                return true;
            }

            @Override
            public int getMaxStackSize() {
                return limit;
            }

            @Override
            public int getMaxStackSize(ItemStack i) {
                return limit;
            }
        });
    }

    protected void addMachineUpgradeSlot(ItemStacksResourceHandler beSlot, int id, int x, int y) {
        addMachineSlot(s -> s.has(LibComponents.UPGRADE_COMPONENTS),
                beSlot, id, 4, x, y);
    }

    protected void addMachineSlot(ItemStacksResourceHandler h, int index, int limit, int x, int y) {
        addMachineSlot(_ -> true, h, index, limit, x, y);
    }

    protected void addMachineInputSlot(ItemStacksResourceHandler h, int id, int limit, int x, int y) {
        addSlot(new ResourceHandlerSlot(h, h::set, id, x, y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return true;
            }

            @Override
            public boolean mayPickup(Player playerIn) {
                return true;
            }

            @Override
            public int getMaxStackSize() {
                return limit;
            }

            @Override
            public int getMaxStackSize(ItemStack i) {
                return limit;
            }
        });
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    /**
     * 0-26 inventory
     * 27-35 hotbar
     * 36+ machine (SlotBuilder)
     */

    public Range hotbarIndexes() {
        return Range.of(27, 35);
    }

    public Range invIndexes() {
        return Range.of(0, 26);
    }

    // <?> -> player
    private ItemStack moveToInventory(ItemStack item, Slot slot, Player player) {
        var copy = item.copy();

        // <?> -> inv
        if (moveItemStackTo(item, invIndexes().min(), invIndexes().max() + 1, false)) {
            slot.onTake(player, item);
            return copy;
        }

        // <?> -> hotbar
        if (moveItemStackTo(item, hotbarIndexes().min(), hotbarIndexes().max() + 1, false)) {
            slot.onTake(player, item);
            return copy;
        }

        return ItemStack.EMPTY;
    }

    // TODO verify if it work correctly
    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        if (index < 0 || index >= this.slots.size())
            return ItemStack.EMPTY;

        var slot = this.slots.get(index);

        if (!slot.hasItem())
            return ItemStack.EMPTY;

        var item = slot.getItem();

        // player inv -> <?>
        if (index >= invIndexes().min() && index <= hotbarIndexes().max()) {
            var copy = item.copy();

            // player -> machine
            if (getSlots() != null)
                for (var i = 0; i < getSlots().get().size(); i++) {
                    if (getSlots().get(i).equals(ResourceType.INPUT) || getSlots().get(i).equals(ResourceType.BUFFER))
                        if (moveItemStackTo(item, hotbarIndexes().max() + i + 1, hotbarIndexes().max() + 2 + i, true)) {
                            slot.onTake(player, item);
                            return copy;
                        }
                }

            // inv -> hotbar
            if (index >= invIndexes().min() && index <= invIndexes().max()) {
                if (moveItemStackTo(item, hotbarIndexes().min(), hotbarIndexes().max() + 1, true)) {
                    slot.onTake(player, item);
                    return copy;
                }
            }

            // hotbar -> inv
            if (index >= hotbarIndexes().min() && index <= hotbarIndexes().max()) {
                if (moveItemStackTo(item, invIndexes().min(), invIndexes().max() + 1, true)) {
                    slot.onTake(player, item);
                    return copy;
                }
            }
        }

        // machine -> <?>
        if (index >= hotbarIndexes().max() + 1) {
            if (getSlots() != null)
                if (!getSlots().get(index - hotbarIndexes().max() + 1).equals(SlotBuilder.ResourceType.NONE))
                    return moveToInventory(item, slot, player);
        }

        return ItemStack.EMPTY;

    }

    public SlotBuilder getSlots() {
        return null;
    };

}
