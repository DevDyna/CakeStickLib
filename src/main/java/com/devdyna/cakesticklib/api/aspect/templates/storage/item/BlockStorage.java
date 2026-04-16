package com.devdyna.cakesticklib.api.aspect.templates.storage.item;

import java.util.function.Function;

import com.devdyna.cakesticklib.api.aspect.templates.menu.BlockMenu;
import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.Block;

public abstract class BlockStorage extends BlockMenu {

    public BlockStorage(Properties p) {
        super(p);
    }

    protected abstract Function<Properties, Block> getFactory();

    @Override
    protected MapCodec<Block> codec() {
        return simpleCodec(getFactory());
    }

}
