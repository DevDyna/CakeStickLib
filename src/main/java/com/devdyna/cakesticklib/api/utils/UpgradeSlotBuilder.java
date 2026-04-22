package com.devdyna.cakesticklib.api.utils;

import java.util.*;

import com.devdyna.cakesticklib.api.primitive.Pos;

import net.minecraft.world.item.ItemStack;

public class UpgradeSlotBuilder {

        private final Map<Integer, ItemPos> storage;

        public UpgradeSlotBuilder() {
            this.storage = new HashMap<>();
        }

        public static UpgradeSlotBuilder of() {
            return new UpgradeSlotBuilder();
        }

        public UpgradeSlotBuilder set(int slot, ItemStack item, Pos pos) {
            this.storage.put(slot, new ItemPos(item, pos));
            return this;
        }

        public ItemStack getItem(int slot) {
            return this.storage.get(slot).getItem();
        }

        public Pos getPos(int slot) {
            return this.storage.get(slot).getPos();
        }

        public Map<Integer, ItemPos> getAll() {
            return this.storage;
        }

        public List<ItemStack> toItems() {
            return this.storage.values()
                    .stream()
                    .map(ItemPos::getItem)
                    .toList();
        }

        public static class ItemPos {
            private final ItemStack item;
            private final Pos pos;

            public ItemPos(ItemStack item, Pos pos) {
                this.item = item;
                this.pos = pos;
            }

            public ItemStack getItem() {
                return item;
            }

            public Pos getPos() {
                return pos;
            }
        }

    }