package com.devdyna.cakesticklib.setup.registry.builders.dynamite;

import com.devdyna.cakesticklib.api.RandomUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
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

        if (level instanceof ServerLevel serverLevel)
            Projectile.spawnProjectileFromRotation(DynamiteEntity::new, serverLevel, item, player, 0.0F, 1.5F, 1.0F);

        item.consume(1, player);
        return InteractionResult.SUCCESS;
    }

    @Override
    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        return new DynamiteEntity(level, position.x(), position.y(), position.z(), itemStack);
    }

}
