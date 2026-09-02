package com.devdyna.cakesticklib.api.datagen.selectors;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.modifiers.DirectionalModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.ModifierUtils;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier.UseType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public record EjectTypeProperty() implements SelectItemModelProperty<Integer> {

    public static final Type<EjectTypeProperty, Integer> TYPE = Type.create(
            MapCodec.unit(new EjectTypeProperty()),
            Codec.INT
    );

    @Override
    public @Nullable Integer get(
            ItemStack stack,
            @Nullable ClientLevel level,
            @Nullable LivingEntity entity,
            int seed,
            ItemDisplayContext displayContext
    ) {
        if (!ModifierUtils.has(stack, UpgradeType.EJECT))
            return 0;

        return switch (((DirectionalModifier) ModifierUtils.get(stack, UpgradeType.EJECT)).type()) {
            case UseType.ITEM -> 1;
            case UseType.FLUID -> 2;
            default -> 0;
        };
    }

    @Override
    public Type<EjectTypeProperty, Integer> type() {
        return TYPE;
    }

    @Override
    public Codec<Integer> valueCodec() {
        return Codec.INT;
    }
}