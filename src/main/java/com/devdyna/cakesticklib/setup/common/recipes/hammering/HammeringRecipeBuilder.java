package com.devdyna.cakesticklib.setup.common.recipes.hammering;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.zLibrary.zItems;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class HammeringRecipeBuilder extends BaseRecipeBuilder implements
        ItemAttach.Output.SimpleOutputItem<HammeringRecipeBuilder>,
        ItemAttach.Input.ListedNoItemCount<HammeringRecipeBuilder> {

    private List<Ingredient> items = new ArrayList<>();
    private ItemStackTemplate output;
    private InputToolDurability type;

    public HammeringRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static HammeringRecipeBuilder of() {
        return new HammeringRecipeBuilder();

    }

    public HammeringRecipeBuilder unlockedBy() {
        return unlockedBy(MODULE_ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(zItems.HAMMER.get()));
    }

    public HammeringRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public HammeringRecipeBuilder tool(InputToolDurability type) {
        this.type = type;
        return this;
    }

    public HammeringRecipeBuilder tool(Ingredient tool) {
        return tool(InputToolDurability.of(tool, 1));
    }

    public HammeringRecipeBuilder tool(Item tool) {
        return tool(InputToolDurability.of(x.itemIngredient(tool), 1));
    }

    public HammeringRecipeBuilder tool(Ingredient tool, int d) {
        return tool(InputToolDurability.of(tool, d));
    }

    public HammeringRecipeBuilder tool(Item tool, int d) {
        return tool(InputToolDurability.of(x.itemIngredient(tool), d));
    }

    @Override
    public HammeringRecipeBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    @Override
    public HammeringRecipeBuilder add(Ingredient input) {
        this.items.add(input);
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "tool_use/" + x.path(output.item().value()).toLowerCase() + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new HammeringRecipe(type, items, output);
    }

    @Override
    public HammeringRecipeBuilder getBuilder() {
        return this;
    }

}