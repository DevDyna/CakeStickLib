package com.devdyna.cakesticklib.api.templates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.utils.ColorUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class EffectItem extends Item {

    private List<MobEffectInstance> effects;

    public EffectItem(Properties p, List<MobEffectInstance> effects) {
        super(p);
        this.effects = effects;
    }

    /**
     * Create an food item with a dedicated tooltip to show mobEffects
     */
    public static class Builder {

        private final List<FoodEffect> list = new ArrayList<>();
        private Properties p = new Properties();
        private FoodProperties food;

        public Builder properties(Function<Properties, Properties> f) {
            this.p = f.apply(p);
            return this;
        }

        public Builder food(FoodProperties food) {
            this.food = food;
            return this;
        }

        public Builder effect(FoodEffect effect) {
            this.list.add(effect);
            return this;
        }

        /**
         * 100% success
         */
        public Builder effect(MobEffectInstance... effect) {
            return effect(FoodEffect.of(effect));
        }

        public Builder effect(float chance, MobEffectInstance... effect) {
            return effect(FoodEffect.of(chance, effect));
        }

        public Builder effect(float chance, List<MobEffectInstance> effect) {
            return effect(FoodEffect.of(chance, effect));
        }

        public Builder effect(List<MobEffectInstance> effect) {
            return effect(FoodEffect.of(effect));
        }

        public EffectItem build() {
            var consumable = Consumable.builder();
            var potions = new ArrayList<MobEffectInstance>();

            for (var effect : list) {
                consumable.onConsume(new ApplyStatusEffectsConsumeEffect(effect.effects(), effect.chance()));
                potions.addAll(effect.effects());
            }

            return new EffectItem(p.food(food, consumable.build()), potions);
        }

        public record FoodEffect(float chance, List<MobEffectInstance> effects) {

            public static final float SUCCESS = 1.0f;

            public static FoodEffect of(float c, List<MobEffectInstance> e) {
                return new FoodEffect(c, e);
            }

            /**
             * 100% success
             */
            public static FoodEffect of(MobEffectInstance... e) {
                return of(SUCCESS, e);
            }

            public static FoodEffect of(float c, MobEffectInstance... e) {
                return of(c, List.of(e));

            }

            /**
             * 100% success
             */
            public static FoodEffect of(List<MobEffectInstance> e) {
                return of(SUCCESS, e);
            }
        }
    }

    public List<Component> getEffectToolTip() {

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
                    .append(MobEffectUtil.formatDuration(instance, 1.0F,
                            Minecraft.getInstance().level.tickRateManager().tickrate()))
                    .append(")");

            result.withStyle(style -> style.withColor(switch (effect.getCategory()) {
                case MobEffectCategory.BENEFICIAL -> ColorUtils.rgb(85, 85, 255);
                case MobEffectCategory.HARMFUL -> ColorUtils.rgb(255, 85, 85);
                case MobEffectCategory.NEUTRAL -> ColorUtils.rgb(170, 0, 170);
            }));

            list.add(result);
        }

        return list;

    }

}
