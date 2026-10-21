package com.devdyna.cakesticklib.setup.compat.jade;

import com.devdyna.cakesticklib.setup.compat.jade.provider.*;

import net.minecraft.world.level.block.Block;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration r) {
        r.registerProgressClient(ProgressBarRecipeProvider.INSTANCE);
        r.registerBlockComponent(TextualRecipeProvider.CLIENT, Block.class);
    }

    @Override
    public void register(IWailaCommonRegistration r) {
        r.registerProgress(ProgressBarRecipeProvider.INSTANCE, Block.class);
        r.registerBlockDataProvider(TextualRecipeProvider.SERVER, Block.class);
    }
}
