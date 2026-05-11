package com.devdyna.cakesticklib.api.datagen;

import com.devdyna.cakesticklib.api.utils.x;

import io.netty.util.internal.UnstableApi;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public interface RecipeGenerators {

        abstract String getModName();

        abstract HolderLookup.Provider getProvider();

        abstract HolderGetter<Item> getItems();

        private Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike item) {
                return InventoryChangeTrigger.TriggerInstance.hasItems(item);
        }

        private Criterion<InventoryChangeTrigger.TriggerInstance> has(TagKey<Item> tag) {
                return InventoryChangeTrigger.TriggerInstance
                                .hasItems(ItemPredicate.Builder.item().of(getItems(), tag));
        }

        private String getConversionRecipeName(ItemLike product, ItemLike material) {
                return x.name(product) + "_from_" + x.name(material);
        }

        default String getConversionRecipeName(ItemLike output, TagKey<Item> input) {
                return x.name(output) + "_from_" + x.name(input);
        }

        private String getHasName(ItemLike i) {
                return "has_" + x.name(i);
        }

        default String getHasName(TagKey<Item> tag) {
                return "has_" + x.name(tag);
        }

        default String asRecipeID(Item i, String suffix) {
                return getModName() + ":" + x.name(i) + suffix;
        }

        default String asRecipeID(Item i) {
                return asRecipeID(i, "_alt");
        }

        // recipes

        default void simpleCooking(RecipeOutput c, Item input, Item output) {
                SimpleCookingRecipeBuilder
                                .smelting(x.itemIngredient(input),
                                                RecipeCategory.MISC,
                                                CookingBookCategory.MISC, output, 0.1F, 200)
                                .unlockedBy(getHasName(input),
                                                has(input))
                                .save(c, getModName() + ":"
                                                + getConversionRecipeName(
                                                                output,
                                                                input));
        }

        default void simpleCooking(RecipeOutput c, TagKey<Item> input, Item output) {
                SimpleCookingRecipeBuilder
                                .smelting(x.itemIngredient(input, getProvider()),
                                                RecipeCategory.MISC,
                                                CookingBookCategory.MISC, output, 0.1F, 200)
                                .unlockedBy(getHasName(input),
                                                has(input))
                                .save(c, getModName() + ":"
                                                + getConversionRecipeName(
                                                                output,
                                                                input));
        }

        default void doubleSmelt(RecipeOutput c, ItemLike input, ItemLike output) {
                SimpleCookingRecipeBuilder
                                .blasting(x.itemIngredient(input), RecipeCategory.MISC,
                                                CookingBookCategory.MISC,
                                                output.asItem(), 0.1F, 100)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, getModName() + ":" + x.name(output) + "_from_"
                                                + x.name(input)
                                                + "_blasting");
                SimpleCookingRecipeBuilder
                                .smelting(x.itemIngredient(input), RecipeCategory.MISC,
                                                CookingBookCategory.MISC,
                                                output.asItem(), 0.1F, 200)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, getModName() + ":" + x.name(output) + "_from_"
                                                + x.name(input)
                                                + "_smelting");
        }

        default void doubleSmelt(RecipeOutput c, TagKey<Item> input, ItemLike output) {
                SimpleCookingRecipeBuilder
                                .blasting(x.itemIngredient(input, getProvider()), RecipeCategory.MISC,
                                                CookingBookCategory.MISC,
                                                output.asItem(), 0.1F, 100)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, getModName() + ":" + x.name(output) + "_from_"
                                                + x.name(input)
                                                + "_blasting");
                SimpleCookingRecipeBuilder
                                .smelting(x.itemIngredient(input, getProvider()), RecipeCategory.MISC,
                                                CookingBookCategory.MISC,
                                                output.asItem(), 0.1F, 200)
                                .unlockedBy(getHasName(input), has(output))
                                .save(c, getModName() + ":" + x.name(output) + "_from_"
                                                + x.name(input)
                                                + "_smelting");
        }

        default void unpacker(RecipeOutput c, ItemLike input, ItemLike output, int count) {
                ShapelessRecipeBuilder.shapeless(getItems(), RecipeCategory.MISC, output, count).requires(input)
                                .unlockedBy(getHasName(input), has(input))
                                .save(c, getConversionRecipeName(output, input));
        }

        default void nuggetIngotBlock(RecipeOutput c, ItemLike nugget, ItemLike ingot, ItemLike block) {
                packUnpack(c, nugget, ingot, false);
                packUnpack(c, ingot, block, false);
        }

        /**
         * BREAKING CHANGES :<br/>
         * <br/>
         * {@code input} and {@code gear} has been switched of position
         */
        @UnstableApi
        default void gear(RecipeOutput c, Item input, Item gear) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, gear)
                                .pattern(" # ")
                                .pattern("# #")
                                .pattern(" # ")
                                .define('#', input)
                                .unlockedBy(getHasName(input), has(input))
                                .save(c);

        }

        @Deprecated
        default void gear(RecipeOutput c, Item gear, TagKey<Item> input) {
        }

        default void gear(RecipeOutput c, TagKey<Item> input, Item gear) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, gear)
                                .pattern(" # ")
                                .pattern("# #")
                                .pattern(" # ")
                                .define('#', input)
                                .unlockedBy(getHasName(input), has(input))
                                .save(c);

        }

        default void twoByTwoPacker(RecipeOutput c, ItemLike output, TagKey<Item> tag) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output)
                                .define('#', tag)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(tag), has(tag))
                                .save(c);
        }

        default void twoByTwoPacker(RecipeOutput c, ItemLike i, ItemLike o, String e) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, i, 1).define('#', o).pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(o), has(o)).save(c, e);
        }

        default void plate(RecipeOutput c, TagKey<Item> input, Item output) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output, 3)
                                .pattern("III")
                                .define('I', input)
                                .unlockedBy(getHasName(input),
                                                has(input))
                                .save(c);

        }

        default void foil(RecipeOutput c, TagKey<Item> input, Item output) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output, 3)
                                .pattern(" IS")
                                .pattern(" I ")
                                .pattern("SI ")
                                .define('I', input)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(input),
                                                has(Items.STICK))
                                .save(c);

        }

        default void coil(RecipeOutput c, TagKey<Item> input, Item output) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output, 4)
                                .pattern(" I ")
                                .pattern("ISI")
                                .pattern(" I ")
                                .define('I', input)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(input),
                                                has(Items.STICK))
                                .save(c);

        }

        default void plate(RecipeOutput c, Item input, Item output) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output, 3)
                                .pattern("III")
                                .define('I', input)
                                .unlockedBy(getHasName(input),
                                                has(input))
                                .save(c);

        }

        default void foil(RecipeOutput c, Item input, Item output) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output, 3)
                                .pattern(" IS")
                                .pattern(" I ")
                                .pattern("SI ")
                                .define('I', input)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(input),
                                                has(Items.STICK))
                                .save(c);

        }

        default void coil(RecipeOutput c, Item input, Item output) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output, 4)
                                .pattern(" I ")
                                .pattern("ISI")
                                .pattern(" I ")
                                .define('I', input)
                                .define('S', Items.STICK)
                                .unlockedBy(getHasName(input),
                                                has(Items.STICK))
                                .save(c);

        }

        default void packUnpack(RecipeOutput c, ItemLike unpacked, ItemLike packed, boolean isSmall) {
                ShapelessRecipeBuilder.shapeless(getItems(), RecipeCategory.MISC, unpacked, isSmall ? 4 : 9)
                                .requires(packed)

                                .unlockedBy(getHasName(packed), has(packed))
                                .save(c, getModName() + ":" + x.name(unpacked) + "_unpack"
                                                + (isSmall ? "_4" : "_9"));

                simplePacked(c, unpacked.asItem(), packed.asItem(), isSmall);
        }

        @Deprecated
        default void simplePacked(RecipeOutput c, Item input, Item output) {
                simplePacked(c, input, output, true);
        }

        default void simplePacked(RecipeOutput c, Item input, Item output, boolean isSmall) {
                var temp = ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output)
                                .define('#', input)
                                .pattern("##" + (!isSmall ? "#" : "")).pattern("##" + (!isSmall ? "#" : ""));

                if (!isSmall)
                        temp = temp.pattern("###");

                temp.unlockedBy(getHasName(input), has(input))
                                .save(c, getModName() + ":" + getConversionRecipeName(output,
                                                input));

        }

        default void slab(ItemLike slab, ItemLike material, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, slab, 6).define('#', material)
                                .pattern("###")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        default void stair(ItemLike stair, ItemLike material, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, stair, 4).define('#', material)
                                .pattern("#  ")
                                .pattern("## ")
                                .pattern("###")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, int resultCount) {
                stonecutter(c, result, material, resultCount,
                                getModName() + ":" + getConversionRecipeName(result, material)
                                                + "_stonecutting");
        }

        default void stonecutter(RecipeOutput c, ItemLike result, ItemLike material) {
                stonecutter(c, result, material, 1,
                                getModName() + ":" + getConversionRecipeName(result, material)
                                                + "_stonecutting");
        }

        default void slab(ItemLike slab, ItemLike material, RecipeOutput c, String extra) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, slab, 6).define('#', material)
                                .pattern("###")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        default void stair(ItemLike stair, ItemLike material, RecipeOutput c, String extra) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, stair, 4).define('#', material)
                                .pattern("  #")
                                .pattern(" ##")
                                .pattern("###")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, int resultCount,
                        String extra) {
                SingleItemRecipeBuilder
                                .stonecutting(Ingredient.of(material), RecipeCategory.BUILDING_BLOCKS, result,
                                                resultCount)
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material, int resultCount,
                        String extra) {
                SingleItemRecipeBuilder
                                .stonecutting(x.itemIngredient(material, getProvider()), RecipeCategory.BUILDING_BLOCKS,
                                                result,
                                                resultCount)
                                .unlockedBy(x.name(material) + "_from_" + x.name(result), has(material))
                                .save(c, extra);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material,
                        String extra) {
                stonecutter(c, result, material, 1, extra);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material) {
                stonecutter(c, result, material, 1,
                                getModName() + ":" + x.name(result) + "_from_stonecutting");
        }

        default void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, String extra) {
                stonecutter(c, result, material, 1, extra);
        }

        default void pillar(RecipeOutput c, ItemLike result, ItemLike material) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 2)
                                .define('#', material)
                                .pattern("#")
                                .pattern("#")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        default void pillar(RecipeOutput c, ItemLike result, ItemLike material,
                        String extra) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 2)
                                .define('#', material)
                                .pattern("#")
                                .pattern("#")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        default void tiles(RecipeOutput c, ItemLike result, ItemLike material,
                        String extra) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c, extra);
        }

        default void tiles(RecipeOutput c, ItemLike result, ItemLike material) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        default void cross(RecipeOutput c, ItemLike result, ItemLike material, ItemLike middle) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .define('A', middle)
                                .pattern(" # ")
                                .pattern("#A#")
                                .pattern(" # ")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

        default void cross(RecipeOutput c, ItemLike result, ItemLike material, TagKey<Item> middle) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .define('A', middle)
                                .pattern(" # ")
                                .pattern("#A#")
                                .pattern(" # ")
                                .unlockedBy(getHasName(material), has(material))
                                .save(c);
        }

}
