package com.devdyna.cakesticklib.api.primitive;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

/**
 * Mainly useful on AOE logics or cubic math
 */
public class Area {

    private Range xz;
    private Range y;

    public Area(Range xz, Range y) {
        this.xz = xz;
        this.y = y;
    }

    public Range getXZ() {
        return xz;
    }

    public Range getY() {
        return y;
    }

    public int getMinXZ() {
        return xz.getMin();
    }

    public int getMaxXZ() {
        return xz.getMax();
    }

    public int getMinY() {
        return y.getMin();
    }

    public int getMaxY() {
        return y.getMax();
    }

    public boolean test(int xz, int y) {
        return this.xz.test(xz) && this.y.test(y);
    }

    public static Area of(Range xz, Range y) {
        return new Area(xz, y);
    }

    public static Area of(int xzMn, int xzMx, int yMn, int yMx) {
        return of(Range.of(xzMn, xzMx), Range.of(yMn, yMx));
    }

    /**
     * 
     * @return Area from 0 -> xzr|yr
     */
    public static Area of(int xzr, int yr) {
        return of(Range.of(0, xzr), Range.of(0, yr));
    }


     public static final Codec<Area> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Range.CODEC.fieldOf("xz").forGetter(Area::getXZ),
            Range.CODEC.fieldOf("y").forGetter(Area::getY))
            .apply(inst, Area::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Area> STREAM_CODEC = StreamCodec
            .composite(
                    Range.STREAM_CODEC, Area::getXZ,
                    Range.STREAM_CODEC, Area::getY,
                    Area::new);

}
