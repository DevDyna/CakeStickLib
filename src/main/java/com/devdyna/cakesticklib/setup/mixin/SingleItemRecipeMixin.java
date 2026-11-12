package com.devdyna.cakesticklib.setup.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.SingleItemRecipe;

@Mixin(SingleItemRecipe.class)
public interface SingleItemRecipeMixin {
    @Accessor("result")
    ItemStackTemplate getResult();
}
