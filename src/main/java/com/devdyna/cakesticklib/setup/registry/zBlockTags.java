package com.devdyna.cakesticklib.setup.registry;

import com.devdyna.cakesticklib.api.RegistryUtils;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

public class zBlockTags {
    public static void register(IEventBus bus) {
    }

    public static final TagKey<Block> BLOCK_STEEL = RegistryUtils
            .tagBlock("c", "storage_blocks/steel");

    public static final TagKey<Block> BLOCK_ADVANCED_ALLOY = RegistryUtils
            .tagBlock("c", "storage_blocks/advanced_alloy");

    public static final TagKey<Block> BLOCK_WROUGHT_IRON = RegistryUtils
            .tagBlock("c", "storage_blocks/wrought_iron");
}
