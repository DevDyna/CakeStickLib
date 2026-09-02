package com.devdyna.cakesticklib.setup.common.network.payloads;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record EjectPayload(int id, Direction dir) implements CustomPacketPayload {

    public static final Type<EjectPayload> TYPE = new Type<>(x.rl(MODULE_ID, "eject_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EjectPayload> STREAM_CODEC = StreamCodec
            .composite(
                    ByteBufCodecs.VAR_INT, EjectPayload::id,
                    Direction.STREAM_CODEC, EjectPayload::dir,
                    EjectPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
