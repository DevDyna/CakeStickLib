package com.devdyna.cakesticklib.api.datagen;

import com.devdyna.cakesticklib.api.utils.x;

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

        private Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike item) {
                return InventoryChangeTrigger.TriggerInstance.hasItems(item);
        }

        private Criterion<InventoryChangeTrigger.TriggerInstance> has(TagKey<Item> tag) {
                return InventoryChangeTrigger.TriggerInstance
                                .hasItems(ItemPredicate.Builder.item().of(getItems(), tag));
        }

        abstract String getHasNameGen(ItemLike i);

        abstract String getItemNameGen(ItemLike i);

        abstract HolderLookup.Provider getProvider();

        abstract HolderGetter<Item> getItems();

        abstract String getConversionRecipeNameGen(ItemLike product, ItemLike material);

        default String asRecipeID(Item i, String suffix) {
                return getModName() + ":" + x.path(i) + suffix;
        }

        default String asRecipeID(Item i) {
                return asRecipeID(i, "_alt");
        }

        default String getHasName(TagKey<Item> tag) {
                return "has_" + x.getTagName(tag);
        }

        default String getConversionRecipeName(TagKey<Item> product, ItemLike material) {
                return x.getTagName(product) + "_from_" + getItemNameGen(material);
        }

        default void simpleCooking(RecipeOutput c, Item input, Item output) {
                SimpleCookingRecipeBuilder
                                .smelting(Ingredient.of(input),
                                                RecipeCategory.MISC,
                                                CookingBookCategory.MISC, output, 0.1F, 200)
                                .unlockedBy(getHasNameGen(input),
                                                has(input))
                                .save(c, getModName() + ":"
                                                + getConversionRecipeNameGen(
                                                                output,
                                                                input));
        }

        default void doubleSmelt(RecipeOutput c, ItemLike input, ItemLike output) {
                SimpleCookingRecipeBuilder
                                .blasting(x.itemIngredient(input), RecipeCategory.MISC,
                                                CookingBookCategory.MISC,
                                                output.asItem(), 0.1F, 100)
                                .unlockedBy(getHasNameGen(input), has(output))
                                .save(c, getModName() + ":" + x.path(output.asItem()) + "_from_"
                                                + x.path(input.asItem())
                                                + "_blasting");
                SimpleCookingRecipeBuilder
                                .smelting(x.itemIngredient(input), RecipeCategory.MISC,
                                                CookingBookCategory.MISC,
                                                output.asItem(), 0.1F, 200)
                                .unlockedBy(getHasNameGen(input), has(output))
                                .save(c, getModName() + ":" + x.path(output.asItem()) + "_from_"
                                                + x.path(input.asItem())
                                                + "_smelting");
        }

        default void unpacker(RecipeOutput c, ItemLike input, ItemLike output, int count) {
                ShapelessRecipeBuilder.shapeless(getItems(), RecipeCategory.MISC, output, count).requires(input)
                                .unlockedBy(getHasNameGen(input), has(input))
                                .save(c, getConversionRecipeNameGen(output, input));
        }

        default void nuggetIngotBlock(RecipeOutput c, ItemLike nugget, ItemLike ingot, ItemLike block) {
                packUnpack(c, nugget, ingot, false);
                packUnpack(c, ingot, block, false);
        }

        default void gear(RecipeOutput c,  Item gear, Item input) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, gear)
                                .pattern(" # ")
                                .pattern("# #")
                                .pattern(" # ")
                                .define('#', input)
                                .unlockedBy(getHasNameGen(input), has(input))
                                .save(c);

        }

        default void gear(RecipeOutput c, Item gear, TagKey<Item> input) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, gear)
                                .pattern(" # ")
                                .pattern("# #")
                                .pattern(" # ")
                                .define('#', input)
                                .unlockedBy(x.getTagName(input), has(input))
                                .save(c);

        }

        default void twoByTwoPacker(RecipeOutput c, ItemLike output, TagKey<Item> tag) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output)
                                .define('#', tag)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(x.getTagName(tag), has(tag))
                                .save(c);
        }

        default void twoByTwoPacker(RecipeOutput c, ItemLike i, ItemLike o, String e) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, i, 1).define('#', o).pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasNameGen(o), has(o)).save(c, e);
        }

        default void plate(RecipeOutput c,TagKey<Item> input, Item output) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output, 3)
                                .pattern("III")
                                .define('I', input)
                                .unlockedBy(x.getTagName(input),
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
                                .unlockedBy(x.getTagName(input),
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
                                .unlockedBy(x.getTagName(input),
                                                has(Items.STICK))
                                .save(c);

        }

        default void plate(RecipeOutput c,Item input, Item output) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.MISC, output, 3)
                                .pattern("III")
                                .define('I', input)
                                .unlockedBy(getHasNameGen(input),
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
                                .unlockedBy(getHasNameGen(input),
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
                                .unlockedBy(getHasNameGen(input),
                                                has(Items.STICK))
                                .save(c);

        }

        default void packUnpack(RecipeOutput c, ItemLike unpacked, ItemLike packed, boolean isSmall) {
                ShapelessRecipeBuilder.shapeless(getItems(), RecipeCategory.MISC, unpacked, isSmall ? 4 : 9)
                                .requires(packed)

                                .unlockedBy(getHasNameGen(packed), has(packed))
                                .save(c, getModName() + ":" + x.path((Item) unpacked) + "_unpack"
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

                temp.unlockedBy(getHasNameGen(input), has(input))
                                .save(c, getModName() + ":" + getConversionRecipeNameGen(output,
                                                input));

        }

        default void slab(ItemLike slab, ItemLike material, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, slab, 6).define('#', material)
                                .pattern("###")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c);
        }

        default void stair(ItemLike stair, ItemLike material, RecipeOutput c) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, stair, 4).define('#', material)
                                .pattern("#  ")
                                .pattern("## ")
                                .pattern("###")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, int resultCount) {
                stonecutter(c, result, material, resultCount,
                                getModName() + ":" + getConversionRecipeNameGen(result, material)
                                                + "_stonecutting");
        }

        default void stonecutter(RecipeOutput c, ItemLike result, ItemLike material) {
                stonecutter(c, result, material, 1,
                                getModName() + ":" + getConversionRecipeNameGen(result, material)
                                                + "_stonecutting");
        }

        default void slab(ItemLike slab, ItemLike material, RecipeOutput c, String extra) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, slab, 6).define('#', material)
                                .pattern("###")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c, extra);
        }

        default void stair(ItemLike stair, ItemLike material, RecipeOutput c, String extra) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, stair, 4).define('#', material)
                                .pattern("  #")
                                .pattern(" ##")
                                .pattern("###")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c, extra);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, int resultCount,
                        String extra) {
                SingleItemRecipeBuilder
                                .stonecutting(Ingredient.of(material), RecipeCategory.BUILDING_BLOCKS, result,
                                                resultCount)
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c, extra);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material, int resultCount,
                        String extra) {
                SingleItemRecipeBuilder
                                .stonecutting(x.itemIngredient(material, getProvider()), RecipeCategory.BUILDING_BLOCKS,
                                                result,
                                                resultCount)
                                .unlockedBy(getConversionRecipeName(material, result), has(material))
                                .save(c, extra);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material,
                        String extra) {
                stonecutter(c, result, material, 1, extra);
        }

        default void stonecutter(RecipeOutput c, ItemLike result, TagKey<Item> material) {
                stonecutter(c, result, material, 1,
                                getModName() + ":" + getItemNameGen(result) + "_from_stonecutting");
        }

        default void stonecutter(RecipeOutput c, ItemLike result, ItemLike material, String extra) {
                stonecutter(c, result, material, 1, extra);
        }

        default void pillar(RecipeOutput c, ItemLike result, ItemLike material) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 2)
                                .define('#', material)
                                .pattern("#")
                                .pattern("#")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c);
        }

        default void pillar(RecipeOutput c, ItemLike result, ItemLike material,
                        String extra) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 2)
                                .define('#', material)
                                .pattern("#")
                                .pattern("#")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c, extra);
        }

        default void tiles(RecipeOutput c, ItemLike result, ItemLike material,
                        String extra) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c, extra);
        }

        default void tiles(RecipeOutput c, ItemLike result, ItemLike material) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .pattern("##")
                                .pattern("##")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c);
        }

        default void cross(RecipeOutput c, ItemLike result, ItemLike material, ItemLike middle) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .define('A', middle)
                                .pattern(" # ")
                                .pattern("#A#")
                                .pattern(" # ")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c);
        }

        default void cross(RecipeOutput c, ItemLike result, ItemLike material, TagKey<Item> middle) {
                ShapedRecipeBuilder.shaped(getItems(), RecipeCategory.BUILDING_BLOCKS, result, 4)
                                .define('#', material)
                                .define('A', middle)
                                .pattern(" # ")
                                .pattern("#A#")
                                .pattern(" # ")
                                .unlockedBy(getHasNameGen(material), has(material))
                                .save(c);
        }

}
