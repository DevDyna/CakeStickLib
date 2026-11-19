package com.devdyna.cakesticklib.api.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.DoubleValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;

public class ConfigUtils {

    public static BooleanValue bool(ModConfigSpec.Builder b, String c, String k, boolean v) {
        return b.comment(c).define(k, v);
    }

    /**
     * default = false
     */
   public static BooleanValue bool(ModConfigSpec.Builder b, String c, String k) {
        return bool(b, c, k, false);
    }

  public  static IntValue num(ModConfigSpec.Builder b, String c, String k, int d, int min, int max) {
        return b.comment(c).defineInRange(k, d, (d < min ? d : min), (d > max ? d : max));
    }

   public static DoubleValue numFloat(ModConfigSpec.Builder b, String c, String k, double d, double min, double max) {
        return b.comment(c).defineInRange(k, d, (d < min ? d : min), (d > max ? d : max));
    }

    /**
     * min = 0<br/>
     * <br/>
     * max = Double.MAX_VALUE
     */

   public static DoubleValue numFloat(ModConfigSpec.Builder b, String c, String k, double d) {
        return numFloat(b, c, k, d, 0, Integer.MAX_VALUE);
    }

    /**
     * max = Double.MAX_VALUE
     */
   public static DoubleValue numFloat(ModConfigSpec.Builder b, String c, String k, double d, double min) {
        return numFloat(b, c, k, d, min, Integer.MAX_VALUE);
    }

    /**
     * min = 1<br/>
     * <br/>
     * max = Integer.MAX_VALUE
     */
  public  static IntValue num(ModConfigSpec.Builder b, String c, String k, int d) {
        return num(b, c, k, d, 1, Integer.MAX_VALUE);
    }

    /**
     * max = Integer.MAX_VALUE
     */
  public static IntValue num(ModConfigSpec.Builder b, String c, String k, int d, int min) {
        return num(b, c, k, d, min, Integer.MAX_VALUE);
    }
}
