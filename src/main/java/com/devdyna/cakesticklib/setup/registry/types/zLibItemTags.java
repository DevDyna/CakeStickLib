package com.devdyna.cakesticklib.setup.registry.types;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.setup.registry.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class zLibItemTags {

    public static final TagKey<Item> OXIDIZER = Material.tagItem(MODULE_ID,"oxidizer");
    public static final TagKey<Item> WAXING = Material.tagItem(MODULE_ID,"waxing");
}
