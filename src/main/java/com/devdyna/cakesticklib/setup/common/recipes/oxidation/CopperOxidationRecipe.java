package com.devdyna.cakesticklib.setup.common.recipes.oxidation;

import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.OxidationStatus.OxidationInput;
import com.devdyna.cakesticklib.setup.registry.types.zLibRecipeTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class CopperOxidationRecipe extends BaseRecipeType<OxidationInput> {

    private OxidationStatus type;
    private Ingredient catalyst;

    public CopperOxidationRecipe(OxidationStatus type, Ingredient catalyst) {
        this.type = type;
        this.catalyst = catalyst;
    }

    public boolean matches(OxidationInput r, Level l) {
        return r.type().equals(type);
    }

    @Override
    public ItemStack assemble(OxidationInput i) {
        return i.getItem(0);
    }

    public OxidationStatus getOxidationType() {
        return type;
    }

    public Ingredient getCatalyst() {
        return catalyst;
    }

    @Override
    public Item getToastIcon() {
        return Items.COPPER_BLOCK;
    }

    public static final RecipeSerializer<CopperOxidationRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<CopperOxidationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            OxidationStatus.CODEC.fieldOf("step").forGetter(CopperOxidationRecipe::getOxidationType),
            Ingredient.CODEC.fieldOf("catalyst").forGetter(CopperOxidationRecipe::getCatalyst))
            .apply(inst, CopperOxidationRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CopperOxidationRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    OxidationStatus.STREAM_CODEC, CopperOxidationRecipe::getOxidationType,
                    Ingredient.CONTENTS_STREAM_CODEC,
                    CopperOxidationRecipe::getCatalyst,
                    CopperOxidationRecipe::new);

    @Override
    public RecipeSerializer<? extends Recipe<OxidationInput>> getSerializer() {
        return zLibRecipeTypes.COPPER_OXIDATION.getSerializer();
    }

    @Override
    public RecipeType<? extends Recipe<OxidationInput>> getType() {
        return zLibRecipeTypes.COPPER_OXIDATION.getType();
    }

    @Override
    public String group() {
        return "copper_oxidation";
    }

}