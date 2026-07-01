package com.devdyna.cakesticklib.api.datagen;

import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class LangUtils {

    public class TipColors {

        public static final String BLACK = "§0";
        public static final String DARK_BLUE = "§1";
        public static final String DARK_GREEN = "§2";
        public static final String CYAN = "§3";
        public static final String DARK_RED = "§4";
        public static final String PURPLE = "§5";
        public static final String GOLD = "§6";
        public static final String LIGHT_GRAY = "§7";
        public static final String GRAY = "§8";
        public static final String BLUE = "§9";
        public static final String GREEN = "§a";
        public static final String LIGHT_BLUE = "§b";
        public static final String RED = "§c";
        public static final String MAGENTA = "§d";
        public static final String YELLOW = "§e";
        public static final String WHITE = "§f";

        public static final String OBFUSCATED = "§k";
        public static final String BOLD = "§l";
        public static final String STRIKETHROUGH = "§m";
        public static final String UNDERLINE = "§n";
        public static final String ITALIC = "§o";
        public static final String RESET = "§r";

        public static final String REGISTRY_ID = GRAY;
        public static final String MOD_DISPLAY_NAME = BLUE;
        public static final String TOOL_STAT_TOOLTIP = DARK_GREEN;

        public static final String RARITY_COMMON = WHITE;
        public static final String RARITY_UNCOMMON = YELLOW;
        public static final String RARITY_RARE = LIGHT_BLUE;
        public static final String RARITY_EPIC = MAGENTA;

        /*
         * This color include :
         * - Goat Horns
         * - Firework rockets
         * - Music Discs
         * - Bundles
         * - When on <?>
         * - Enchantments
         * - Smithing Templates
         */
        public static final String ITEM_TOOLTIP = LIGHT_GRAY;

        //spawner
        public static final String INTERACTION_TOOLTIP = BLUE;

        public static final String POTION_EFFECT_NO_EFFECT = LIGHT_GRAY;
        public static final String POTION_EFFECT_POSITIVE = BLUE;
        public static final String POTION_EFFECT_NEGATIVE = RED;

        public static final String POTION_APPLIED_TOOLTIP = PURPLE;

        public class Modifiers {

            public static final String POSITIVE = GREEN;
            public static final String NEGATIVE = RED;
            public static final String NEUTRAL = YELLOW;

        }

    }

    // TODO REMOVE
    // replaced by Colors.ITEM_TOOLTIP
    @Deprecated
    public static final String TIP_COLOR = TipColors.ITEM_TOOLTIP;

    public static String named(DeferredHolder<?, ?> b, String modid) {

        StringBuilder result = new StringBuilder();
        for (String word : b.getRegisteredName()
                .replace(modid + ":", "")
                .replaceAll("_", " ")
                .split(" "))
            if (!word.isEmpty())
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
        return result.toString().trim();
    }

    public static void advKey(LanguageProvider l, String modid, String k, String title, String desc) {
        l.add(modid + ".advancement.branch." + k, title);
        l.add(modid + ".advancement.branch." + k + ".desc", desc);
    }

}
