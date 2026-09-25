package com.devdyna.cakesticklib.setup.common.recipes.jei_modifier;

import java.util.Optional;

import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.setup.registry.LibItems;
import com.devdyna.cakesticklib.setup.registry.LibRecipeTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class JeiModiferRecipe extends BaseRecipeType<RecipeInput> {

    private final Identifier target;
    private final ItemStackTemplate output;
    private final String tooltip;

    public JeiModiferRecipe(Identifier target, ItemStackTemplate output, String tooltip) {
        super();
        this.target = target;
        this.output = output;
        this.tooltip = tooltip;
    }

    public Identifier getTarget() {
        return target;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    public String getTooltip() {
        return tooltip;
    }

    @Override
    public boolean matches(RecipeInput r, Level l) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeInput input) {
        return output == null
                ? ItemStack.EMPTY
                : output.create().copy();
    }

    @Override
    public Item getToastIcon() {
        return LibItems.PATINA.get();
    }

    public static final RecipeSerializer<JeiModiferRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<JeiModiferRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(

            Identifier.CODEC
                    .fieldOf("target")
                    .forGetter(JeiModiferRecipe::getTarget),

            ItemStackTemplate.CODEC
                    .fieldOf("output")
                    .forGetter(JeiModiferRecipe::getOutput),
            Codec.STRING
                    .optionalFieldOf("rich_tooltip_key")
                    .forGetter(r -> Optional.ofNullable(r.getTooltip()))

    ).apply(inst, (t, o, r) -> new JeiModiferRecipe(t, o, r.orElse(null))));

    public static final StreamCodec<RegistryFriendlyByteBuf, JeiModiferRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    Identifier.STREAM_CODEC,
                    JeiModiferRecipe::getTarget,

                    ItemStackTemplate.STREAM_CODEC,
                    JeiModiferRecipe::getOutput,

                    ByteBufCodecs.optional(ByteBufCodecs.STRING_UTF8),
                    r -> Optional.ofNullable(r.getTooltip()),

                    (t, o, r) -> new JeiModiferRecipe(t, o, r.orElse(null)));

    @Override
    public RecipeSerializer<? extends Recipe<RecipeInput>> getSerializer() {
        return LibRecipeTypes.JEI_MODIFIER.getSerializer();
    }

    @Override
    public RecipeType<? extends Recipe<RecipeInput>> getType() {
        return LibRecipeTypes.JEI_MODIFIER.getType();
    }

    @Override
    public String group() {
        return "jei_modifier";
    }
}