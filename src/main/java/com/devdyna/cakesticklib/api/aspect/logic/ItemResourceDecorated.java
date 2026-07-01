package com.devdyna.cakesticklib.api.aspect.logic;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.primitive.Size;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public interface ItemResourceDecorated {

    /**
     * return the identifier of the item/blockitem resource rendered above the item
     */
    abstract @Nullable Identifier getResource(ItemStack stack);

    /**
     * return the position of the rendered item/blockitem
     */
    default Size getPos() {
        return Size.of(0, 0);
    }
}
