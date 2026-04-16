package com.devdyna.cakesticklib.api.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ClazzUtil {

    public static String[] getAllStrings(Class<?> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            return java.util.Arrays.stream(fields)
                    .filter(f -> f.getType() == String.class)
                    .map(f -> {
                        try {
                            f.setAccessible(true);
                            return (String) f.get(null);
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(s -> s != null)
                    .toArray(String[]::new);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static List<Item> include(List<DeferredHolder<Item, ?>> list, DeferredHolder<Block, Block>... blocks) {
        // DeferredHolder<Item, ?> -> Item
        List<Item> items = list.stream()
                .map(DeferredHolder::get)
                .collect(Collectors.toList());

        // DeferredHolder<Block, Block> -> list
        List<Item> blockItems = Stream.of(blocks)
                .map(DeferredHolder::get)
                .map(block -> block.asItem())
                .collect(Collectors.toList());

        items.addAll(blockItems);
        return items;
    }

}
