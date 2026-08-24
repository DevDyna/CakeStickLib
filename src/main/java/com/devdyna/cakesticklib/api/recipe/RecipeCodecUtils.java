package com.devdyna.cakesticklib.api.recipe;

import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

/**
 * Don't work as intended!
 * <br/>
 * <br/>
 * USE FOR CODECS
 * <br/>
 * <br/>
 * {@code Codec<T>.optionalFieldOf()} AND {@code Optional<T>.ofNullable()}
 * <br/>
 * <br/>
 * USE FOR STREAM CODECS
 * <br/>
 * <br/>
 * {@code StreamCodec<RegistryFriendlyByteBuf,Optional<T>>.apply(ByteBufCodecs::optional)}
 * AND {@code Optional.ofNullable()}
 */
@Deprecated
public class RecipeCodecUtils {

    public static FluidStackTemplate optionalCodec(FluidStackTemplate f) {
        return f.fluid() != null ? f : null;
    }

    public static ItemStackTemplate optionalCodec(ItemStackTemplate i) {
        return i.item() != null ? i : null;
    }

    public static Ingredient optionalCodec(Ingredient i) {
        return i.isEmpty() ? null : i;
    }

    public static FluidIngredient optionalCodec(FluidIngredient f) {
        return f.fluids().isEmpty() ? null : f;
    }

    public static SizedIngredient optionalCodec(SizedIngredient i) {
        return i.ingredient() == null || i.ingredient().isEmpty() ? null : i;
    }

    public static SizedFluidIngredient optionalCodec(SizedFluidIngredient f) {
        return f.ingredient() == null || f.ingredient().fluids().isEmpty() ? null : f;
    }

}
