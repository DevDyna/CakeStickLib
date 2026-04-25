package com.devdyna.cakesticklib.setup.common.recipes.hammering;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.devdyna.cakesticklib.api.utils.ArrayUtils;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;

public class HammeringRecipe extends ShapelessRecipe {

    private final List<Ingredient> items;
    private final ItemStackTemplate result;
    private final InputToolDurability type;

    public HammeringRecipe(InputToolDurability input, List<Ingredient> items, ItemStackTemplate result) {
        super(RecipeBuilder.createCraftingCommonInfo(true),
                RecipeBuilder.createCraftingBookInfo(RecipeCategory.MISC, "hammering"), result,
                ArrayUtils.concat(items, input.getTool()));
        this.type = input;
        this.items = List.copyOf(items);
        this.result = result;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return result.create();
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {

        if (!type.checkTool(input, level)) {
            return false;
        }

        List<ItemStack> stacks = input.items().stream()
                .filter(s -> !s.isEmpty() && !type.isTool(s))
                .toList();

        if (stacks.size() != items.size()) {
            return false;
        }

        List<Ingredient> required = new ArrayList<>(items);

        for (ItemStack stack : stacks) {
            boolean matched = false;

            for (Iterator<Ingredient> it = required.iterator(); it.hasNext();) {
                Ingredient ing = it.next();
                if (ing.test(stack)) {
                    it.remove();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                return false;
            }
        }

        return required.isEmpty();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int slot = 0; slot < input.size(); ++slot) {
            ItemStack stack = input.getItem(slot);

            if (stack.isEmpty()) {
                continue;
            }

            if (type.isTool(stack)) {
                remaining.set(slot, type.getRemainItem(stack));
                continue;
            }

            ItemStackTemplate remainder = stack.getCraftingRemainder();
            if (remainder != null) {
                ItemStack remStack = remainder.create();
                if (!remStack.isEmpty()) {
                    remaining.set(slot, remStack);
                }
            }
        }

        return remaining;
    }

    public InputToolDurability getInput() {
        return type;
    }

    public List<Ingredient> getItems() {
        return items;
    }

    public ItemStackTemplate getResult() {
        return result;
    }

    public static final RecipeSerializer<HammeringRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<HammeringRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            InputToolDurability.CODEC.fieldOf("mode").forGetter(HammeringRecipe::getInput),
            Ingredient.CODEC.listOf().fieldOf("items").forGetter(HammeringRecipe::getItems),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(HammeringRecipe::getResult))
            .apply(inst, HammeringRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, HammeringRecipe> STREAM_CODEC = StreamCodec.composite(
            InputToolDurability.STREAM_CODEC, HammeringRecipe::getInput,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(8)), HammeringRecipe::getItems,
            ItemStackTemplate.STREAM_CODEC, HammeringRecipe::getResult,
            HammeringRecipe::new);
}