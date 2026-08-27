package com.devdyna.cakesticklib.setup.registry.builders;

import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
@Deprecated
public class IndustrialUpgrade extends Item {

    public IndustrialUpgrade(Properties properties) {
        super(properties);
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
    public ItemStack set(int s, int e, int l, int f, Direction j) {
        return UpgradeComponents.create(this, s, e, l, f, j);
    }

}