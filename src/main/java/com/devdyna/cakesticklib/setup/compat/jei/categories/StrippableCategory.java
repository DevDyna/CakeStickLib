package com.devdyna.cakesticklib.setup.compat.jei.categories;

import java.util.*;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.compat.jei.BaseCategory;
import com.devdyna.cakesticklib.api.compat.jei.ImageJei;
import com.devdyna.cakesticklib.api.compat.jei.JeiModifierHandler;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.compat.jei.recipes.StrippableRecipe;
import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

public class StrippableCategory extends BaseCategory<StrippableRecipe> implements JeiModifierHandler {

    public static final List<StrippableRecipe> getRecipes() {
        return x.getBlocks(NeoForgeDataMaps.STRIPPABLES)
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Reference::value)
                .map(i -> new StrippableRecipe(i,
                        i.builtInRegistryHolder().getData(NeoForgeDataMaps.STRIPPABLES).strippedBlock()))
                .toList();
    }

    public StrippableCategory(IGuiHelper h) {
        super(h);
    }

    public static final IRecipeType<StrippableRecipe> TYPE = IRecipeType
            .create(x.rl(MODULE_ID, "strippable"), StrippableRecipe.class);

    @Override
    public IRecipeType<StrippableRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.strippable";
    }

    @Override
    public ItemLike getIconItem() {
        return Items.IRON_AXE;
    }

    @Override
    public Size setXY() {
        return Size.of(77, 39);
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/catalyst.png");
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, StrippableRecipe recipe, IFocusGroup focuses) {

        super.setRecipe(builder, recipe, focuses);

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
                .add(recipe.unstripped());

        var axes = BuiltInRegistries.ITEM.get(ItemTags.AXES);

        if (axes.isPresent())
            builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 29, 2)
                    .add(x.itemIngredient(axes.get()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 2)
                .add(recipe.stripped());

        var modifier = getRecipeMatch(getIdentifier(recipe));
        if (modifier != null) {
            var slot = builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 21)
                    .add(modifier.getOutput());

            if (modifier.getTooltip() != null)
                slot.addRichTooltipCallback((v, t) -> t.add(Component.translatable(modifier.getTooltip())));

        }

    }

    @Override
    public void background(GuiGraphicsExtractor graphics) {
        backgroundImage.size(77, 20).render(helper, graphics);
    }

    @Override
    public void draw(StrippableRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics,
            double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        if (getRecipeMatch(getIdentifier(recipe)) != null)
            ImageJei.of()
                    .rl(x.mcLoc("textures/gui/sprites/container/slot.png"))
                    .size(18, 18)
                    .offset(58, 20)
                    .render(helper, guiGraphics);

    }

    @Override
    public @Nullable Identifier getIdentifier(StrippableRecipe recipe) {
        return NeoForgeDataMaps.STRIPPABLES.id().withSuffix("/" + x.name(recipe.unstripped()));
    }

}