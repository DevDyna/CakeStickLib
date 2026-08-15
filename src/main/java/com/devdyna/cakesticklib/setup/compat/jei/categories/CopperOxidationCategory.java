package com.devdyna.cakesticklib.setup.compat.jei.categories;

import java.util.*;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.compat.jei.ImageJei;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationRecipe;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.OxidationStatus;
import com.devdyna.cakesticklib.setup.registry.*;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.awt.Color;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.Holder.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DataMapHooks;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

public class CopperOxidationCategory extends BaseRecipeCategory<CopperOxidationRecipe> {

    private List<Block> oxidable = x.getBlocks(NeoForgeDataMaps.OXIDIZABLES)
            .stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(Reference::value).toList();

    private List<Block> waxable = x.getBlocks(NeoForgeDataMaps.WAXABLES)
            .stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(Reference::value).toList();

    public CopperOxidationCategory(IGuiHelper h) {
        super(h);
    }

    public static final IRecipeType<RecipeHolder<CopperOxidationRecipe>> TYPE = IRecipeType
            .create(LibRecipeTypes.COPPER_OXIDATION.getType());

    @Override
    public IRecipeType<RecipeHolder<CopperOxidationRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.copper_oxidation";
    }

    @Override
    public ItemLike getIconItem() {
        return LibItems.REDSTONE_ACID.get();
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
    public void setRecipe(IRecipeLayoutBuilder builder, CopperOxidationRecipe recipe, IFocusGroup focuses) {

        super.setRecipe(builder, recipe, focuses);

        Ingredient catalyst = recipe.getCatalyst();

        if (catalyst != null)
            builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 29, 2)
                    .add(catalyst);

        switch (recipe.getOxidationType()) {

            case SCRAPPING -> {
                builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
                        .add(mapBlocks(
                                oxidable,
                                DataMapHooks::getNextOxidizedStage));

                builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 2)
                        .add(x.itemIngredient(
                                oxidable.stream()
                                        .map(Block::asItem)
                                        .toList()));

                // TODO API : add toggleable
                builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 21)
                        .add(x.item(LibItems.PATINA.get()))
                        .addRichTooltipCallback(
                                (v, t) -> t.add(
                                        Component.translatable(
                                                MODULE_ID + ".jei.patina_drop",
                                                "0-2")));
            }

            case OXIDIZING -> {
                builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
                        .add(x.itemIngredient(
                                oxidable.stream()
                                        .map(Block::asItem)
                                        .toList()));

                builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 2)
                        .add(mapBlocks(
                                oxidable,
                                DataMapHooks::getNextOxidizedStage));
            }

            case WAXING -> {
                builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
                        .add(x.itemIngredient(
                                waxable.stream()
                                        .map(Block::asItem)
                                        .toList()));

                builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 2)
                        .add(mapBlocks(
                                waxable,
                                DataMapHooks::getBlockWaxed));
            }

            case UNWAXING -> {
                builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
                        .add(mapBlocks(
                                waxable,
                                DataMapHooks::getBlockWaxed));

                builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 2)
                        .add(x.itemIngredient(
                                waxable.stream()
                                        .map(Block::asItem)
                                        .toList()));
            }

            case CUSTOM -> {
                if (recipe.getInput() != null)
                    builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
                            .add(recipe.getInput());

                if (recipe.getOutput() != null)
                    builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 2)
                            .add(recipe.getOutput());

            }
        }
    }

    private Ingredient mapBlocks(List<Block> blocks, Function<Block, Block> f) {
        return x.itemIngredient(blocks.stream().map(f).map(Block::asItem).toArray(ItemLike[]::new));
    }

    @Override
    public void draw(CopperOxidationRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics,
            double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        if (recipe.getOxidationType().equals(OxidationStatus.SCRAPPING)) {
            ImageJei.of()
                    .rl(x.mcLoc("textures/gui/sprites/container/slot.png"))
                    .size(18, 18)
                    .offset(58, 20)
                    .render(helper, guiGraphics);

            drawCentredStringFixed(guiGraphics, font, Component.literal("0-2"), 67, 30, Color.WHITE.getRGB(), true);
        }

    }

    @Override
    public void background(GuiGraphicsExtractor graphics) {
        this.backgroundImage
                .size(77, 20)
                .render(helper, graphics);
    }

}