package com.devdyna.cakesticklib.api.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;

public class AdvancementsUtils {
    public static Advancement.Builder getExistingParent(AdvancementHolder parent, ItemLike icon, String modid,
            String txt,
            AdvancementType type, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(icon,
                Component.translatable(modid + ".advancement.branch." + txt),
                Component.translatable(modid + ".advancement.branch." + txt + ".desc"),
                null, type, showToast, announceToChat, hidden);
    }

    public static Advancement.Builder getExistingParent(String parent, ItemLike icon, String modid, String txt,
            AdvancementType type, boolean showToast, boolean announceToChat, boolean hidden) {
        return getExistingParent(AdvancementSubProvider.createPlaceholder(parent), icon, modid, txt, type, showToast,
                announceToChat, hidden);
    }
}
