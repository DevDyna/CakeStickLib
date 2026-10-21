package com.devdyna.cakesticklib.setup.compat.jade.provider;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.TickerDelay;
import com.devdyna.cakesticklib.api.aspect.logic.TickerDelay.TimerStyle;
import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.utils.StringUtil;
import com.devdyna.cakesticklib.api.utils.TimeUtil;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public class TextualRecipeProvider {

    public static final Identifier UUID = x.rl(MODULE_ID, "textual_delay");
    public static final ClientProvider CLIENT = new ClientProvider();
    public static final ServerProvider SERVER = new ServerProvider();

    public record RecordProvider(int timer, float multiplier, boolean hide) {
    }

    public static class ServerProvider implements StreamServerDataProvider<BlockAccessor, RecordProvider> {

        @Override
        public @Nullable RecordProvider streamData(BlockAccessor a) {
            return (a.getBlockEntity() instanceof TickerDelay t)
                    && (TimerStyle.isTextual(t))
                            ? new RecordProvider((t.getTicker() == null
                                    ? 0
                                    : t.getTicker().diff()), t.getMultiplier(), t.hideMultiplier())
                            : null;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, RecordProvider> streamCodec() {
            return StreamCodec.composite(
                    ByteBufCodecs.INT, RecordProvider::timer,
                    ByteBufCodecs.FLOAT, RecordProvider::multiplier,
                    ByteBufCodecs.BOOL, RecordProvider::hide,
                    RecordProvider::new);
        }

        @Override
        public Identifier getUid() {
            return TextualRecipeProvider.UUID;
        }
    }

    public static class ClientProvider implements IBlockComponentProvider {

        @Override
        public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

            var v = TextualRecipeProvider.SERVER.decodeFromData(a);

            if (v.isEmpty())
                return;

            var r = v.get();

            t.add((r.hide()
                    ? Component.empty()
                    : Component.translatable(MODULE_ID + ".jade.textual_delay.multiplier",
                            (r.multiplier() >= 1.0f ? TipColors.GREEN : TipColors.RED)
                                    + ((r.multiplier() > 0 && r.multiplier() < 0.1)
                                            ? "⅟" + ((int) (1f / r.multiplier()))
                                            : StringUtil.cut(r.multiplier()))))
                    .append(
                            Component.translatable(
                                    MODULE_ID + ".jade.textual_delay.time." + (r.timer() < 10 ? "empty" : "left"),
                                    TimeUtil.getSimpleTimer(r.timer() + 19))));

        }

        @Override
        public Identifier getUid() {
            return TextualRecipeProvider.UUID;
        }

    }
}