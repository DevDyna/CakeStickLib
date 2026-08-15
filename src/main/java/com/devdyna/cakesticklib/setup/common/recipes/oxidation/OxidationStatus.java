package com.devdyna.cakesticklib.setup.common.recipes.oxidation;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public enum OxidationStatus implements StringRepresentable {
    SCRAPPING("scrapping"),
    OXIDIZING("oxidizing"),
    WAXING("waxing"),
    UNWAXING("unwaxing"),
    CUSTOM("custom");

    private final String i;

    OxidationStatus(String i) {
        this.i = i;
    }

    @Override
    public String getSerializedName() {
        return i;
    }

    public record OxidationInput(
            OxidationStatus type,
            ItemStack input) implements RecipeInput {

        @Override
        public ItemStack getItem(int index) {
            return input;
        }

        @Override
        public int size() {
            return 1;
        }
    }

    public static final Codec<OxidationStatus> CODEC = StringRepresentable.fromEnum(OxidationStatus::values);
    public static final StreamCodec<ByteBuf, OxidationStatus> STREAM_CODEC = ByteBufCodecs
            .fromCodec(OxidationStatus.CODEC);
}