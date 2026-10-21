package com.devdyna.cakesticklib.api.aspect.logic;

import com.devdyna.cakesticklib.api.primitive.Ticker;

public interface TickerDelay {

    public enum TimerStyle {
        NONE(),
        PROGRESS_BAR(),
        TEXTUAL_ONLY_NUMBER(),
        TEXTUAL_NUMBER_AND_MULTIPLIER();

        public static boolean isTextual(TimerStyle s) {
            return s == TEXTUAL_ONLY_NUMBER
                    || s == TEXTUAL_NUMBER_AND_MULTIPLIER;
        }

        public static boolean isProgressBar(TimerStyle s) {
            return s == PROGRESS_BAR;
        }

        public static boolean isTextual(TickerDelay s) {
            return isTextual(s.getStyle());
        }

        public static boolean isProgressBar(TickerDelay s) {
            return isProgressBar(s.getStyle());
        }
    }

    abstract Ticker getTicker();

    default float getMultiplier() {
        return 1.0f;
    }

    default boolean hideMultiplier() {
        return getStyle() != TimerStyle.TEXTUAL_NUMBER_AND_MULTIPLIER;
    }

    abstract TimerStyle getStyle();

}
