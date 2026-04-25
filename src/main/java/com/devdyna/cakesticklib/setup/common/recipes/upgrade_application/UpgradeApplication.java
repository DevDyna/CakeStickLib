package com.devdyna.cakesticklib.setup.common.recipes.upgrade_application;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.setup.registry.zLibrary.zComponents;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

public class UpgradeApplication extends ShapedRecipe {

    private ShapedRecipePattern pattern;
    private ItemStackTemplate result;
    private UpgradeComponents builder;

    public UpgradeApplication(ShapedRecipePattern pattern,
            ItemStackTemplate result, UpgradeComponents builder) {
        super(RecipeBuilder.createCraftingCommonInfo(true),
                RecipeBuilder.createCraftingBookInfo(RecipeCategory.MISC, "upgrade_application"), pattern, result);
        this.pattern = pattern;
        this.result = result;
        this.builder = builder;

    }

    public ItemStackTemplate getResult() {
        return result;
    }

    public UpgradeComponents getBuilder() {
        return builder;
    }

    public ShapedRecipePattern getPattern() {
        return this.pattern;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        var item = result.create();
        item.set(zComponents.UPGRADE_COMPONENTS, builder);
        return item;
    }

    public static final RecipeSerializer<UpgradeApplication> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<UpgradeApplication> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ShapedRecipePattern.MAP_CODEC.fieldOf("pattern").forGetter(UpgradeApplication::getPattern),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(UpgradeApplication::getResult),
            UpgradeComponents.CODEC.fieldOf("upgrades").forGetter(UpgradeApplication::getBuilder))
            .apply(inst, UpgradeApplication::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, UpgradeApplication> STREAM_CODEC = StreamCodec.composite(
            ShapedRecipePattern.STREAM_CODEC, UpgradeApplication::getPattern,
            ItemStackTemplate.STREAM_CODEC, UpgradeApplication::getResult,
            UpgradeComponents.STREAM_CODEC, UpgradeApplication::getBuilder,
            UpgradeApplication::new);

}
