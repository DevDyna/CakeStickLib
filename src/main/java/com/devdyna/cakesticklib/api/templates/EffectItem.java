package com.devdyna.cakesticklib.api.templates;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

@Deprecated
public class EffectItem extends Item implements com.devdyna.cakesticklib.api.aspect.logic.EffectItem<EffectItem> {

    private List<MobEffectInstance> effects;

    public EffectItem(Properties p, List<MobEffectInstance> effects) {
        super(p);
        this.effects = effects;
    }

    @Deprecated
    public static class Builder {

        private final List<FoodEffect> list = new ArrayList<>();
        private final Properties p;
        private FoodProperties food;

        public Builder(Properties p) {
            this.p = p;
        }

        public static Builder of(Properties p) {
            return new Builder(p);
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

    @Override
    public List<Component> getEffectToolTip() {
        return defaultEffectTooltip(effects);
    }

    @Override
    public EffectItem create(Properties p, ArrayList<MobEffectInstance> effects) {
        return new EffectItem(p, effects);
    }

}
