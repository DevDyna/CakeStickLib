package com.devdyna.cakesticklib.setup.common.events;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.aspect.logic.UpgradeInstallable;
import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.utils.StringUtil;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class EjectModifierSetup {

    @SubscribeEvent
    public static void itemUseOnBlock(PlayerInteractEvent.RightClickBlock event) {
        var pos = event.getPos();
        var level = event.getLevel();
        var player = event.getEntity();
        var be = level.getBlockEntity(pos);
        var item = event.getItemStack();
        var face = event.getFace();
        var hand = event.getHand();

        if (UpgradeComponents.isValid(item)) {

            if (player.isCrouching()) {
                if (be instanceof UpgradeInstallable machineBE)
                    if (machineBE.tryAddUpgrade(item)) {
                        if (!player.isCreative())
                            item.shrink(1);
                        player.swing(hand);
                        player.sendOverlayMessage(Component.translatable(MODULE_ID + ".item_use.install"));
                        level.playSound(player, pos, SoundEvents.SMITHING_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.5F);

                    }

            } else if (UpgradeComponents.has(item, UpgradeType.EJECT))
                if (UpgradeComponents.get(item, UpgradeType.EJECT) != face) {
                    player.swing(hand);
                    UpgradeComponents.modify(item, UpgradeType.EJECT, face);
                    player.sendOverlayMessage(Component.translatable(MODULE_ID + ".item_use.eject")
                            .append(TipColors.GREEN + StringUtil.nameCapitalized(face.getName())));

                }
        }

    }
}
