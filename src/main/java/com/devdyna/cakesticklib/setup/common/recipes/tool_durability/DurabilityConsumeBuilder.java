package com.devdyna.cakesticklib.setup.common.recipes.tool_durability;

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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class DurabilityConsumeBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<DurabilityConsumeBuilder>,
        ItemAttach.Input.ListedNoItemCount<DurabilityConsumeBuilder> {

    private List<Ingredient> items = new ArrayList<>();
    private ItemStackTemplate output;
    private InputToolDurability type;

    public DurabilityConsumeBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static DurabilityConsumeBuilder of(HolderLookup.Provider p) {
        return new DurabilityConsumeBuilder(p);
    }

    public DurabilityConsumeBuilder unlockedBy() {
        return unlockedBy(x.name(LibItems.HAMMER.get()),
                InventoryChangeTrigger.TriggerInstance
                        .hasItems(LibItems.HAMMER.get()));
    }

    public DurabilityConsumeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public DurabilityConsumeBuilder tool(InputToolDurability type) {
        this.type = type;
        return this;
    }

    public DurabilityConsumeBuilder tool(Ingredient tool) {
        return tool(InputToolDurability.of(tool, 1));
    }

    public DurabilityConsumeBuilder tool(TagKey<Item> tool) {
        return tool(x.itemIngredient(tool, getProvider()));
    }

    public DurabilityConsumeBuilder tool(Item tool) {
        return tool(InputToolDurability.of(x.itemIngredient(tool), 1));
    }

    public DurabilityConsumeBuilder tool(Ingredient tool, int d) {
        return tool(InputToolDurability.of(tool, d));
    }

    public DurabilityConsumeBuilder tool(TagKey<Item> tool, int d) {
        return tool(x.itemIngredient(tool, getProvider()), d);
    }

    public DurabilityConsumeBuilder tool(Item tool, int d) {
        return tool(InputToolDurability.of(x.itemIngredient(tool), d));
    }

    @Override
    public DurabilityConsumeBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    @Override
    public DurabilityConsumeBuilder add(Ingredient input) {
        this.items.add(input);
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "durability_use/" + x.name(output).toLowerCase() + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new DurabilityConsumeRecipe(type, items, output);
    }

    @Override
    public DurabilityConsumeBuilder getBuilder() {
        return this;
    }

}