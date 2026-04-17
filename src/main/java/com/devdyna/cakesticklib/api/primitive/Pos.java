package com.devdyna.cakesticklib.api.primitive;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
/**
 * Similar to <code>net.minecraft.core.BlockPos</code> but specialized on element inclusion
 */
public class Pos {
    
    private int x0;
    private int y0;
    private int x1 = 0;
    private int y1 = 0;

    public Pos(int x, int y) {
        this.x0 = x;
        this.y0 = y;
    }

    public Pos setSize(int x, int y) {
        this.x1 = x0 + x;
        this.y1 = y0 + y;
        return this;
    }

    public int getX0() {
        return x0;
    }

    public int getX1() {
        return x1;
    }

    public int getY0() {
        return y0;
    }

    public int getY1() {
        return y1;
    }

    public boolean test(int x, int y) {
        return x0 <= x && x < x1 && y0 <= y && y < y1;
    }

    public boolean test(double x, double y) {
        return test((int)x,(int) y);
    }

    @Deprecated
    public static Pos of(int x0, int y0, int x1, int y1) {
        return new Pos(x0, y0).setSize(x1, y1);
    }

    public static Pos of(int x, int y) {
        return new Pos(x, y);
    }

    public static final Codec<Pos> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.fieldOf("x_start").forGetter(Pos::getX0),
            Codec.INT.fieldOf("y_start").forGetter(Pos::getY0),
            Codec.INT.fieldOf("x_end").forGetter(Pos::getX1),
            Codec.INT.fieldOf("y_end").forGetter(Pos::getY1))
            .apply(inst, (x, y, x0, y0) -> Pos.of(x, y).setSize(x0, y0)));

    public static final StreamCodec<RegistryFriendlyByteBuf, Pos> STREAM_CODEC = StreamCodec
            .composite(
                    ByteBufCodecs.INT, Pos::getX0,
                    ByteBufCodecs.INT, Pos::getY0,
                    ByteBufCodecs.INT, Pos::getX1,
                    ByteBufCodecs.INT, Pos::getY1,
                    (x, y, x0, y0) -> Pos.of(x, y).setSize(x0, y0));

}