package com.devdyna.cakesticklib.api.upgrades.modifiers.base;

import com.mojang.serialization.Codec;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public interface BaseModifier {

    Codec<? extends BaseModifier> getCodec();

    StreamCodec<FriendlyByteBuf, ? extends BaseModifier> getStreamCodec();

    public enum UseType {
        ITEM("item"),
        FLUID("fluid");

        private final String id;

        UseType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public static final Codec<UseType> CODEC = Codec.STRING.xmap(
                id -> switch (id) {
                    case "item" -> ITEM;
                    case "fluid" -> FLUID;
                    default -> throw new IllegalArgumentException("Unknown Modifier#UseType: " + id);
                },
                UseType::getId);

        public static final StreamCodec<FriendlyByteBuf, UseType> STREAM_CODEC = StreamCodec.of(
                (buf, value) -> buf.writeUtf(value.getId()),
                buf -> switch (buf.readUtf()) {
                    case "item" -> ITEM;
                    case "fluid" -> FLUID;
                    default -> throw new IllegalArgumentException("Unknown Modifier#UseType");
                });
    }
}
