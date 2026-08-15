package com.devdyna.cakesticklib.setup.common.recipes.oxidation;

import java.util.Optional;

import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.OxidationStatus.OxidationInput;
import com.devdyna.cakesticklib.setup.registry.LibRecipeTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class CopperOxidationRecipe extends BaseRecipeType<OxidationInput> {

    private final OxidationStatus type;
    private final Ingredient catalyst;
    private final Ingredient input;
    private final ItemStackTemplate output;

    public CopperOxidationRecipe(OxidationStatus type, Ingredient catalyst, Ingredient input,
            ItemStackTemplate output) {
        this.type = type;
        this.catalyst = catalyst;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(OxidationInput r, Level l) {

        if (!r.type().equals(type))
            return false;

        if (type == OxidationStatus.CUSTOM)
            return input != null && input.test(r.getItem(0));

        return true;
    }

    @Override
    public ItemStack assemble(OxidationInput i) {
        if (type == OxidationStatus.CUSTOM)
            return output.create().copy();

        return i.getItem(0).copy();
    }

    public OxidationStatus getOxidationType() {
        return type;
    }

    public Ingredient getCatalyst() {
        return catalyst;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    @Override
    public Item getToastIcon() {
        return Items.COPPER_BLOCK;
    }

    public static final RecipeSerializer<CopperOxidationRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<CopperOxidationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            OxidationStatus.CODEC
                    .fieldOf("step")
                    .forGetter(CopperOxidationRecipe::getOxidationType),

            Ingredient.CODEC
                    .optionalFieldOf("catalyst")
                    .forGetter(r -> Optional.ofNullable(r.getCatalyst())),

            Ingredient.CODEC
                    .optionalFieldOf("input")
                    .forGetter(r -> Optional.ofNullable(r.getInput())),

            ItemStackTemplate.CODEC
                    .optionalFieldOf("output")
                    .forGetter(r -> Optional.ofNullable(r.getOutput()))

    ).apply(inst, (type, catalyst, input, output) -> new CopperOxidationRecipe(
            type,
            catalyst.orElse(null),
            input.orElse(null),
            output.orElse(null))));

    public static final StreamCodec<RegistryFriendlyByteBuf, CopperOxidationRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    OxidationStatus.STREAM_CODEC,
                    CopperOxidationRecipe::getOxidationType,

                    Ingredient.CONTENTS_STREAM_CODEC.apply(
                            ByteBufCodecs::optional),
                    r -> Optional.ofNullable(r.getCatalyst()),

                    Ingredient.CONTENTS_STREAM_CODEC.apply(
                            ByteBufCodecs::optional),
                    r -> Optional.ofNullable(r.getInput()),

                    ItemStackTemplate.STREAM_CODEC.apply(
                            ByteBufCodecs::optional),
                    r -> Optional.ofNullable(r.getOutput()),

                    (type, catalyst, input, output) -> new CopperOxidationRecipe(
                            type,
                            catalyst.orElse(null),
                            input.orElse(null),
                            output.orElse(null)));

    @Override
    public RecipeSerializer<? extends Recipe<OxidationInput>> getSerializer() {
        return LibRecipeTypes.COPPER_OXIDATION.getSerializer();
    }

    @Override
    public RecipeType<? extends Recipe<OxidationInput>> getType() {
        return LibRecipeTypes.COPPER_OXIDATION.getType();
    }

    @Override
    public String group() {
        return "copper_oxidation";
    }
}