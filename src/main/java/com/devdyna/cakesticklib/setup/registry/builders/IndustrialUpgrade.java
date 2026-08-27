package com.devdyna.cakesticklib.setup.registry.builders;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.aspect.logic.UpgradeInstallable;
import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.utils.StringUtil;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents.UpgradeType;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

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
        var face = c.getClickedFace();

        if (player.isCrouching() && be instanceof UpgradeInstallable machineBE) {

            if (machineBE.tryAddUpgrade(item)) {
                if (!player.isCreative())
                    item.shrink(1);

                return InteractionResult.SUCCESS;
            }

        }

        if (item.is(this))
            if (UpgradeComponents.has(item, UpgradeType.EJECT))
                if (UpgradeComponents.get(item, UpgradeType.EJECT) != face) {
                    UpgradeComponents.modify(item, UpgradeType.EJECT, face);
                    player.sendOverlayMessage(Component.translatable(MODULE_ID + ".item_use.eject")
                            .append(TipColors.GREEN+StringUtil.nameCapitalized(face.getName())));

                    return InteractionResult.SUCCESS;
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
     * @param s speed %
     * @param e energy usage %
     * @param l secondary output luck %
     * @param f fluid usage %
     * @param j eject direction (nullable)
     *          <br/>
     *          <br/>
     *          Cannot be used on Datagen and Item registry!
     */
    @SuppressWarnings("deprecation")
    public ItemStack set(int s, int e, int l, int f, Direction j) {
        return UpgradeComponents.create(this, s, e, l, f, j);
    }

}