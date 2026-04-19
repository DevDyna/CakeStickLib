package com.devdyna.cakesticklib.api.gui;

import java.util.List;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.aspect.logic.EnergyBlock;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.MachineItemAutomation;
import com.devdyna.cakesticklib.setup.registry.types.zLibComponents;

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

@SuppressWarnings("null")
public abstract class BaseMenu extends AbstractContainerMenu {

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

        if (blockEntity instanceof MachineItemAutomation be) {
            this.type = BlockMenuType.MACHINE;
            this.MACHINE_SLOT = be.getMachineSlots();
            this.inputSlotIndex = be.getInputSlotIndex();
            this.outputSlotIndex = be.getOutputSlotIndex();
            this.container = new SimpleContainer(MACHINE_SLOT);
        }

        if (blockEntity instanceof EnergyBlock enel) {
            energyData = enel.getContainerData();
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
        addMachineSlot(s -> s.has(zLibComponents.UPGRADE_COMPONENTS),
                beSlot, id, 4, x, y);
    }

    // not work properly
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack movedStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack rawStack = slot.getItem();
            movedStack = rawStack.copy();

            if (outputSlotIndex != null && outputSlotIndex.contains(index)) {
                if (!this.moveItemStackTo(rawStack, MACHINE_SLOT + 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(rawStack, movedStack);
            }

            else if (index >= MACHINE_SLOT + 1) {
                boolean movedToInput = false;

                if (inputSlotIndex != null && !inputSlotIndex.isEmpty()) {
                    for (int inputIndex : inputSlotIndex) {
                        if (this.moveItemStackTo(rawStack, inputIndex, inputIndex + 1, false)) {
                            movedToInput = true;
                            break;
                        }
                    }
                }

                if (!movedToInput) {
                    int invStart = MACHINE_SLOT + 1;
                    int invEnd = invStart + 27;
                    int hotbarStart = invEnd;
                    int hotbarEnd = this.slots.size();

                    if (index >= invStart && index < invEnd) {
                        if (!this.moveItemStackTo(rawStack, hotbarStart, hotbarEnd, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= hotbarStart) {
                        if (!this.moveItemStackTo(rawStack, invStart, invEnd, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }

            else if (inputSlotIndex != null && inputSlotIndex.contains(index)) {
                if (!this.moveItemStackTo(rawStack, MACHINE_SLOT + 1, this.slots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (rawStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (rawStack.getCount() == movedStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, rawStack);
        }

        return movedStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    // @Override
    // public boolean stillValid(Player player) {
    // return ContainerLevelAccess.create(getLevel(),
    // getBlockEntity().getBlockPos())
    // .evaluate((lvl, pos) -> {
    // for (Block b : getValidBlock()) {
    // if (lvl.getBlockState(pos).is(b)) {
    // return player.canInteractWithBlock(pos, 4.0);
    // }
    // }
    // return false;
    // }, true);
    // }

    // public abstract Block[] getValidBlock();

    public abstract BlockEntity getBlockEntity();

    public abstract Level getLevel();

}
