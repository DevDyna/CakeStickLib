package com.devdyna.cakesticklib.setup.common.network;


import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.eject.EjectModifierMenu;
import com.devdyna.cakesticklib.api.upgrades.modifiers.DirectionalModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.ModifierUtils;
import com.devdyna.cakesticklib.setup.common.network.payloads.eject.EjectDirectionPayload;
import com.devdyna.cakesticklib.setup.common.network.payloads.eject.EjectUseTypePayload;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PayloadRegistry {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payload = event.registrar(MODULE_ID);

        payload.playToServer(EjectDirectionPayload.TYPE, EjectDirectionPayload.STREAM_CODEC,
                (p2s, ctx) -> {

                    ctx.enqueueWork(() -> {

                        if (!(ctx.player() instanceof ServerPlayer player))
                            return;

                        if (!(player.containerMenu instanceof EjectModifierMenu menu))
                            return;

                        if (menu.containerId != p2s.id())
                            return;

                        var stack = ItemStack.EMPTY;

                        for (var hand : List.of(player.getMainHandItem(), player.getOffhandItem()))
                            if (!hand.isEmpty()
                                    && ModifierUtils.itemValid(hand)
                                    && ModifierUtils.has(
                                            hand,
                                            UpgradeType.EJECT)) {
                                stack = hand;
                                break;
                            }

                        if (stack.isEmpty())
                            return;

                        if (!ModifierUtils.itemValid(stack))
                            return;

                        if (!ModifierUtils.has(stack, UpgradeType.EJECT))
                            return;

                        var modifier = (DirectionalModifier) ModifierUtils.get(stack, UpgradeType.EJECT);

                        if (modifier == null)
                            return;

                        ModifierUtils.modify(stack, UpgradeType.EJECT,
                                DirectionalModifier.of(p2s.dir(), modifier.type()));

                        menu.broadcastChanges();
                    });

                });
    
    
    payload.playToServer(EjectUseTypePayload.TYPE, EjectUseTypePayload.STREAM_CODEC,
                (p2s, ctx) -> {

                    ctx.enqueueWork(() -> {

                        if (!(ctx.player() instanceof ServerPlayer player))
                            return;

                        if (!(player.containerMenu instanceof EjectModifierMenu menu))
                            return;

                        if (menu.containerId != p2s.id())
                            return;

                        var stack = ItemStack.EMPTY;

                        for (var hand : List.of(player.getMainHandItem(), player.getOffhandItem()))
                            if (!hand.isEmpty()
                                    && ModifierUtils.itemValid(hand)
                                    && ModifierUtils.has(
                                            hand,
                                            UpgradeType.EJECT)) {
                                stack = hand;
                                break;
                            }

                        if (stack.isEmpty())
                            return;

                        if (!ModifierUtils.itemValid(stack))
                            return;

                        if (!ModifierUtils.has(stack, UpgradeType.EJECT))
                            return;

                        var modifier = (DirectionalModifier) ModifierUtils.get(stack, UpgradeType.EJECT);

                        if (modifier == null)
                            return;

                        ModifierUtils.modify(stack, UpgradeType.EJECT,
                                DirectionalModifier.of(modifier.dir(), p2s.useType()));

                        menu.broadcastChanges();
                    });

                });
    
    
    
            }
}
