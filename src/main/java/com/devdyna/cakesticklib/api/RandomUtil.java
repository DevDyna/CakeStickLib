package com.devdyna.cakesticklib.api;

import net.minecraft.world.level.Level;

public class RandomUtil {
    public static boolean chance(Level level, int chance) {
        return level.getRandom().nextInt(100) < chance;
    }

    public static boolean rnd50(Level level) {
        return level.getRandom().nextBoolean();
    }
}
