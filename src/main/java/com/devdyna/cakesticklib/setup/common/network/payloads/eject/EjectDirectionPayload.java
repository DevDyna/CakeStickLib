package com.devdyna.cakesticklib.setup.common.network.payloads.eject;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record EjectDirectionPayload(int id, Direction dir) implements CustomPacketPayload {

    public static final Type<EjectDirectionPayload> TYPE = new Type<>(x.rl(MODULE_ID, "eject_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EjectDirectionPayload> STREAM_CODEC = StreamCodec
            .composite(
                    ByteBufCodecs.VAR_INT, EjectDirectionPayload::id,
                    Direction.STREAM_CODEC, EjectDirectionPayload::dir,
                    EjectDirectionPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
