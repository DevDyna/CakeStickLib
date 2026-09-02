package com.devdyna.cakesticklib.api.utils;

import java.util.ArrayList;
import java.util.List;

public class SlotBuilder {

    public enum ResourceType {

        /**
         * can be insered but not extracted
         */
        INPUT,
        /**
         * can be extracted but not insered
         */
        OUTPUT,
        /**
         * can be insered and extracted
         */
        BUFFER,
        /**
         * cannot be handled
         */
        NONE;

    }

    private List<ResourceType> list;

    public SlotBuilder(int slots) {
        List<ResourceType> a = new ArrayList<>();
            for (int i = 0; i < slots; i++)
                a.add(ResourceType.NONE);
            this.list = a;
    }

    public static SlotBuilder of(int slots) {
        return new SlotBuilder(slots);
    }

    public SlotBuilder set(int slot, ResourceType type) {
        list.set(slot, type);
        return this;
    }

    public SlotBuilder setAll(ResourceType type, Integer... slots) {
        for (int s : slots)
            list.set(s, type);
        return this;
    }

    public SlotBuilder setAll(ResourceType type, List<Integer> slots) {
        return setAll(type, slots.toArray(Integer[]::new));
    }

    public List<ResourceType> get() {
        return list;
    }

    public List<Integer> get(ResourceType type) {
        return list.stream().filter(t->t.equals(type)).map(a->list.indexOf(a)).toList();
    }

    public ResourceType get(int slot) {
        return list.get(slot);
    }

}
