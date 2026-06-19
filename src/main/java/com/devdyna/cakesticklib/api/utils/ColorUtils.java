package com.devdyna.cakesticklib.api.utils;

import net.minecraft.world.level.Level;

import java.awt.Color;

public class ColorUtils {

    private static final long TICKS_PER_SECOND = 20L;
    private static final long MILLIS_PER_SECOND = 1000L;

    public static final Color BLACK = Color.BLACK;
    public static final Color BLUE = Color.BLUE;
    public static final Color CYAN = Color.CYAN;
    public static final Color DARK_GRAY = Color.DARK_GRAY;
    public static final Color GRAY = Color.GRAY;
    public static final Color GREEN = Color.GREEN;
    public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
    public static final Color MAGENTA = Color.MAGENTA;
    public static final Color ORANGE = Color.ORANGE;
    public static final Color PINK = Color.PINK;
    public static final Color RED = Color.RED;
    public static final Color WHITE = Color.WHITE;
    public static final Color YELLOW = Color.YELLOW;

    // TODO verify if they respect color names

    public static final Color PURPLE = color(128, 0, 128);
    public static final Color VIOLET = color(238, 130, 238);
    public static final Color INDIGO = color(75, 0, 130);

    public static final Color LIME = color(0, 255, 0);
    public static final Color TEAL = color(0, 128, 128);
    public static final Color TURQUOISE = color(64, 224, 208);

    public static final Color NAVY = color(0, 0, 128);
    public static final Color SKY_BLUE = color(135, 206, 235);

    public static final Color BROWN = color(165, 42, 42);
    public static final Color TAN = color(210, 180, 140);

    public static final Color OLIVE = color(128, 128, 0);
    public static final Color GOLD = color(255, 215, 0);

    public static final Color SILVER = color(192, 192, 192);
    public static final Color MAROON = color(128, 0, 0);

    public static final Color CRIMSON = color(220, 20, 60);
    public static final Color FIREBRICK = color(178, 34, 34);
    public static final Color SALMON = color(250, 128, 114);
    public static final Color CORAL = color(255, 127, 80);

    public static final Color DARK_ORANGE = color(255, 140, 0);
    public static final Color PEACH = color(255, 218, 185);

    public static final Color KHAKI = color(240, 230, 140);
    public static final Color LEMON = color(255, 250, 205);

    public static final Color FOREST_GREEN = color(34, 139, 34);
    public static final Color LIME_GREEN = color(50, 205, 50);
    public static final Color SEA_GREEN = color(46, 139, 87);
    public static final Color MINT = color(189, 252, 201);
    public static final Color SPRING_GREEN = color(0, 255, 127);

    public static final Color AQUA = color(0, 255, 255);
    public static final Color AQUAMARINE = color(127, 255, 212);

    public static final Color DODGER_BLUE = color(30, 144, 255);
    public static final Color DEEP_SKY_BLUE = color(0, 191, 255);
    public static final Color STEEL_BLUE = color(70, 130, 180);
    public static final Color ROYAL_BLUE = color(65, 105, 225);
    public static final Color MIDNIGHT_BLUE = color(25, 25, 112);

    public static final Color PLUM = color(221, 160, 221);
    public static final Color ORCHID = color(218, 112, 214);
    public static final Color MEDIUM_PURPLE = color(147, 112, 219);
    public static final Color DARK_VIOLET = color(148, 0, 211);

    public static final Color SADDLE_BROWN = color(139, 69, 19);
    public static final Color CHOCOLATE = color(210, 105, 30);
    public static final Color PERU = color(205, 133, 63);
    public static final Color SANDY_BROWN = color(244, 164, 96);

    public static final Color IVORY = color(255, 255, 240);
    public static final Color BEIGE = color(245, 245, 220);
    public static final Color CREAM = color(255, 253, 208);
    public static final Color SNOW = color(255, 250, 250);

    public static final Color DIM_GRAY = color(105, 105, 105);
    public static final Color SLATE_GRAY = color(112, 128, 144);
    public static final Color GAINSBORO = color(220, 220, 220);

    public static final Color BRONZE = color(205, 127, 50);
    public static final Color COPPER = color(184, 115, 51);

    public static final Color HOT_PINK = color(255, 105, 180);
    public static final Color DEEP_PINK = color(255, 20, 147);
    public static final Color LIGHT_PINK = color(255, 182, 193);
    public static final Color ROSE = color(255, 0, 127);

    public static final Color INDIAN_RED = color(205, 92, 92);
    public static final Color TOMATO = color(255, 99, 71);

    public static final Color ORANGE_RED = color(255, 69, 0);
    public static final Color GOLDENROD = color(218, 165, 32);
    public static final Color DARK_GOLDENROD = color(184, 134, 11);

    public static final Color DARK_GREEN = color(0, 100, 0);
    public static final Color OLIVE_DRAB = color(107, 142, 35);
    public static final Color YELLOW_GREEN = color(154, 205, 50);
    public static final Color LAWN_GREEN = color(124, 252, 0);
    public static final Color PALE_GREEN = color(152, 251, 152);
    public static final Color DARK_SEA_GREEN = color(143, 188, 143);

    public static final Color LIGHT_SEA_GREEN = color(32, 178, 170);
    public static final Color DARK_CYAN = color(0, 139, 139);

    public static final Color CORNFLOWER_BLUE = color(100, 149, 237);
    public static final Color CADET_BLUE = color(95, 158, 160);
    public static final Color POWDER_BLUE = color(176, 224, 230);
    public static final Color LIGHT_BLUE = color(173, 216, 230);
    public static final Color LIGHT_SKY_BLUE = color(135, 206, 250);
    public static final Color DARK_BLUE = color(0, 0, 139);

    public static final Color BLUE_VIOLET = color(138, 43, 226);
    public static final Color MEDIUM_ORCHID = color(186, 85, 211);
    public static final Color THISTLE = color(216, 191, 216);
    public static final Color LAVENDER = color(230, 230, 250);

    public static final Color ROSY_BROWN = color(188, 143, 143);
    public static final Color BURLYWOOD = color(222, 184, 135);
    public static final Color WHEAT = color(245, 222, 179);

    public static final Color ALICE_BLUE = color(240, 248, 255);
    public static final Color ANTIQUE_WHITE = color(250, 235, 215);
    public static final Color FLORAL_WHITE = color(255, 250, 240);
    public static final Color GHOST_WHITE = color(248, 248, 255);
    public static final Color HONEYDEW = color(240, 255, 240);
    public static final Color LAVENDER_BLUSH = color(255, 240, 245);

    public static final Color DARK_SLATE_GRAY = color(47, 79, 79);
    public static final Color LIGHT_SLATE_GRAY = color(119, 136, 153);
    public static final Color SILVER_GRAY = color(192, 192, 192);

    public static final Color CHARCOAL = color(54, 69, 79);
    public static final Color AMBER = color(255, 191, 0);
    public static final Color EMERALD = color(80, 200, 120);
    public static final Color RUBY = color(224, 17, 95);
    public static final Color SAPPHIRE = color(15, 82, 186);

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