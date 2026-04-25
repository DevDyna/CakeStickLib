package com.devdyna.cakesticklib.setup.common.recipes.upgrade_application;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.zLibrary.zComponents;
import com.devdyna.cakesticklib.setup.registry.zLibrary.zItems;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;

public class UpgradeApplicationBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<UpgradeApplicationBuilder> {

    private ItemStackTemplate result;
    private Map<Character, Ingredient> key = new HashMap<>();
    private List<String> row = new ArrayList<>();

    private int energy;
    private int speed;
    private int luck;
    private int fluid;

    public UpgradeApplicationBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static UpgradeApplicationBuilder of() {
        return new UpgradeApplicationBuilder();

    }

    public UpgradeApplicationBuilder unlockedBy() {
        return unlockedBy(MODULE_ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(zItems.STEEL_PLATE.get()));
    }

    public UpgradeApplicationBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public UpgradeApplicationBuilder output(ItemStackTemplate upgrade) {

        this.result = new ItemStackTemplate(upgrade.item(), 1,
                DataComponentPatch.builder()
                        .set(zComponents.UPGRADE_COMPONENTS.get(), UpgradeComponents
                                .builder(speed, energy, luck, fluid))
                        .build());
        return this;
    }

    public UpgradeApplicationBuilder energy(int energy) {
        this.energy = energy;
        return this;
    }

    public UpgradeApplicationBuilder speed(int speed) {
        this.speed = speed;
        return this;
    }

    public UpgradeApplicationBuilder luck(int luck) {
        this.luck = luck;
        return this;
    }

    public UpgradeApplicationBuilder fluid(int fluid) {
        this.fluid = fluid;
        return this;
    }

    public UpgradeApplicationBuilder define(Character symbol, Ingredient ingredient) {
        if (this.key.containsKey(symbol))
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");

        if (symbol == ' ')
            throw new IllegalArgumentException("Symbol ' ' cannot be defined as key");

        this.key.put(symbol, ingredient);
        return this;

    }

    public UpgradeApplicationBuilder define(Character symbol, TagKey<Item> tag, HolderLookup.Provider p) {
        return define(symbol, Ingredient.of(p.getOrThrow(tag)));
    }

    public UpgradeApplicationBuilder define(Character symbol, ItemLike item) {
        return define(symbol, Ingredient.of(item));
    }

    public UpgradeApplicationBuilder pattern(String row) {
        if (!this.row.isEmpty() && row.length() != ((String) this.row.get(0)).length())
            throw new IllegalArgumentException("Pattern must be the same width on every line!");

        this.row.add(row);
        return this;

    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "upgrade_application/" + x.path(result.item().value()).toLowerCase() + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new UpgradeApplication(ShapedRecipePattern.of(key, row), result,
                UpgradeComponents.builder(speed, energy, luck, fluid));
    }

    @Override
    public UpgradeApplicationBuilder getBuilder() {
        return this;
    }

}
