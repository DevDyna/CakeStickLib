package com.devdyna.cakesticklib.setup.registry.builders;

import com.devdyna.cakesticklib.setup.Config;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DataMapHooks;

public class RedstoneAcid extends CopperReagentItem {

    public RedstoneAcid(Properties p) {
        super(p);
    }

    @Override
    public Block getNextBlock(Block b) {
        return DataMapHooks.getNextOxidizedStage(b);
    }

    @Override
    public Boolean getConfig() {
        return Config.DISABLE_REDSTONE_ACID_EVENT.get();
    }

}