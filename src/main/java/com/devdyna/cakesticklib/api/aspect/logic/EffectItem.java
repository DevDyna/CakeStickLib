package com.devdyna.cakesticklib.api.aspect.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import com.devdyna.cakesticklib.api.utils.ColorUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public interface EffectItem<T extends Item> {

    T create(Properties p, ArrayList<MobEffectInstance> effects);

    List<Component> getEffectToolTip();

    /**
     * Create an food item with a dedicated tooltip to show mobEffects
     */
    static class Builder<T extends Item> {

        private final List<FoodEffect> list = new ArrayList<>();
        private final Properties p;
        private final BiFunction<Properties, ArrayList<MobEffectInstance>, T> factory;

        private FoodProperties food;

        public Builder(Properties p, BiFunction<Properties, ArrayList<MobEffectInstance>, T> factory) {
            this.p = p;
            this.factory = factory;
        }

        public static <T extends Item> Builder<T> of(Properties p,
                BiFunction<Properties, ArrayList<MobEffectInstance>, T> factory) {
            return new Builder<>(p, factory);
        }

        public Builder(Properties p) {
            this(p, (i, l) ->(T) new Item(i));
        }

        public static <T extends Item> Builder<T> of(Properties p) {
            return new Builder<>(p);
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

    default List<Component> defaultEffectTooltip(List<MobEffectInstance> effects) {
        var list = new ArrayList<Component>();

        for (var instance : effects) {
            var effect = instance.getEffect().value();

            var result = Component.empty()
                    .append(effect.getDisplayName());

            if (instance.getAmplifier() > 0)
                result.append(" ")
                        .append(Component.translatable(
                                "potion.potency." + instance.getAmplifier()));

            result.append(" (")
                    .append(MobEffectUtil.formatDuration(
                            instance,
                            1.0F,
                            Minecraft.getInstance().level
                                    .tickRateManager()
                                    .tickrate()))
                    .append(")");

            result.withStyle(style -> style.withColor(switch (effect.getCategory()) {
                case BENEFICIAL -> ColorUtils.rgb(85, 85, 255);
                case HARMFUL -> ColorUtils.rgb(255, 85, 85);
                case NEUTRAL -> ColorUtils.rgb(170, 0, 170);
            }));

            list.add(result);
        }

        return list;
    }
}