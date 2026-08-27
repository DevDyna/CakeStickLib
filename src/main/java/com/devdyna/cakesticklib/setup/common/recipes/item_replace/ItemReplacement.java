package com.devdyna.cakesticklib.setup.common.recipes.item_replace;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;

public class ItemReplacement {

    private final Ingredient base;
    private final ItemStackTemplate result;

    public ItemReplacement(Ingredient base, ItemStackTemplate result) {
        this.base = base;
        this.result = result;
    }

    public static ItemReplacement of(Ingredient base, ItemStackTemplate result) {
        return new ItemReplacement(base, result);
    }

    public Ingredient getBase() {
        return base;
    }

    public ItemStackTemplate getResult() {
        return result;
    }
    
@Deprecated
    public boolean test(ItemStack stack) {
        return base.test(stack);
    }

    public boolean isResult(ItemStack stack) {
        return result.is(stack.getItem());
    }

    public boolean isBase(ItemStack stack) {
        return base.test(stack);
    }

    public static final MapCodec<ItemReplacement> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(

            Ingredient.CODEC
                    .fieldOf("base")
                    .forGetter(ItemReplacement::getBase),

            ItemStackTemplate.CODEC
                    .fieldOf("result")
                    .forGetter(ItemReplacement::getResult)

    ).apply(
            inst,
            ItemReplacement::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemReplacement> STREAM_CODEC = StreamCodec.composite(

            Ingredient.CONTENTS_STREAM_CODEC,
            ItemReplacement::getBase,

            ItemStackTemplate.STREAM_CODEC,
            ItemReplacement::getResult,

            ItemReplacement::new);
}