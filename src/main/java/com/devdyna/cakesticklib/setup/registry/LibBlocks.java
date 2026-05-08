package com.devdyna.cakesticklib.setup.registry;

import java.util.function.Function;

import com.devdyna.cakesticklib.CakeStickLib;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LibBlocks {
        public static void register(IEventBus bus) {
                zBlockItem.register(bus);
        }

        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister
                        .createBlocks(CakeStickLib.MODULE_ID);

        public static final DeferredHolder<Block, Block> WROUGHT_IRON_BLOCK = registerItemBlock(
                        "wrought_iron_block",
                        p -> p.sound(SoundType.METAL)
                                        .strength(2.5f)
                                        .mapColor(MapColor.RAW_IRON));

        public static final DeferredHolder<Block, Block> ADVANCED_ALLOY_BLOCK = registerItemBlock(
                        "advanced_alloy_block",
                        p -> p.sound(SoundType.METAL)
                                        .strength(2f)
                                        .mapColor(MapColor.METAL));

        public static final DeferredHolder<Block, Block> STEEL_BLOCK = registerItemBlock(
                        "steel_block",
                        p -> p.sound(SoundType.METAL)
                                        .strength(2f)
                                        .mapColor(MapColor.METAL));

        private static DeferredHolder<Block, Block> registerItemBlock(String blockname,
                        Function<Properties, Properties> sup) {
                DeferredHolder<Block, Block> block = LibBlocks.zBlockItem.registerBlock(blockname,
                                p -> new Block(sup.apply(p)));
                LibItems.zBlockItem.registerSimpleBlockItem(block);
                return block;
        }

}