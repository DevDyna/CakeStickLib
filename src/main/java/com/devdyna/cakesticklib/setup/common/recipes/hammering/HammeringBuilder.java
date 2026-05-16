package com.devdyna.cakesticklib.setup.common.recipes.hammering;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.*;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class HammeringBuilder extends BaseRecipeBuilder implements
        ItemAttach.Output.SimpleOutputItem<HammeringBuilder>,
        ItemAttach.Input.ListedNoItemCount<HammeringBuilder> {

    private List<Ingredient> items = new ArrayList<>();
    private ItemStackTemplate output;
    private InputToolDurability type;

    public HammeringBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static HammeringBuilder of(HolderLookup.Provider p) {
        return new HammeringBuilder(p);

    }

    public HammeringBuilder unlockedBy() {
        return unlockedBy(x.name(LibItems.HAMMER.get()), InventoryChangeTrigger.TriggerInstance
                .hasItems(LibItems.HAMMER.get()));
    }

    public HammeringBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public HammeringBuilder tool(InputToolDurability type) {
        this.type = type;
        return this;
    }

    public HammeringBuilder tool(Ingredient tool) {
        return tool(InputToolDurability.of(tool, 1));
    }

    public HammeringBuilder tool(Item tool) {
        return tool(InputToolDurability.of(x.itemIngredient(tool), 1));
    }

    public HammeringBuilder tool(Ingredient tool, int d) {
        return tool(InputToolDurability.of(tool, d));
    }

    public HammeringBuilder tool(Item tool, int d) {
        return tool(InputToolDurability.of(x.itemIngredient(tool), d));
    }

    @Override
    public HammeringBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    @Override
    public HammeringBuilder add(Ingredient input) {
        this.items.add(input);
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "tool_use/" + x.name(output).toLowerCase() + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new HammeringRecipe(type, items, output);
    }

    @Override
    public HammeringBuilder getBuilder() {
        return this;
    }

}