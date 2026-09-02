package com.devdyna.cakesticklib.setup.common.network.payloads.eject;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier.UseType;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record EjectUseTypePayload(int id, UseType useType) implements CustomPacketPayload {

    public static final Type<EjectUseTypePayload> TYPE = new Type<>(x.rl(MODULE_ID, "eject_type_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EjectUseTypePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, EjectUseTypePayload::id,
            UseType.STREAM_CODEC, EjectUseTypePayload::useType,
            EjectUseTypePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}