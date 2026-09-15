package com.devdyna.cakesticklib.api.upgrades.eject;

import com.devdyna.cakesticklib.api.gui.buttons.DirectionalItemButton;
import com.devdyna.cakesticklib.api.gui.buttons.ItemButton;
import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.modifiers.DirectionalModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.ModifierUtils;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier.UseType;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.common.network.payloads.eject.EjectDirectionPayload;
import com.devdyna.cakesticklib.setup.common.network.payloads.eject.EjectUseTypePayload;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EjectModifierScreen extends AbstractContainerScreen<EjectModifierMenu> {

        private final Map<Direction, DirectionalItemButton> BUTTONS = new EnumMap<>(Direction.class);
        private Direction saved = Direction.NORTH;

        private ItemButton typeButton;
        private UseType cachedType = UseType.ITEM;

        public EjectModifierScreen(EjectModifierMenu menu, Inventory inventory, Component title) {
                super(menu, inventory, title, 128, 128);
        }

        @Override
        protected void init() {

                super.init();

                BUTTONS.clear();

                var modifier = (DirectionalModifier) ModifierUtils.get(
                                minecraft.player.getActiveItem(),
                                UpgradeType.EJECT);

                if (modifier == null)
                        return;

                saved = modifier.dir();
                cachedType = modifier.type();

                addBundledButtons(5 + 20 + 5 + 20 + 5, 17 + 20 + 20 + 10);

                addTypedButton(105// 87 - 4
                                , 17 // 67 - 4
                );

        }

        private void addBundledButtons(int x, int y) {

                addSynchedButton(Direction.NORTH, getLeftPos() - 20 + x, getTopPos() - 20 + y);
                addSynchedButton(Direction.SOUTH, getLeftPos() + 21 + x, getTopPos() + 20 + y);
                addSynchedButton(Direction.EAST, getLeftPos() - 20 + x, getTopPos() + y);
                addSynchedButton(Direction.WEST, getLeftPos() + 21 + x, getTopPos() + y);
                addSynchedButton(Direction.UP, getLeftPos() + x, getTopPos() - 20 + y);
                addSynchedButton(Direction.DOWN, getLeftPos() + x, getTopPos() + 20 + y);

                if (minecraft == null || minecraft.level == null)
                        return;

                for (var entry : BUTTONS.entrySet())
                        entry.getValue().setStack(minecraft.level.getBlockState(
                                        menu.getPos().relative(entry.getKey())));

                updateButtons();
        }

        private void addTypedButton(int xOf, int yOf) {

                typeButton = new ItemButton(getLeftPos() + xOf, getTopPos() + yOf,
                                16 // + 8
                                , 16// + 8
                                ,
                                button -> {

                                        cachedType = cachedType == UseType.ITEM
                                                        ? UseType.FLUID
                                                        : UseType.ITEM;

                                        ClientPacketDistributor.sendToServer(
                                                        new EjectUseTypePayload(menu.containerId, cachedType));
                                }, Component.translatable(
                                                MODULE_ID + ".widgets.button." + cachedType.name().toLowerCase()));

                typeButton.onButtonPress(graphics -> typeButton.renderImage(graphics, x.rl(MODULE_ID,
                                "textures/gui/modifier/buttons/"
                                                + (cachedType == UseType.ITEM ? "item" : "fluid") + ".png")));

                addRenderableWidget(typeButton);
        }

        private void addSynchedButton(Direction dir, int xOf, int yOf) {

                var button = new DirectionalItemButton(
                                xOf, yOf,
                                16, 16,
                                dir,
                                dir == saved,
                                press -> {
                                        saved = dir;
                                        updateButtons();
                                        ClientPacketDistributor
                                                        .sendToServer(new EjectDirectionPayload(menu.containerId, dir));
                                });

                button.defineSprites((graphics, clicked) -> {
                        if (clicked)
                                button.draw(graphics, x.rl(MODULE_ID, "textures/gui/modifier/buttons/"+(cachedType == UseType.ITEM ? "item" : "fluid")+".png"));

                        else
                                button.draw(graphics, x.rl(MODULE_ID, "textures/gui/modifier/buttons/off.png"));

                });

                BUTTONS.put(dir, button);

                addRenderableWidget(button);
        }

        private void updateButtons() {
                for (var entry : BUTTONS.entrySet())
                        entry.getValue().setPress(entry.getKey() == saved);
        }

        @Override
        public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

                graphics.blit(RenderPipelines.GUI_TEXTURED, x.rl(MODULE_ID,
                                "textures/gui/modifier/eject.png"),
                                this.getLeftPos(), this.getTopPos(),
                                0.0F, 0.0F,
                                128, 128,
                                128, 128);

                // graphics.blit(RenderPipelines.GUI_TEXTURED, x.rl(MODULE_ID,
                // "textures/item/upgrade/base.png"),
                // this.getLeftPos(), this.getTopPos(),
                // 0.0F, 0.0F,
                // 128, 128,
                // 128, 128, 0x40FFFFFF);

                // graphics.blit(RenderPipelines.GUI_TEXTURED,
                // x.rl(MODULE_ID, "textures/item/upgrade/eject/"
                // + (cachedType == UseType.ITEM ? "item" : "fluid") + ".png"),
                // this.getLeftPos(), this.getTopPos(),
                // 0.0F, 0.0F,
                // 128, 128,
                // 128, 128, 0x40FFFFFF);

                graphics.blit(RenderPipelines.GUI_TEXTURED,
                                x.rl(MODULE_ID, "textures/gui/modifier/buttons/middle.png"),
                                getLeftPos() + 30 + 20 + 5, getTopPos() + 67,
                                0, 0,
                                16, 16,
                                16, 16);

                if (minecraft == null || minecraft.level == null)
                        return;

                var state = minecraft.level.getBlockState(menu.getPos());

                graphics.item(x.asIcon(state), getLeftPos() + 30 + 20 + 5, getTopPos() + 67);

                typeButton.setStack(x.item(cachedType == UseType.ITEM ? Items.CHEST : Items.BUCKET));
                typeButton.setComponents(List.of(
                                Component.translatable(
                                                MODULE_ID + ".widgets.button." + cachedType.name().toLowerCase())));

        }

        @Override
        protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {

                graphics.text(this.font, this.title, 5 + 2, 5, -12566464, false);

                graphics.text(this.font,
                                Component.translatable(MODULE_ID + ".eject.gui.dir", saved.name()),
                                23 + 5 - 14, 93 + 2 - 74,
                                ColorUtils.argb(ColorUtils.GREEN.LIME_GREEN), false);
        }

}