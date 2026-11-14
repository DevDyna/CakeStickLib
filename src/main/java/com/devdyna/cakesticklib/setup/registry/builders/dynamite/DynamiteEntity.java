package com.devdyna.cakesticklib.setup.registry.builders.dynamite;

import java.util.Optional;

import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.setup.registry.LibEntities;
import com.devdyna.cakesticklib.setup.registry.LibItems;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.HitResult;

public class DynamiteEntity extends ThrowableItemProjectile {

    private Ticker ticker = null;

    public DynamiteEntity(EntityType<? extends DynamiteEntity> t, Level l) {
        super(t, l);
        initialize();
    }

    public DynamiteEntity(Level level, LivingEntity e, ItemStack i) {
        super(LibEntities.DYNAMITE.get(), e, level, i);
        initialize();
    }

    public DynamiteEntity(Level level, double x, double y, double z, ItemStack i) {
        super(LibEntities.DYNAMITE.get(), x, y, z, level, i);
        initialize();
    }

    private void initialize() {
        if (ticker == null)
            this.ticker = Ticker.of(40);// TODO config
    }

    @Override
    public void tick() {
        super.tick();
        level().addParticle(ParticleTypes.SMOKE, getX(), getY() + 0.5, getZ(), 0.0, 0.0, 0.0);
    }

    @Override
    protected Item getDefaultItem() {
        return LibItems.DYNAMITE.get();
    }

    @Override
    protected void onHit(HitResult r) {
        super.onHit(r);

        level().addParticle(ParticleTypes.FLAME, getX(), getY() + 0.5, getZ(), 0.0, 0.0, 0.0);

        if (ticker.commit())
            if (!level().isClientSide()) {
                discard();

                if (level() instanceof ServerLevel l)
                    if (l.getGameRules().get(GameRules.TNT_EXPLODES))
                        level().explode(getOwner(), null,
                                new SimpleExplosionDamageCalculator(true, false, Optional.of(0.5f), Optional.empty()),
                                getX(), getY(), getZ(), 5, false, ExplosionInteraction.TNT);
            }

    }
}
