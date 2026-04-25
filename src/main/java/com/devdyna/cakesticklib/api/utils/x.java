package com.devdyna.cakesticklib.api.utils;

import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet.Named;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class x {
    // Basic Resourcelocation stuff
    // -------------------------------------------------//

    public static Identifier rl(String modid, String s) {
        return Identifier.fromNamespaceAndPath(modid, s);
    }

    public static Identifier mcLoc(String s) {
        return rl("minecraft", s);
    }

    public static Identifier parse(String s) {
        return Identifier.parse(s);
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    public static <T> Identifier rl(DefaultedRegistry<T> d, T i) {
        return d.getKey(i);
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    public static <T> Identifier rl(DefaultedRegistry<T> d, T i, String modid) {
        return rl(modid, path(d, i));
    }

    public static Identifier rl(Item i) {
        return rl(BuiltInRegistries.ITEM, i);
    }

    public static Identifier rl(Block i) {
        return rl(BuiltInRegistries.BLOCK, i);
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    public static <T> String path(DefaultedRegistry<T> d, T i) {
        return d.getKey(i).getPath();
    }

    public static String path(Item i) {
        return path(BuiltInRegistries.ITEM, i);
    }

    public static String path(Fluid i) {
        return path(BuiltInRegistries.FLUID, i);
    }

    public static String path(ItemStack i) {
        return path(i.getItem());
    }

    public static String path(Block i) {
        return path(BuiltInRegistries.BLOCK, i);
    }

    public static String path(BlockState i) {
        return path(i.getBlock());
    }

    /**
     * @param <T>
     * @param d   <code>BuiltInRegistries.BLOCK</code>
     * @param i   <code>"stone"</code>
     */
    public static <T> T get(DefaultedRegistry<T> d, String modid, String i) {
        return d.getValue(rl(modid, i));
    }

    public static <T> T get(DefaultedRegistry<T> d, Identifier i) {
        return d.getValue(i);
    }

    /**
     * use <code>x.rl(Item)</code>
     */
    @Deprecated
    public static Identifier id(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public static String getMod(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getNamespace();
    }
    public static String getMod(ItemLike item) {
        return getMod(item.asItem());
    }

    public static String getTagName(TagKey<?> tag) {
        return tag.location().getPath().toString();
    }

    public static Item get(Identifier rl) {
        return BuiltInRegistries.ITEM.getValue(rl);
    }

    // Stack types
    // -------------------------------------------------//

    public static ItemStack item(Item i, int c) {
        return new ItemStack(i, c);
    }

    public static ItemStack item(DeferredHolder<Item, Item> i, int c) {
        return item(i.get(), c);
    }

    public static ItemStack item(ItemLike i) {
        return x.item(i.asItem(), 1);
    }

    public static ItemStack item(BlockState i) {
        return item(i.getBlock());
    }

    public static FluidStack fluid(Fluid f) {
        return fluid(f, 1000);
    }

    public static FluidStack fluid(Fluid f, int amount) {
        return new FluidStack(f, amount);
    }

    // Stack templates
    // -------------------------------------------------//

    public static ItemStackTemplate itemTemplate(Item i, int c) {
        return new ItemStackTemplate(i, c);
    }

    public static ItemStackTemplate itemTemplate(DeferredHolder<Item, Item> i, int c) {
        return itemTemplate(i.get(), c);
    }

    public static ItemStackTemplate itemTemplate(ItemLike i) {
        return x.itemTemplate(i.asItem(), 1);
    }

    public static ItemStackTemplate itemTemplate(BlockState i) {
        return itemTemplate(i.getBlock());
    }

    public static FluidStackTemplate fluidTemplate(Fluid i, int c) {
        return new FluidStackTemplate(i, c);
    }

    public static FluidStackTemplate fluidTemplate(DeferredHolder<Fluid, Fluid> i, int c) {
        return fluidTemplate(i.get(), c);
    }

    // Simple Ingredient types
    // -------------------------------------------------//

    public static Ingredient itemIngredient(ItemStack... i) {
        return Ingredient.of(Stream.of(i)
                .map(ItemStack::getItem));
    }

    public static Ingredient itemIngredient(ItemLike... i) {
        return Ingredient.of(i);
    }

    public static Ingredient itemIngredient(Named<Item> i) {
        return Ingredient.of(i);
    }

    public static Ingredient itemIngredient(Item i) {
        return itemIngredient(List.of(i));
    }

    public static Ingredient itemIngredient(DeferredHolder<Item, ?> i) {
        return itemIngredient(i.get());
    }

    public static Ingredient itemIngredient(ItemStackTemplate[] i) {
        return x.itemIngredient(Arrays.asList(i).stream()
                .map(ItemStackTemplate::item)
                .map(Holder::value)
                .toList());
    }

    @Deprecated
    public static Ingredient itemIngredient(TagKey<Item> i) {
        return Ingredient.of(BuiltInRegistries.ITEM.getOrThrow(i));
    }

    public static Ingredient itemIngredient(TagKey<Item> i, HolderLookup.Provider p) {
        return Ingredient.of(p.getOrThrow(i));
    }

    /**
     * This method will create a new itemtag!
     */
    public static Ingredient itemIngredient(Identifier tag) {
        return itemIngredient(TagKey.create(Registries.ITEM, tag));
    }

    public static Ingredient itemIngredient(List<Item> list) {
        return itemIngredient(list.stream().toArray(ItemLike[]::new));
    }

    public static FluidIngredient fluidIngredient(Fluid i) {
        return FluidIngredient.of(i);
    }

    @Deprecated
    public static FluidIngredient fluidIngredient(TagKey<Fluid> i) {
        return FluidIngredient.of(BuiltInRegistries.FLUID.getOrThrow(i));
    }

    public static FluidIngredient fluidIngredient(TagKey<Fluid> i, HolderLookup.Provider p) {
        return FluidIngredient.of(p.getOrThrow(i));
    }

    public static FluidIngredient fluidIngredient(Identifier tag) {
        return fluidIngredient(TagKey.create(Registries.FLUID, tag));
    }

    // Sized Ingredients types
    // -------------------------------------------------//

    public static SizedIngredient itemSized(Identifier tag) {
        return itemSized(itemIngredient(tag));
    }

    public static SizedIngredient itemSized(ItemLike stack) {
        return itemSized(stack, 1);
    }

    public static SizedIngredient itemSized(TagKey<Item> stack, HolderLookup.Provider p) {
        return itemSized(stack, 1, p);
    }

    public static SizedIngredient itemSized(ItemLike stack, int c) {
        return SizedIngredient.of(stack, c);
    }

    public static SizedIngredient itemSized(TagKey<Item> stack, int c, HolderLookup.Provider p) {
        return itemSized(itemIngredient(stack, p), c);
    }

    public static SizedIngredient itemSized(ItemStack stack) {
        return itemSized(stack.getItem(), stack.getCount());
    }

    public static SizedIngredient itemSized(Ingredient i, int c) {
        return new SizedIngredient(i, c);
    }

    public static SizedIngredient itemSized(Ingredient i) {
        return itemSized(i, 1);
    }

    public static SizedFluidIngredient fluidSized(TagKey<Fluid> tag, int amount, HolderLookup.Provider p) {
        return fluidSized(fluidIngredient(tag, p), amount);
    }

    public static SizedFluidIngredient fluidSized(Fluid fluid, int amount) {
        return SizedFluidIngredient.of(fluid, amount);
    }

    public static SizedFluidIngredient fluidSized(FluidIngredient fluid, int amount) {
        return new SizedFluidIngredient(fluid, amount);
    }

    public static SizedFluidIngredient fluidSized(TagKey<Fluid> tag, HolderLookup.Provider p) {
        return fluidSized(tag, 1000, p);
    }

    public static SizedFluidIngredient fluidSized(Fluid fluid) {
        return fluidSized(fluid, 1000);
    }

    // Block types
    // -------------------------------------------------//

    public static Block block(DeferredHolder<Block, ?> b) {
        return b.get();
    }

    public static BlockState state(DeferredHolder<Block, ?> b) {
        return block(b).defaultBlockState();
    }

    public static Block block(BlockState b) {
        return b.getBlock();
    }

    // other
    // -------------------------------------------------//

    public static <T> ItemStack stack(DeferredHolder<T, ?> holder) {
        T obj = holder.get();
        if (obj instanceof Item item) {
            return item.getDefaultInstance();
        } else if (obj instanceof Block block) {
            return x.item(block.asItem());
        } else {
            throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
        }
    }

    public static List<FluidStack> getFluids(SizedFluidIngredient i) {
        return getFluids(i.ingredient());
    }

    public static List<FluidStack> getFluids(FluidIngredient i) {
        return i.fluids().stream()
                .map(Holder::value)
                .map(x::fluid).toList();
    }

    public static boolean matchAny(SizedFluidIngredient s, SizedFluidIngredient f) {
        return getFluids(s).stream().anyMatch(i -> f.test(i));
    }

    public static boolean matchAny(SizedIngredient s, SizedIngredient f) {
        return getItems(s).stream().anyMatch(i -> f.test(i));
    }

    public static List<ItemStack> getItems(SizedIngredient i) {
        return getItems(i.ingredient());
    }

    public static List<ItemStack> getItems(Ingredient i) {
        return i.getValues().stream()
                .map(Holder::value)
                .map(x::item).toList();
    }

    public static Block[] toBlocks(DeferredHolder<Block, ?>... blocks) {
        return Arrays.asList(blocks).stream().map(DeferredHolder::get).toArray(Block[]::new);
    }

    public static Item[] toItems(DeferredHolder<Block, ?>... blocks) {
        return toItems(toBlocks(blocks));
    }

    public static Item[] toItems(Block... blocks) {
        return Arrays.asList(blocks).stream().map(Block::asItem).toArray(Item[]::new);
    }

    public static List<Optional<Reference<Block>>> getBlocks(DataMapType<Block, ?> datamap) {
        return BuiltInRegistries.BLOCK.getDataMap(datamap).entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .map(ResourceKey::identifier)
                .map(BuiltInRegistries.BLOCK::get).toList();
    }

    public static List<Optional<Reference<Item>>> getItems(DataMapType<Item, ?> datamap) {
        return BuiltInRegistries.ITEM.getDataMap(datamap).entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .map(ResourceKey::identifier)
                .map(BuiltInRegistries.ITEM::get).toList();
    }

}
