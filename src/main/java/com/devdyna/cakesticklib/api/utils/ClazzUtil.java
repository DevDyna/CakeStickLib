package com.devdyna.cakesticklib.api.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
@Deprecated
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

    public static <T> List<T> getAll(Class<?> clazz, Class<T> type) {
        List<T> result = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(type)) {
                try {
                    result.add(type.cast(field.get(null)));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(
                            "Failed to access field: " + field.getName(), e);
                }
            }
        }

        return result;
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
