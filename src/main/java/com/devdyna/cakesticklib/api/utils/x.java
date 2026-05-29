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
    private static <T> Identifier rl(DefaultedRegistry<T> d, T i) {
        return d.getKey(i);
    }

    /**
     * @param d <code>BuiltInRegistries.BLOCK</code>
     * @param i <code>Blocks.STONE</code>
     */
    @Deprecated
    public static <T> Identifier rl(DefaultedRegistry<T> d, T i, String modid) {
        return rl(modid, path(d, i));
    }

    public static Identifier rl(Item i) {
        return rl(BuiltInRegistries.ITEM, i);
    }

    public static Identifier rl(TagKey<?> t) {
        return t.location();
    }

    public static Identifier rl(Block i) {
        return rl(BuiltInRegistries.BLOCK, i);
    }

    /**
     * {@code path(?)} -> {@code name(?)}
     */
    private static <T> String path(DefaultedRegistry<T> d, T i) {
        return rl(d, i).getPath();
    }

    public static String name(TagKey<?> t) {
        return rl(t).getPath();
    }

    public static String name(Item i) {
        return path(BuiltInRegistries.ITEM, i);
    }

    public static String name(Fluid i) {
        return path(BuiltInRegistries.FLUID, i);
    }

    public static String name(ItemStack i) {
        return name(i.getItem());
    }

    public static String name(Block i) {
        return path(BuiltInRegistries.BLOCK, i);
    }

    public static String name(BlockState i) {
        return name(i.getBlock());
    }

    public static String name(ItemStackTemplate i) {
        return name(i.item().value());
    }

    public static String name(ItemLike i) {
        return name(i.asItem());
    }

    private static <T> String mod(DefaultedRegistry<T> d, T i) {
        return rl(d, i).getNamespace();
    }

    public static String mod(TagKey<?> t) {
        return rl(t).getNamespace();
    }

    public static String mod(Item i) {
        return mod(BuiltInRegistries.ITEM, i);
    }

    public static String mod(Fluid i) {
        return mod(BuiltInRegistries.FLUID, i);
    }

    public static String mod(ItemStack i) {
        return mod(i.getItem());
    }

    public static String mod(ItemStackTemplate i) {
        return mod(i.item().value());
    }

    public static String mod(ItemLike i) {
        return mod(i.asItem());
    }

    public static String mod(Block i) {
        return mod(BuiltInRegistries.BLOCK, i);
    }

    public static String mod(BlockState i) {
        return mod(i.getBlock());
    }

    @Deprecated
    public static <T> T get(DefaultedRegistry<T> d, String modid, String i) {
        return d.getValue(rl(modid, i));
    }

    private static <T> T get(DefaultedRegistry<T> d, Identifier i) {
        return d.getValue(i);
    }

    public static Item getItem(Identifier rl) {
        return get(BuiltInRegistries.ITEM, rl);
    }

    public static Fluid getFluid(Identifier rl) {
        return get(BuiltInRegistries.FLUID, rl);
    }

    public static Block getBlock(Identifier rl) {
        return get(BuiltInRegistries.BLOCK, rl);
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
    @Deprecated
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

    @Deprecated
    public static Block block(DeferredHolder<Block, ?> b) {
        return b.get();
    }

    @Deprecated
    public static BlockState state(DeferredHolder<Block, ?> b) {
        return block(b).defaultBlockState();
    }

    @Deprecated
    public static Block block(BlockState b) {
        return b.getBlock();
    }

    // other
    // -------------------------------------------------//

    @Deprecated
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

    @Deprecated
    public static List<FluidStack> getFluids(SizedFluidIngredient i) {
        return getFluids(i.ingredient());
    }

    @Deprecated
    public static List<FluidStack> getFluids(FluidIngredient i) {
        return i.fluids().stream()
                .map(Holder::value)
                .map(x::fluid).toList();
    }

    /**
     * Mainly useful on {@code unlockedBy()} recipebuilder method
     */
    public static Fluid[] getFluidsFromIngredient(FluidIngredient f) {
        return f.fluids().stream()
                .map(Holder::getKey)
                .map(ResourceKey::identifier)
                .map(BuiltInRegistries.FLUID::getValue)
                .toArray(Fluid[]::new);
    }

    /**
     * Mainly useful on {@code unlockedBy()} recipebuilder method
     */
    public static Item[] getItemsFromIngredient(Ingredient i) {
        return i.getValues().stream()
                .map(Holder::getKey)
                .map(ResourceKey::identifier)
                .map(BuiltInRegistries.ITEM::getValue)
                .toArray(Item[]::new);
    }

    /**
     * Don't use on recipe builders!
     */
    public static List<FluidStack> getFluidStacksFromIngredient(FluidIngredient f) {
        return Arrays.asList(getFluidsFromIngredient(f))
                .stream()
                .map(s -> x.fluid(s))
                .toList();
    }

    /**
     * Don't use on recipe builders!
     */
    public static List<FluidStack> getFluidStacksFromIngredient(SizedFluidIngredient f) {
        return Arrays.asList(getFluidsFromIngredient(f.ingredient()))
                .stream()
                .map(s -> x.fluid(s, f.amount()))
                .toList();
    }

    /**
     * Don't use on recipe builders!
     */
    public static List<ItemStack> getItemStacksFromIngredient(Ingredient i) {
        return Arrays.asList(getItemsFromIngredient(i))
                .stream()
                .map(s -> x.item(s))
                .toList();
    }

    /**
     * Don't use on recipe builders!
     */
    public static List<ItemStack> getItemStacksFromIngredient(SizedIngredient i) {
        return Arrays.asList(getItemsFromIngredient(i.ingredient()))
                .stream()
                .map(s -> x.item(s, i.count()))
                .toList();
    }

    @Deprecated
    public static boolean matchAny(SizedFluidIngredient s, SizedFluidIngredient f) {
        return getFluids(s).stream().anyMatch(i -> f.test(i));
    }

    @Deprecated
    public static boolean matchAny(SizedIngredient s, SizedIngredient f) {
        return getItems(s).stream().anyMatch(i -> f.test(i));
    }

    @Deprecated
    public static List<ItemStack> getItems(SizedIngredient i) {
        return getItems(i.ingredient());
    }

    @Deprecated
    public static List<ItemStack> getItems(Ingredient i) {
        return i.getValues().stream()
                .map(Holder::value)
                .map(x::item).toList();
    }

    public static Block[] toBlocks(DeferredHolder<Block, ?>... blocks) {
        return Arrays.asList(blocks)
                .stream()
                .map(DeferredHolder::get)
                .toArray(Block[]::new);
    }

    public static Item[] toItems(DeferredHolder<Block, ?>... blocks) {
        return toItems(toBlocks(blocks));
    }

    public static Item[] toItems(Block... blocks) {
        return Arrays.asList(blocks)
                .stream()
                .map(Block::asItem)
                .toArray(Item[]::new);
    }

    public static List<Optional<Reference<Block>>> getBlocks(DataMapType<Block, ?> datamap) {
        return getFrom(BuiltInRegistries.BLOCK, datamap);
    }

    public static List<Optional<Reference<Item>>> getItems(DataMapType<Item, ?> datamap) {
        return getFrom(BuiltInRegistries.ITEM, datamap);
    }

    public static <T> List<Optional<Reference<T>>> getFrom(
            DefaultedRegistry<T> registry,
            DataMapType<T, ?> datamap) {
        return registry.getDataMap(datamap).entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .map(ResourceKey::identifier)
                .map(registry::get)
                .toList();
    }

}
