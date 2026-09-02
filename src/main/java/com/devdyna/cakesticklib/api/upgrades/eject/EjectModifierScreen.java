package com.devdyna.cakesticklib.api.upgrades.eject;

import com.devdyna.cakesticklib.api.gui.buttons.DirectionalItemButton;
import com.devdyna.cakesticklib.api.upgrades.UpgradeComponents.UpgradeType;
import com.devdyna.cakesticklib.api.upgrades.modifiers.DirectionalModifier;
import com.devdyna.cakesticklib.api.upgrades.modifiers.ModifierUtils;
import com.devdyna.cakesticklib.api.upgrades.modifiers.base.BaseModifier.UseType;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.common.network.payloads.eject.EjectDirectionPayload;
import com.devdyna.cakesticklib.setup.common.network.payloads.eject.EjectUseTypePayload;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.EnumMap;
import java.util.Map;

public class EjectModifierScreen extends AbstractContainerScreen<EjectModifierMenu> {

        private final Map<Direction, DirectionalItemButton> BUTTONS = new EnumMap<>(Direction.class);
        private Direction saved = Direction.NORTH;

        private Button typeButton;
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

                addBundledButtons(0, +10);

                addTypedButton(0, -10);

        }

        private void addBundledButtons(int x, int y) {

                addSynchedButton(Direction.NORTH, getLeftPos() + 35 + x, getTopPos() + 27 + y);
                addSynchedButton(Direction.SOUTH, getLeftPos() + 76 + x, getTopPos() + 67 + y);
                addSynchedButton(Direction.EAST, getLeftPos() + 35 + x, getTopPos() + 47 + y);
                addSynchedButton(Direction.WEST, getLeftPos() + 76 + x, getTopPos() + 47 + y);
                addSynchedButton(Direction.UP, getLeftPos() + 55 + x, getTopPos() + 27 + y);
                addSynchedButton(Direction.DOWN, getLeftPos() + 55 + x, getTopPos() + 67 + y);

                if (minecraft == null || minecraft.level == null)
                        return;

                for (var entry : BUTTONS.entrySet()) {

                        var button = entry.getValue();

                        var state = minecraft.level.getBlockState(
                                        menu.getPos().relative(entry.getKey()));

                        if (state.isAir())
                                button.setPreviewStack(ItemStack.EMPTY);
                        else
                                button.setPreviewStack(state.getBlock().asItem().getDefaultInstance());
                }

                updateButtons();
        }

        private void addTypedButton(int x, int y) {

                typeButton = Button.builder(
                                Component.literal(cachedType.getId().toUpperCase()),
                                button -> {

                                        cachedType = cachedType == UseType.ITEM
                                                        ? UseType.FLUID
                                                        : UseType.ITEM;

                                        button.setMessage(Component.literal(cachedType.getId().toUpperCase()));

                                        ClientPacketDistributor.sendToServer(
                                                        new EjectUseTypePayload(menu.containerId, cachedType));
                                })
                                .bounds(
                                                getLeftPos() + 35 + x, getTopPos() + 108 + y,
                                                58, 20)
                                .build();

                addRenderableWidget(typeButton);
        }

        private void addSynchedButton(Direction dir, int x, int y) {

                var button = new DirectionalItemButton(
                                x, y,
                                16, 16,
                                dir,
                                dir == saved,
                                press -> {
                                        saved = dir;
                                        updateButtons();
                                        ClientPacketDistributor
                                                        .sendToServer(new EjectDirectionPayload(menu.containerId, dir));
                                });

                BUTTONS.put(dir, button);

                addRenderableWidget(button);
        }

        private void updateButtons() {
                for (var entry : BUTTONS.entrySet())
                        entry.getValue().update(entry.getKey() == saved);
        }

        @Override
        public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

                graphics.blit(RenderPipelines.GUI_TEXTURED, x.rl(MODULE_ID, "textures/gui/modifier/eject.png"),
                                this.getLeftPos(), this.getTopPos(),
                                0.0F, 0.0F,
                                128, 128,
                                128, 128);

                graphics.blit(RenderPipelines.GUI_TEXTURED,
                                x.rl(MODULE_ID, "textures/gui/modifier/buttons/middle.png"),
                                getLeftPos() + 55, getTopPos() + 47 + 10,
                                0, 0,
                                16, 16,
                                16, 16);

                if (minecraft == null || minecraft.level == null)
                        return;

                var state = minecraft.level.getBlockState(menu.getPos());

                graphics.item(
                                (state.getBlock().asItem() == null
                                                && !(state.isAir() || state.is(Blocks.BARRIER))
                                                                ? x.item(Items.BARRIER)
                                                                : x.item(state)),
                                getLeftPos() + 55,
                                getTopPos() + 47);

        }

        @Override
        protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {

                graphics.text(this.font, this.title, 5 + 2, 5, -12566464, false);

                graphics.text(this.font,
                                Component.translatable(MODULE_ID + ".eject.gui.dir", saved.name()),
                                23 + 5, 93 + 2 - 74,
                                ColorUtils.argb(ColorUtils.LIME_GREEN), false);
        }

}