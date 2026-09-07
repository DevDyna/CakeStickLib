package com.devdyna.cakesticklib.setup.common.events;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.eject.EjectModifierMenu;
import com.devdyna.cakesticklib.api.upgrades.modifiers.ModifierUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ModifierItemEvents {

        @SubscribeEvent
        public static void itemUseWithout(PlayerInteractEvent.RightClickItem event) {
                var pos = event.getPos();
                var player = event.getEntity();
                var item = event.getItemStack();

                if (ModifierUtils.itemValid(item)) {

                        if (player.isCrouching()) {

                                var hit = player.pick(player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE),
                                                1.0F, false);

                                if (hit.getType() == HitResult.Type.BLOCK)
                                        return;

                                if (!ModifierUtils.has(item, UpgradeType.EJECT))
                                        return;

                                player.openMenu(new SimpleMenuProvider(
                                                (id, i, p) -> new EjectModifierMenu(id, i, pos),
                                                Component.translatable(MODULE_ID + ".eject.gui.title")),
                                                b -> b.writeBlockPos(pos));

                                event.setCanceled(true);
                                return;
                        }
                }
        }

        @SubscribeEvent
        public static void itemUseOnBlock(PlayerInteractEvent.RightClickBlock event) {
                var pos = event.getPos();
                var player = event.getEntity();
                var item = event.getItemStack();

                if (ModifierUtils.itemValid(item)) {

                        if (player.isCrouching()) {

                                if (!ModifierUtils.has(item, UpgradeType.EJECT))
                                        return;

                                player.openMenu(new SimpleMenuProvider(
                                                (id, i, p) -> new EjectModifierMenu(id, i, pos),
                                                Component.translatable(MODULE_ID + ".eject.gui.title")),
                                                b -> b.writeBlockPos(pos));

                                event.setCanceled(true);
                                return;
                        }

                }
        }
}
