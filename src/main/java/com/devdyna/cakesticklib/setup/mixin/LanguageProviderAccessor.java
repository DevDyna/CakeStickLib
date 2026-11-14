package com.devdyna.cakesticklib.setup.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.neoforged.neoforge.common.data.LanguageProvider;

@Mixin(LanguageProvider.class)
public interface LanguageProviderAccessor {
    @Accessor("modid")
    String getModName();
}
