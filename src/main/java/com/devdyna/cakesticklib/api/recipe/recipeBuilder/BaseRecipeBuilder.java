package com.devdyna.cakesticklib.api.recipe.recipeBuilder;

import java.util.Map;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;

public abstract class BaseRecipeBuilder {

    protected Map<String, Criterion<?>> criteria;

    public abstract Recipe<?> createRecipe();

    public abstract Identifier getSuffix(String extra);

    public void save(RecipeOutput recipeOutput) {
        save(recipeOutput, "");
    }

    public void save(RecipeOutput o, String extra) {
        this.save(o, ResourceKey.create(Registries.RECIPE, getSuffix(extra)));
    }

    public void save(RecipeOutput c, ResourceKey<Recipe<?>> pId) {
        if (this.criteria.isEmpty())
            throw new IllegalStateException("Recipe unobtainable : " + String.valueOf(pId));
        Advancement.Builder adv = c.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(adv::addCriterion);
        c.accept(pId, createRecipe(),
                adv.build(pId.identifier().withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }

}
