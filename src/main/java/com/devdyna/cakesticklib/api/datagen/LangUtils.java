package com.devdyna.cakesticklib.api.datagen;

import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class LangUtils {

    /**
     * **Warning:**
     * <br/><br/>This variable use a Mojang deprecated code to work!
     */
    public static final String TIP_COLOR = "§7";

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

    public static void advKey(LanguageProvider l,String modid,String k, String title, String desc) {
        l.add(modid + ".advancement.branch." + k, title);
        l.add(modid + ".advancement.branch." + k + ".desc", desc);
    }

}
