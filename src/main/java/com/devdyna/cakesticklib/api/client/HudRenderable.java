package com.devdyna.cakesticklib.api.client;

import com.devdyna.cakesticklib.api.client.hud.HudInfoRenderHandler;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.client.gui.GuiLayer;

public class HudRenderable {

     public static final GuiLayer LAYER = HudRenderable::render;

     public static void render(
               GuiGraphicsExtractor gui,
               DeltaTracker deltaTracker) {
          var mc = Minecraft.getInstance();
          var hit = mc.hitResult;
          var level = mc.level;
          var player = mc.player;

          if (player == null || hit == null || level == null)
               return;

          var item = player.getMainHandItem();

          if (item == null)
               return;

          var width = gui.guiWidth();
          var height = gui.guiHeight();

          if (item.getItem() instanceof HudInfoRenderHandler.ItemHudInfo itemHudInfo)
               itemHudInfo.render(gui, player, hit, level, item, width, height);

          if (level.getBlockState(BlockPos.containing(hit.getLocation()))
                    .getBlock() instanceof HudInfoRenderHandler.BlockHudInfo blockHudInfo)
               blockHudInfo.render(gui, player, hit, level, item, width, height);

     }
}
