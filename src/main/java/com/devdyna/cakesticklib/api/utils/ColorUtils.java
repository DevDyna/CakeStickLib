package com.devdyna.cakesticklib.api.utils;

import net.minecraft.world.level.Level;

import java.awt.Color;

public class ColorUtils {

    private static final long TICKS_PER_SECOND = 20L;
    private static final long MILLIS_PER_SECOND = 1000L;

    public static Color color(int r, int g, int b) {
        return new Color(r, g, b);
    }

    public static Color color(float r, float g, float b) {
        return new Color(r, g, b);
    }

    public static Color color(float r, float g, float b, float a) {
        return new Color(r, g, b, a);
    }

    public static Color color(int rgb) {
        return new Color(rgb);
    }

    public static Color color(int r, int g, int b, int a) {
        return new Color(r, g, b, a);
    }

    public static int rgb(Color color) {
        return (color.getRed() << 16)
                | (color.getGreen() << 8)
                | color.getBlue();
    }

    public static int argb(Color color) {
        return (color.getAlpha() << 24)
                | (color.getRed() << 16)
                | (color.getGreen() << 8)
                | color.getBlue();
    }

    public static int rgb(int r, int g, int b) {
        return (r << 16)
                | (g << 8)
                | b;
    }

    public static int argb(int a, int r, int g, int b) {
        return (a << 24)
                | (r << 16)
                | (g << 8)
                | b;
    }

    public static Color pulse(Color a, Color b, int milli) {
        return pulse(a, b, milli * MILLIS_PER_SECOND);
    }

    public static Color pulse(Color a, Color b, int ticks, Level level) {
        return pulse(a, b, ticks * TICKS_PER_SECOND, level);
    }

    public static Color pulse(Color colorA, Color colorB, long milli) {

        return interpolate(colorA, colorB, oscillate((System.currentTimeMillis() % milli) / milli));
    }

    public static Color pulse(Color colorA, Color colorB, long ticks, Level level) {
        return interpolate(colorA, colorB, oscillate((level.getGameTime() % ticks) / ticks));
    }

    public static Color rainbow() {
        return Color.getHSBColor((System.currentTimeMillis() % 5000L) / 5000F, 1F, 1F);
    }

    public static Color rainbow(Level level) {
        return Color.getHSBColor((level.getGameTime() % 200L) / 200F, 1F, 1F);
    }

    private static double oscillate(double v) {
        return 0.5D * (1D + Math.sin((v * 2D * Math.PI) - (Math.PI / 2D)));
    }

    private static Color interpolate(Color a, Color b, double factor) {
        var fixed = Math.max(0D, Math.min(1D, factor));
        return new Color(
                (int) (a.getRed() + (b.getRed() - a.getRed()) * fixed),
                (int) (a.getGreen() + (b.getGreen() - a.getGreen()) * fixed),
                (int) (a.getBlue() + (b.getBlue() - a.getBlue()) * fixed),
                (int) (a.getAlpha() + (b.getAlpha() - a.getAlpha()) * fixed));
    }
}