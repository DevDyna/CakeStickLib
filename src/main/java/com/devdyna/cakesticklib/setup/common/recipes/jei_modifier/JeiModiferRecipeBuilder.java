package com.devdyna.cakesticklib.setup.common.recipes.jei_modifier;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.BaseRecipeBuilder;
import com.devdyna.cakesticklib.api.recipe.recipeBuilder.ItemAttach;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.OxidationStatus;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

public class JeiModiferRecipeBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<JeiModiferRecipeBuilder> {

    private Identifier target;
    private ItemStackTemplate output;
    private String tooltip = null;

    public JeiModiferRecipeBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static JeiModiferRecipeBuilder of(HolderLookup.Provider p) {
        return new JeiModiferRecipeBuilder(p);
    }

    public JeiModiferRecipeBuilder unlockedBy() {
        return unlockedBy(output.item().value());
    }

    public JeiModiferRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public JeiModiferRecipeBuilder target(Identifier target) {
        this.target = target;
        return this;
    }

    public JeiModiferRecipeBuilder target(String mod, String path) {
        return target(x.rl(mod, path));
    }

    public JeiModiferRecipeBuilder oxidation(OxidationStatus status) {
        if (status.isCustom())
            throw new NullPointerException("Use oxidation(ItemLike item) if you want use OxidstionStatus.CUSTOM!");
        return target(status.getIdentifier());
    }

    public JeiModiferRecipeBuilder strippable(ItemLike target) {
        return target(NeoForgeDataMaps.STRIPPABLES.id().withSuffix("/" + x.name(target)));
    }

    public JeiModiferRecipeBuilder oxidation(ItemLike target) {
        return target(OxidationStatus.CUSTOM.getIdentifier().withSuffix(x.name(target)));
    }

    public JeiModiferRecipeBuilder tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public JeiModiferRecipeBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "jei_modifier/" + x.name(output) + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new JeiModiferRecipe(target, output, tooltip);
    }

    @Override
    public JeiModiferRecipeBuilder getBuilder() {
        return this;
    }

}