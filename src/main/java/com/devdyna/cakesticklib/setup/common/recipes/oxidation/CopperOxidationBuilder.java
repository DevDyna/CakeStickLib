package com.devdyna.cakesticklib.setup.common.recipes.oxidation;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.BaseRecipeBuilder;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class CopperOxidationBuilder extends BaseRecipeBuilder {

    private OxidationStatus type;
    private Ingredient catalyst;

    public CopperOxidationBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CopperOxidationBuilder of() {
        return new CopperOxidationBuilder();

    }

    public CopperOxidationBuilder unlockedBy() {
        return unlockedBy(MODULE_ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Items.COPPER_INGOT));
    }

    public CopperOxidationBuilder unlockedBy(String name, Criterion<?> criterion) {
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

    public CopperOxidationBuilder catalyst(TagKey<Item> catalyst, HolderLookup.Provider p) {
        return catalyst(x.itemIngredient(p.getOrThrow(catalyst)));
    }

    public CopperOxidationBuilder catalyst(Item catalyst) {
        return catalyst(x.itemIngredient(catalyst));
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "copper_oxidation/" + type.name().toLowerCase() + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CopperOxidationRecipe(type, catalyst);
    }

}