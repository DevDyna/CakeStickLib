package com.devdyna.cakesticklib.setup.registry.builders;

import com.devdyna.cakesticklib.api.aspect.logic.UpgradeInstallable;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class IndustrialUpgrade extends Item {

    public IndustrialUpgrade(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var level = c.getLevel();
        var pos = c.getClickedPos();
        var item = c.getItemInHand();
        var be = level.getBlockEntity(pos);
        var player = c.getPlayer();

        if (player.isCrouching() && be instanceof UpgradeInstallable machineBE) {

            if (machineBE.tryAddUpgrade(item)) {
                if (!player.isCreative())
                    item.shrink(1);
                return InteractionResult.SUCCESS;
            }

        }

        return InteractionResult.FAIL;

    }

    /**
     * Value 0 will exclude the modifier
     * <br/>
     * <br/>
     * 100 => x 1.0
     * <br/>
     * <br/>
     * 
     * @param s  speed %
     * @param e energy usage %
     * @param l  secondary output luck %
     * @param f  fluid usage %
     * <br/><br/>
     * Cannot be used on Datagen and Item registry!
     */
    @SuppressWarnings("deprecation")
    public ItemStack set(int s, int e, int l, int f) {
        return UpgradeComponents.create(this, s, e, l, f);
    }

}