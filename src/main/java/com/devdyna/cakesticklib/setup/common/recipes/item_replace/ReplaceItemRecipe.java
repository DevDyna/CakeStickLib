package com.devdyna.cakesticklib.setup.common.recipes.item_replace;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.devdyna.cakesticklib.api.LoreTweaker;
import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.NormalCraftingRecipe;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;

public class ReplaceItemRecipe extends NormalCraftingRecipe {

        private final List<Ingredient> items;
        private final List<ItemReplacement> filters;
        private final ItemStackTemplate result;

        public ReplaceItemRecipe(List<Ingredient> items, List<ItemReplacement> filters, ItemStackTemplate result) {
                super(RecipeBuilder.createCraftingCommonInfo(true),
                                RecipeBuilder.createCraftingBookInfo(RecipeCategory.MISC, "replace_item"));

                if (items.isEmpty() && filters.isEmpty())
                        throw new IllegalArgumentException(
                                        "Replacement recipe requires at least one input");

                if (items.size() + filters.size() > 9)
                        throw new IllegalArgumentException(
                                        "Replacement recipe cannot contain more than 9 required inputs");

                this.items = List.copyOf(items);
                this.filters = List.copyOf(filters);
                this.result = result;
        }

        public List<Ingredient> getItems() {
                return items;
        }

        public List<ItemReplacement> getFilters() {
                return filters;
        }

        public ItemStackTemplate getResult() {
                return result;
        }

        @Override
        public ItemStack assemble(CraftingInput input) {
                return result.create();
        }

        @Override
        public boolean matches(CraftingInput input, Level level) {

                List<ItemStack> remaining = new ArrayList<>();

                for (var stack : input.items())
                        if (!stack.isEmpty())
                                remaining.add(stack);

                if (remaining.size() != (items.size() + filters.size()))
                        return false;

                for (var filter : filters) {

                        ItemStack matched = null;

                        for (var stack : remaining)
                                if (filter.getBase().test(stack)) {
                                        matched = stack;
                                        break;
                                }

                        if (matched == null)
                                return false;

                        remaining.remove(matched);
                }

                for (var item : items) {

                        ItemStack matched = null;

                        for (var stack : remaining)
                                if (item.test(stack)) {
                                        matched = stack;
                                        break;
                                }

                        if (matched == null)
                                return false;

                        remaining.remove(matched);
                }

                return remaining.isEmpty();
        }

        @Override
        public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {

                var remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);

                List<ItemReplacement> remainingFilters = new ArrayList<>(filters);

                for (int slot = 0; slot < input.size(); ++slot) {

                        var stack = input.getItem(slot);

                        if (stack.isEmpty())
                                continue;

                        ItemReplacement matched = null;

                        for (var filter : remainingFilters)
                                if (filter.getBase().test(stack)) {
                                        matched = filter;
                                        break;
                                }

                        if (matched == null)
                                continue;

                        var replacement = matched.getResult();

                        if (replacement != null)
                                remaining.set(slot, replacement.create());

                        remainingFilters.remove(matched);
                }

                return remaining;
        }

        @Override
        protected PlacementInfo createPlacementInfo() {
                List<Ingredient> ingredients = new ArrayList<>();
                ingredients.addAll(items);

                for (var filter : filters)
                        ingredients.add(filter.getBase());

                return PlacementInfo.create(ingredients);
        }

        @Override
        public List<RecipeDisplay> display() {

                List<SlotDisplay> inputs = new ArrayList<>();

                for (var filter : filters) {

                        List<ItemStack> baseItems = new ArrayList<>();

                        var replace = filter.getResult()
                                        .create()
                                        .copy();

                        for (var item : x.getItemStacksFromIngredient(filter.getBase())) {

                                if (filter.getBase().test(replace))
                                        LoreTweaker.advancedLore(item,
                                                        Component.translatable(MODULE_ID + ".ui.dont_consume"),
                                                        Style.EMPTY.withColor(ChatFormatting.RED));

                                else
                                        LoreTweaker.advancedLore(item,
                                                        Component.translatable(MODULE_ID
                                                                        + ".jei.recipe.item_replace.remainder",
                                                                        replace.getHoverName()),
                                                        Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE));

                                baseItems.add(item);
                        }

                        inputs.add(new SlotDisplay.Composite(Arrays.asList(baseItems.stream()
                                        .map(i -> new SlotDisplay.ItemStackSlotDisplay(x.itemTemplate(i)))
                                        .toArray(SlotDisplay[]::new))));
                }

                inputs.addAll(items.stream().map(Ingredient::display).toList());

                return List.of(new ShapelessCraftingRecipeDisplay(inputs,
                                new SlotDisplay.ItemStackSlotDisplay(result),
                                new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)));
        }

        @Override
        public RecipeSerializer<? extends NormalCraftingRecipe> getSerializer() {
                return SERIALIZER;
        }

        public static final MapCodec<ReplaceItemRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(

                        Ingredient.CODEC
                                        .listOf()
                                        .fieldOf("items")
                                        .forGetter(
                                                        ReplaceItemRecipe::getItems),

                        ItemReplacement.CODEC
                                        .codec()
                                        .listOf()
                                        .fieldOf("filters")
                                        .forGetter(
                                                        ReplaceItemRecipe::getFilters),

                        ItemStackTemplate.CODEC
                                        .fieldOf("result")
                                        .forGetter(
                                                        ReplaceItemRecipe::getResult)

        ).apply(inst, ReplaceItemRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ReplaceItemRecipe> STREAM_CODEC = StreamCodec
                        .composite(

                                        Ingredient.CONTENTS_STREAM_CODEC
                                                        .apply(ByteBufCodecs.list(9)),
                                        ReplaceItemRecipe::getItems,

                                        ItemReplacement.STREAM_CODEC
                                                        .apply(ByteBufCodecs.list(9)),
                                        ReplaceItemRecipe::getFilters,

                                        ItemStackTemplate.STREAM_CODEC,
                                        ReplaceItemRecipe::getResult,

                                        ReplaceItemRecipe::new);

        public static final RecipeSerializer<ReplaceItemRecipe> SERIALIZER = new RecipeSerializer<>(
                        CODEC, STREAM_CODEC);
}