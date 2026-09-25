package com.devdyna.cakesticklib.setup.compat.jei.categories;

import java.util.*;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.compat.jei.*;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationRecipe;
import com.devdyna.cakesticklib.setup.registry.*;
import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

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

public class CopperOxidationCategory extends BaseCategory<RecipeHolder<CopperOxidationRecipe>>
                implements JeiModifierHandler {

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
        public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<CopperOxidationRecipe> recipe,
                        IFocusGroup focuses) {

                super.setRecipe(builder, recipe, focuses);

                var catalyst = recipe.value().getCatalyst();

                if (catalyst != null)
                        builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 29, 2)
                                        .add(catalyst);

                var input = builder.addSlot(RecipeIngredientRole.INPUT, 2, 2);
                var output = builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 2);

                switch (recipe.value().getOxidationType()) {

                        case SCRAPPING -> {
                                input.add(mapBlocks(oxidable, DataMapHooks::getNextOxidizedStage));

                                output.add(x.itemIngredient(oxidable.stream().map(Block::asItem).toList()));

                        }

                        case OXIDIZING -> {
                                input.add(x.itemIngredient(oxidable.stream().map(Block::asItem).toList()));

                                output.add(mapBlocks(oxidable, DataMapHooks::getNextOxidizedStage));
                        }

                        case WAXING -> {
                                input.add(x.itemIngredient(waxable.stream().map(Block::asItem).toList()));

                                output.add(mapBlocks(waxable, DataMapHooks::getBlockWaxed));
                        }

                        case UNWAXING -> {
                                input.add(mapBlocks(waxable, DataMapHooks::getBlockWaxed));

                                output.add(x.itemIngredient(waxable.stream().map(Block::asItem).toList()));
                        }

                        case CUSTOM -> {
                                if (recipe.value().getInput() != null)
                                        input.add(recipe.value().getInput());

                                if (recipe.value().getOutput() != null)
                                        output.add(recipe.value().getOutput());
                        }
                }

                var modifier = getRecipeMatch(recipe);

                if (modifier != null) {

                        var slot = builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 21).add(modifier.getOutput());

                        if (modifier.getTooltip() != null)
                                slot.addRichTooltipCallback(
                                                (v, t) -> t.add(Component.translatable(modifier.getTooltip())));

                }

        }

        private Ingredient mapBlocks(List<Block> blocks, Function<Block, Block> f) {
                return x.itemIngredient(blocks.stream().map(f).map(Block::asItem).toArray(ItemLike[]::new));
        }

        @Override
        public void draw(RecipeHolder<CopperOxidationRecipe> recipe, IRecipeSlotsView recipeSlotsView,
                        GuiGraphicsExtractor guiGraphics,
                        double mouseX, double mouseY) {
                super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

                if (getRecipeMatch(recipe) != null) {
                        ImageJei.of()
                                        .rl(x.mcLoc("textures/gui/sprites/container/slot.png"))
                                        .size(18, 18)
                                        .offset(58, 20)
                                        .render(helper, guiGraphics);

                }

        }

        @Override
        public void background(GuiGraphicsExtractor graphics) {
                this.backgroundImage
                                .size(77, 20)
                                .render(helper, graphics);
        }

}