package com.devdyna.cakesticklib.setup.common.events;

import java.util.List;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.utils.RecipeUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.Config;
import com.devdyna.cakesticklib.setup.common.recipes.tool_durability.DurabilityConsumeRecipe;
import com.devdyna.cakesticklib.setup.registry.LibTags;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.CraftingInput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

public class ToolMineTweaks {

    @SubscribeEvent
    public static void onBlockDrops(BlockDropsEvent event) {

        var entity = event.getBreaker();
        var item = event.getTool();
        var block = event.getState();
        var level = event.getLevel();
        var drops = event.getDrops();

        if (entity instanceof Player player ? player.isCreative() : false)
            return;

        if (Config.CROOK_DROP_EVENT.get())
            if (block.is(LibTags.Blocks.MINEABLE_WITH_CROOK) && item.is(LibTags.Items.TOOLS_LEAVES_DROP_INCREASE))
                drops = drops.stream().map(e -> {
                    e.getItem().setCount(e.getItem().count() + (int) RandomUtil.between(level, 2, 5));
                    return e;
                }).toList();

        if (Config.MINED_RETURN_CRAFT_EVENT.get())
            if (!block.is(LibTags.Blocks.IGNORE_MINEABLE_WITH_CRAFTING_TOOLS)
                    && item.is(LibTags.Items.TOOLS_MINING_CRAFTING)) {

                var input = CraftingInput.of(2, 1, List.of(item, x.item(block)));

                DurabilityConsumeRecipe saved = null;

                // TODO investigate why it doesn't work correctly
                for (var holder : RecipeUtils.getRecipesByType(level, h -> h instanceof DurabilityConsumeRecipe)) {
                    var recipe = (DurabilityConsumeRecipe) holder.value();

                    if (recipe.getInput().test(input, level) && recipe.getInput().isTool(item)
                            && recipe.matches(input, level)) {
                        saved = recipe;
                        continue;
                    }

                }

                if (saved == null)
                    return;

                var result = saved.getResult();

                //TODO doesn't work correctly (maybe it is ignored?)
                item.hurtAndBreak(saved.getInput().getDurability(), (LivingEntity) entity, InteractionHand.MAIN_HAND);

                drops = drops.stream().map(e -> {
                    if (e.getItem().is(x.item(block).getItem()))
                        e.setItem(result.create());

                    return e;
                }).toList();

            }

    }

}
