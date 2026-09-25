package com.devdyna.cakesticklib.setup.common.recipes.oxidation;

import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

public enum OxidationStatus implements StringRepresentable {
    SCRAPPING("scrapping", x.rl(NeoForgeMod.MOD_ID, "datamaps/scrapping")),
    OXIDIZING("oxidizing", NeoForgeDataMaps.OXIDIZABLES.id().withPrefix("datamaps/")),
    WAXING("waxing", NeoForgeDataMaps.WAXABLES.id().withPrefix("datamaps/")),
    UNWAXING("unwaxing", x.rl(NeoForgeMod.MOD_ID, "datamaps/unwaxing")),
    CUSTOM("custom", x.rl(NeoForgeMod.MOD_ID, "custom/"));

    private String i;
    private Identifier rl;

    OxidationStatus(String i, Identifier rl) {
        this.i = i;
        this.rl = rl;
    }

    @Override
    public String getSerializedName() {
        return i;
    }

    public boolean isCustom() {
        return this.equals(OxidationStatus.CUSTOM);
    }

    public Identifier getIdentifier() {
        return rl;
    }

    public static final Codec<OxidationStatus> CODEC = StringRepresentable.fromEnum(OxidationStatus::values);
    public static final StreamCodec<ByteBuf, OxidationStatus> STREAM_CODEC = ByteBufCodecs
            .fromCodec(OxidationStatus.CODEC);

    public record OxidationInput(OxidationStatus type, ItemStack input) implements RecipeInput {

        @Override
        public ItemStack getItem(int index) {
            return input;
        }

        @Override
        public int size() {
            return 1;
        }
    }

}