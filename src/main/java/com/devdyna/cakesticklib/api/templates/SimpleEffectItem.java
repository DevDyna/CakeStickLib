package com.devdyna.cakesticklib.api.templates;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.cakesticklib.api.aspect.logic.EffectItem;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;

public class SimpleEffectItem extends Item implements EffectItem<SimpleEffectItem> {

    private List<MobEffectInstance> effects;

    public SimpleEffectItem(Properties p, List<MobEffectInstance> effects) {
        super(p);
        this.effects = effects;
    }

    @Override
    public SimpleEffectItem create(Properties p, ArrayList<MobEffectInstance> e) {
        return new SimpleEffectItem(p, e);
    }

    @Override
    public List<Component> getEffectToolTip() {
       return defaultEffectTooltip(effects);
    }

}
