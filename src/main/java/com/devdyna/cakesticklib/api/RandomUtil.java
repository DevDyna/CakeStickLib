package com.devdyna.cakesticklib.api;

import net.minecraft.world.level.Level;

public class RandomUtil {

    /**
     * @param chance must be inside {@code [ 0 <-> 100 ]}
     */
    public static boolean chance(Level level, int chance) {
        return level.getRandom().nextInt(100) < chance;
    }

    /**
     * @param chance must be inside {@code [ 0.0f <-> 1.0f ]}
     */
    public static boolean chance(Level level, double chance) {
        return level.getRandom().nextDouble() < chance;
    }

    public static boolean rnd50(Level level) {
        return level.getRandom().nextBoolean();
    }

    public static double between(Level l, double min, double max) {
        return min + l.getRandom().nextDouble() * (max - min);
    }
}
