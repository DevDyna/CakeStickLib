package com.devdyna.cakesticklib.setup;

import com.devdyna.cakesticklib.api.utils.ModAddonUtil;
import com.devdyna.cakesticklib.api.utils.StringUtil;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.*;

@SuppressWarnings("unused")
public class Config {

    public static BooleanValue HARVESTABLE_ACTION;
    public static IntValue TREE_CUTTING_LIMIT;
    public static BooleanValue ENDER_EYE_RETURN_EVENT;
    public static BooleanValue PATINA_DROP_EVENT;
    public static BooleanValue REDSTONE_ACID_EVENT;
    public static BooleanValue HONEY_SOLUTION_EVENT;

    private static final ModConfigSpec.Builder qCOMMON = new ModConfigSpec.Builder();

    public static void register(ModContainer c) {

        qCOMMON.comment("Gameplay Additions").push("events");

        HARVESTABLE_ACTION = bool(
                "Player can right-click on crops to collect intend of breaking" +
                        "\nIf detected Farmers Delight it will turn off by default",
                "harvestable_action",
                !ModAddonUtil.checkMod("farmersdelight"));

        TREE_CUTTING_LIMIT = number(
                "Max number of harvestable blocks foreach interaction",
                "tree_cutting_limit", 2048);

        ENDER_EYE_RETURN_EVENT = bool("End Portal Frame can be interacted to remove Eye of Ender",
                "ender_eye_return",true);

        PATINA_DROP_EVENT = bool("Patina will drop when scrapped any oxidized copper block",
                "patina_drop",true);

        REDSTONE_ACID_EVENT = bool(
                "Redstone Acid can be used to increase a stage of oxidation at the copper block clicked",
                "redstone_acid_oxide",true);

        HONEY_SOLUTION_EVENT = bool("Honey Solution can be used to wax copper blocks clicked",
                "honey_solution_wax",true);

        qCOMMON.pop();

        c.registerConfig(ModConfig.Type.COMMON, qCOMMON.build());
    }

    private static BooleanValue bool(String c, String k, boolean b) {
        return qCOMMON
                .comment(c)
                .define(k, b);
    }

    /**
     * default = false
     */
    private static BooleanValue bool(String c, String k) {
        return bool(c, k, false);
    }

    private static IntValue number(String c, String k, int d, int mn, int mx) {
        return qCOMMON
                .comment(c)
                .defineInRange(k, d, mn, mx);
    }

    private static DoubleValue numberFloat(String c, String k, double d, double min, double max) {
        return qCOMMON
                .comment(c)
                .defineInRange(k, d, min, max);
    }

    /**
     * min = 0<br/>
     * <br/>
     * max = Double.MAX_VALUE
     */

    private static DoubleValue numberFloat(String c, String k, double d) {
        return numberFloat(c, k, d, 0, Integer.MAX_VALUE);
    }

    /**
     * max = Double.MAX_VALUE
     */
    private static DoubleValue numberFloat(String c, String k, double d, double min) {
        return numberFloat(c, k, d, min, Integer.MAX_VALUE);
    }

    /**
     * min = 1<br/>
     * <br/>
     * max = Integer.MAX_VALUE
     */
    private static IntValue number(String c, String k, int d) {
        return number(c, k, d, 1, Integer.MAX_VALUE);
    }

    /**
     * max = Integer.MAX_VALUE
     */
    private static IntValue number(String c, String k, int d, int min) {
        return number(c, k, d, min, Integer.MAX_VALUE);
    }

    protected class decor {
        protected static void complex(String s) {
            qCOMMON.comment(StringUtil.nameCapitalized(s));
        }

        protected static void simple(String s) {
            qCOMMON.comment(s);
        }
    }

}