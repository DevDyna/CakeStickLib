package com.devdyna.cakesticklib.setup.common.recipes.oxidation;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.BaseRecipeBuilder;
import com.devdyna.cakesticklib.api.recipe.recipeBuilder.ItemAttach;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;

public class CopperOxidationBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<CopperOxidationBuilder> {

    private OxidationStatus type;
    private Ingredient catalyst;
    private Ingredient input;
    private ItemStackTemplate output;

    public CopperOxidationBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CopperOxidationBuilder of(HolderLookup.Provider p) {
        return new CopperOxidationBuilder(p);
    }

    public CopperOxidationBuilder unlockedBy() {
        return unlockedBy(
                x.name(Items.COPPER_INGOT),
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT));
    }

    public CopperOxidationBuilder unlockedBy(
            String name,
            Criterion<?> criterion) {

        this.criteria.put(name, criterion);
        return this;
    }

    public CopperOxidationBuilder type(OxidationStatus type) {
        this.type = type;
        return this;
    }

    private CopperOxidationBuilder catalyst(Ingredient catalyst) {
        this.catalyst = catalyst;
        return this;
    }

    public CopperOxidationBuilder catalyst(TagKey<Item> catalyst) {
        return catalyst(x.itemIngredient(catalyst, getProvider()));
    }

    public CopperOxidationBuilder catalyst(Item catalyst) {
        return catalyst(x.itemIngredient(catalyst));
    }

    private CopperOxidationBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public CopperOxidationBuilder input(TagKey<Item> input) {
        return input(x.itemIngredient(input, getProvider()));
    }

    public CopperOxidationBuilder input(Item input) {
        return input(x.itemIngredient(input));
    }

    public CopperOxidationBuilder input(ItemLike input) {
        return input(x.itemIngredient(input));
    }

    public CopperOxidationBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "copper_oxidation/" + type.name().toLowerCase()
                + (type.equals(OxidationStatus.CUSTOM) ? "/" + x.name(output) : "") + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CopperOxidationRecipe(type, catalyst, input, output);
    }

    @Override
    public CopperOxidationBuilder getBuilder() {
        return this;
    }

}