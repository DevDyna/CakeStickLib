package com.devdyna.cakesticklib.api.aspect.logic;

@Deprecated
public interface EnvironmentModifier {
    abstract float getSpeedModifier();

    abstract boolean isRequired();

    default String failDescKey() {
        return "";
    }
}