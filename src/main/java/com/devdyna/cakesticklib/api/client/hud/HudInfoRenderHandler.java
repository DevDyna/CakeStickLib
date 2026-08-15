package com.devdyna.cakesticklib.api.client.hud;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;

public interface HudInfoRenderHandler {
    abstract void render(GuiGraphicsExtractor graphics, LocalPlayer player,
            HitResult hit, ClientLevel level,
            ItemStack item, int widthGui, int heightGui);

    // TODO API : decide if they have a future sense
    @Deprecated
    public interface ItemHudInfo extends HudInfoRenderHandler {

    }

    @Deprecated
    public interface BlockHudInfo extends HudInfoRenderHandler {

    }

}
