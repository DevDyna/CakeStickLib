package com.devdyna.cakesticklib.api.templates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import com.devdyna.cakesticklib.api.aspect.logic.EffectItem;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;

public class SimpleEffectItems {

    public static class Item extends net.minecraft.world.item.Item implements EffectItem {

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

    public static class BlockItem extends net.minecraft.world.item.BlockItem implements EffectItem {

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

    /**
     * Create an food item with a dedicated tooltip to show mobEffects
     */
    public static class Builder<T extends net.minecraft.world.item.Item & EffectItem> {

        private final List<FoodEffect> list = new ArrayList<>();
        private final Properties p;
        private final BiFunction<Properties, ArrayList<MobEffectInstance>, T> factory;

        private FoodProperties food;

        private Builder(Properties p, BiFunction<Properties, ArrayList<MobEffectInstance>, T> factory) {
            this.p = p;
            this.factory = factory;
        }

        public static <T extends net.minecraft.world.item.Item & EffectItem> Builder<T> of(Properties p,
                BiFunction<Properties, ArrayList<MobEffectInstance>, T> factory) {
            return new Builder<>(p, factory);
        }

        public static <T extends net.minecraft.world.item.Item & EffectItem> Builder<T> item(Properties p) {
            return of(p, (i, l) -> (T) new SimpleEffectItems.Item(i, l));
        }

        public static <T extends net.minecraft.world.item.Item & EffectItem> Builder<T> blockItem(Block b,
                Properties p) {
            return of(p, (i, l) -> (T) new SimpleEffectItems.BlockItem(b, i, l));
        }

        public Builder<T> food(FoodProperties food) {
            this.food = food;
            return this;
        }

        public Builder<T> effect(FoodEffect effect) {
            this.list.add(effect);
            return this;
        }

        public Builder<T> effect(MobEffectInstance... effects) {
            return effect(FoodEffect.of(effects));
        }

        public Builder<T> effect(float chance, MobEffectInstance... effects) {
            return effect(FoodEffect.of(chance, effects));
        }

        public Builder<T> effect(float chance, List<MobEffectInstance> effects) {
            return effect(FoodEffect.of(chance, effects));
        }

        public Builder<T> effect(List<MobEffectInstance> effects) {
            return effect(FoodEffect.of(effects));
        }

        public T build() {
            var consumable = Consumable.builder();
            var potions = new ArrayList<MobEffectInstance>();

            for (var effect : list) {
                consumable.onConsume(new ApplyStatusEffectsConsumeEffect(effect.effects(), effect.chance()));
                potions.addAll(effect.effects());
            }

            return factory.apply(p.food(food, consumable.build()), potions);
        }

        public record FoodEffect(float chance, List<MobEffectInstance> effects) {
            public static final float SUCCESS = 1.0f;

            public static FoodEffect of(float chance, List<MobEffectInstance> effects) {
                return new FoodEffect(chance, effects);
            }

            public static FoodEffect of(MobEffectInstance... effects) {
                return of(SUCCESS, effects);
            }

            public static FoodEffect of(float chance, MobEffectInstance... effects) {
                return of(chance, List.of(effects));
            }

            public static FoodEffect of(List<MobEffectInstance> effects) {
                return of(SUCCESS, effects);
            }
        }
    }

}
