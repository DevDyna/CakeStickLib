package com.devdyna.cakesticklib.setup.common.recipes.tool_durability;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.devdyna.cakesticklib.api.LoreTweaker;
import com.devdyna.cakesticklib.api.utils.ArrayUtils;
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

public class DurabilityConsumeRecipe extends NormalCraftingRecipe {

    private final List<Ingredient> items;
    private final ItemStackTemplate result;
    private final InputToolDurability tool;

    public DurabilityConsumeRecipe(InputToolDurability input, List<Ingredient> items, ItemStackTemplate result) {
        super(RecipeBuilder.createCraftingCommonInfo(true),
                RecipeBuilder.createCraftingBookInfo(RecipeCategory.MISC, "durability_use"));

        this.tool = input;
        this.items = List.copyOf(items);
        this.result = result;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return result.create();
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {

        if (!tool.test(input, level))
            return false;

        var stacks = input.items().stream()
                .filter(Predicate.not(ItemStack::isEmpty))
                .filter(Predicate.not(tool::isTool))
                .toList();

        if (stacks.size() != items.size())
            return false;

        List<Ingredient> required = new ArrayList<>(items);

        for (var item : stacks) {

            var matched = false;

            for (var it = required.iterator(); it.hasNext();)
                if (it.next().test(item)) {
                    it.remove();
                    matched = true;
                    break;
                }

            if (!matched)
                return false;

        }

        return required.isEmpty();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {

        var remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int slot = 0; slot < input.size(); ++slot) {

            var stack = input.getItem(slot);

            if (stack.isEmpty())
                continue;

            if (tool.isTool(stack)) {
                remaining.set(slot, tool.getRemainItem(stack));
                continue;
            }

            var remainder = stack.getCraftingRemainder();

            remaining.set(slot, remainder != null ? remainder.create() : ItemStack.EMPTY);

        }

        return remaining;
    }

    @Override
    protected PlacementInfo createPlacementInfo() {
        return PlacementInfo.create(ArrayUtils.concat(items, tool.getTool()));
    }

    @Override
    public List<RecipeDisplay> display() {

        var item = x.getItemStacksFromIngredient(tool.getTool()).getFirst().copy();

        LoreTweaker.advancedLore(item,
                Component.translatable(MODULE_ID + ".jei.recipe.durability_consume", tool.getDurability()),
                Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE));

        return List.of(
                new ShapelessCraftingRecipeDisplay(
                        ArrayUtils.concat(items.stream().map(Ingredient::display).toList(),
                                new SlotDisplay.ItemStackSlotDisplay(ItemStackTemplate.fromNonEmptyStack(item))),
                        new SlotDisplay.ItemStackSlotDisplay(result),
                        new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)));
    }

    public InputToolDurability getInput() {
        return tool;
    }

    public List<Ingredient> getItems() {
        return items;
    }

    public ItemStackTemplate getResult() {
        return result;
    }

    public static final MapCodec<DurabilityConsumeRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            InputToolDurability.CODEC
                    .fieldOf("mode")
                    .forGetter(DurabilityConsumeRecipe::getInput),

            Ingredient.CODEC.listOf()
                    .fieldOf("items")
                    .forGetter(DurabilityConsumeRecipe::getItems),

            ItemStackTemplate.CODEC
                    .fieldOf("result")
                    .forGetter(DurabilityConsumeRecipe::getResult)

    ).apply(inst, DurabilityConsumeRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, DurabilityConsumeRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    InputToolDurability.STREAM_CODEC,
                    DurabilityConsumeRecipe::getInput,

                    Ingredient.CONTENTS_STREAM_CODEC
                            .apply(ByteBufCodecs.list(8)),
                    DurabilityConsumeRecipe::getItems,

                    ItemStackTemplate.STREAM_CODEC,
                    DurabilityConsumeRecipe::getResult,

                    DurabilityConsumeRecipe::new);

    public static final RecipeSerializer<DurabilityConsumeRecipe> SERIALIZER = new RecipeSerializer<>(
            CODEC,
            STREAM_CODEC);

    @Override
    public RecipeSerializer<? extends NormalCraftingRecipe> getSerializer() {
        return SERIALIZER;
    }

}