package com.devdyna.cakesticklib.api;

import java.util.*;
import java.util.function.*;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class RegistryUtils {

        public static DeferredHolder<CreativeModeTab, CreativeModeTab> createCreativeTab(
                        String modid,
                        String id,
                        Supplier<Item> icon, DeferredRegister<CreativeModeTab> register) {
                return register
                                .register(modid + "_" + id, () -> CreativeModeTab.builder()
                                                .title(Component.translatable(
                                                                modid + ".creative_tab." + id))
                                                .icon(() -> icon.get().getDefaultInstance())
                                                .build());
        }

        /**
         * register an block + item
         * 
         * @param be  zBlockEntities.zTiles
         * @param sup () -> new Builder
         * @param b   Blocks.zBlock
         * @deprecated
         */
        @Deprecated(forRemoval = true)
        public static DeferredHolder<Block, Block> registerItemBlock(String blockname, Supplier<Block> sup,
                        DeferredRegister.Blocks b, DeferredRegister.Items i) {
                DeferredHolder<Block, Block> block = b.register(blockname, sup);
                i.registerSimpleBlockItem(block);
                return block;
        }

        /** @deprecated */
        @Deprecated(forRemoval = true)
        public static DeferredHolder<Block, Block> registerItemBlock(String blockname, DeferredRegister.Blocks b,
                        DeferredRegister.Items i) {
                return registerItemBlock(blockname, () -> new Block(BlockBehaviour.Properties.of()), b, i);
        }

        /**
         * create an itemtag
         */
        public static TagKey<Item> tagItem(String modname, String id) {
                return TagKey.create(BuiltInRegistries.ITEM.key(),
                                x.rl(modname, id));
        }

        /**
         * create an blocktag
         */
        public static TagKey<Block> tagBlock(String modname, String id) {
                return TagKey.create(BuiltInRegistries.BLOCK.key(),
                                x.rl(modname, id));
        }

        /**
         * create an fluidtag
         */
        public static TagKey<Fluid> tagFluid(String modname, String id) {
                return TagKey.create(BuiltInRegistries.FLUID.key(),
                                x.rl(modname, id));
        }

        /**
         * create an entity tag
         */
        public static TagKey<EntityType<?>> tagEntity(String modname, String id) {
                return TagKey.create(Registries.ENTITY_TYPE, x.rl(modname, id));
        }

        public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> createBlockEntity(
                        String id, DeferredRegister<BlockEntityType<?>> be,
                        BlockEntitySupplier<T> factory,
                        Supplier<? extends Block>... validBlocks) {
                return be.register(id,
                                () -> new BlockEntityType<>(factory,
                                                Arrays.stream(validBlocks).map(Supplier::get).toArray(Block[]::new)));
        }

        public static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String modname, String id) {
                return ResourceKey.create(Registries.CONFIGURED_FEATURE, x.rl(modname, id));
        }

        public static ResourceKey<PlacedFeature> createPlacedFeature(String modname, String id) {
                return ResourceKey.create(Registries.PLACED_FEATURE, x.rl(modname, id));
        }

        public static ResourceKey<BiomeModifier> createBiomeModifier(String modname, String id) {
                return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, x.rl(modname, id));
        }

        public static Item[] getItems(DeferredRegister.Items items) {
                return items.getEntries().stream().map(DeferredHolder::get).toArray(Item[]::new);
        }

}