package com.devdyna.cakesticklib.api.aspect.logic;

public interface EnvironmentModifier {
    abstract float getSpeedModifier();

    abstract boolean isRequired();

    default String failDescKey() {
        return "";//TODO IMP : when defaul fall on errored jade
    }
}