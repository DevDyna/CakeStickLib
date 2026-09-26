package com.devdyna.cakesticklib.api.templates;

import java.util.List;

import com.devdyna.cakesticklib.api.aspect.logic.EffectItem;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.Block;

public class SimpleEffectItems {

    public static class Item extends net.minecraft.world.item.Item implements EffectItem<Item> {

        private List<MobEffectInstance> effects;

        public Item(Properties p, List<MobEffectInstance> effects) {
            super(p);
            this.effects = effects;
        }

        @Override
        public List<Component> getEffectToolTip() {
            return defaultEffectTooltip(effects);
        }

    }

    public static class BlockItem extends net.minecraft.world.item.BlockItem implements EffectItem<BlockItem> {

        private List<MobEffectInstance> effects;

        public BlockItem(Block b, Properties p, List<MobEffectInstance> effects) {
            super(b, p);
            this.effects = effects;
        }

        @Override
        public List<Component> getEffectToolTip() {
            return defaultEffectTooltip(effects);
        }

    }

}
