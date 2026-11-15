package com.devdyna.cakesticklib.setup.registry.builders.dynamite;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.ToolTipHelper;
import com.devdyna.cakesticklib.setup.registry.LibComponents;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class DynamiteItem extends Item implements ProjectileItem {

    public DynamiteItem(Properties p) {
        super(p);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        var item = player.getItemInHand(hand);

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW,
                SoundSource.NEUTRAL, 0.5F, (float) RandomUtil.between(level, 1.75, 1.85));

        if (level instanceof ServerLevel server)
            Projectile.spawnProjectileFromRotation(DynamiteEntity::new, server, item, player, 0.0F, 1.5F, 1.0F);

        item.consume(1, player);
        return InteractionResult.SUCCESS;
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack item, Direction direction) {
        var entity = new DynamiteEntity(level, pos, item);

        if (item.has(LibComponents.EXPLOSION_DELAY))
            entity.delay = Math.max(1, item.get(LibComponents.EXPLOSION_DELAY));

        if (item.has(LibComponents.EXPLOSION_RANGE))
            entity.range = Math.max(0, item.get(LibComponents.EXPLOSION_RANGE));

        return entity;
    }

    // TODO rework to a dedicated class
    public static void createExplosiveTooltip(List<Component> tip, ItemStack item) {

        if (item.has(LibComponents.EXPLOSION_RANGE))
            tip.add(ToolTipHelper.INDEX,
                    Component.translatable(MODULE_ID + ".explosive.range", item.get(LibComponents.EXPLOSION_RANGE)).withStyle(ChatFormatting.DARK_GREEN));

        if (item.has(LibComponents.EXPLOSION_DELAY))
            tip.add(ToolTipHelper.INDEX,
                    Component.translatable(MODULE_ID + ".explosive.delay", item.get(LibComponents.EXPLOSION_DELAY)).withStyle(ChatFormatting.DARK_GREEN));

        tip.add(ToolTipHelper.INDEX, Component.translatable(MODULE_ID + ".explosive.stats"));
        tip.add(ToolTipHelper.INDEX, Component.empty());

    }

}
