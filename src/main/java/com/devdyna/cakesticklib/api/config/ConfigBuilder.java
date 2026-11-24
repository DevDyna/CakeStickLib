package com.devdyna.cakesticklib.api.config;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import com.devdyna.cakesticklib.api.datagen.LangGenerators;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.DoubleValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;

//TODO experimental
public class ConfigBuilder {

    private ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private Map<String, String> keys = new HashMap<>();
    private Map<String, String> chapters = new HashMap<>();

    private Type type;

    public ConfigBuilder(ModConfig.Type type) {
        BUILDER = new ModConfigSpec.Builder();
        this.type = type;
    }

    public ConfigBuilder push(String key, String display) {
        chapters.put(key, display);
        return this;
    }

    public ConfigBuilder pop() {
        return this;
    }

    /**
     * max = Integer.MAX_VALUE
     */
    public IntValue keyInt(String key, String display, int base, int min) {
        return keyInt(key, display, base, min, Integer.MAX_VALUE);
    }

    /**
     * min = 1<br/>
     * <br/>
     * max = Integer.MAX_VALUE
     */
    public IntValue keyInt(String key, String display, int base) {
        return keyInt(key, display, base, 1);
    }

    public IntValue keyInt(String key, String display, int base, int min, int max) {
        keys.put(key, display);
        return ConfigUtils.num(BUILDER, display, key, base, min, max);
    }

    /**
     * max = Integer.MAX_VALUE
     */
    public DoubleValue keyDouble(String key, String display, double base, double min) {
        return keyDouble(key, display, base, min, Integer.MAX_VALUE);
    }

    /**
     * min = 1<br/>
     * <br/>
     * max = Integer.MAX_VALUE
     */
    public DoubleValue keyDouble(String key, String display, double base) {
        return keyDouble(key, display, base, 1);
    }

    public DoubleValue keyDouble(String key, String display, double base, double min, double max) {
        keys.put(key, display);
        return ConfigUtils.numFloat(BUILDER, display, key, base, min, max);
    }

    /**
     * default = false
     */
    public BooleanValue keyBoolean(String key, String display) {
        return keyBoolean(key, display, false);
    }

    public BooleanValue keyBoolean(String key, String display, boolean value) {
        keys.put(key, display);
        return ConfigUtils.bool(BUILDER, display, key, value);
    }

    public <T> T keyCustom(String key, String display, Function<ModConfigSpec.Builder, T> builder) {
        keys.put(key, display);
        return builder.apply(BUILDER.comment(display));
    }

    public void register(ModContainer c) {
        c.registerConfig(type, BUILDER.build());
    }

    public <T extends LangGenerators> void datagen(T gen) {
        chapters.forEach((k, v) -> gen.addConfigTitle(k, v));
        keys.forEach((k, v) -> gen.addConfigEntry(k, v));
    }
}
