package com.devdyna.cakesticklib.api.primitive;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import com.mojang.serialization.codecs.RecordCodecBuilder;

import com.mojang.serialization.Codec;

/**
 * Mainly useful on 2D elements size definition
 */
public class Size {

  private int x;
  private int y;

  public Size(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Size of(int x, int y) {
    return new Size(x, y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  /**
   * @return true when the size is inside
   */
  public boolean test(int x0, int y0) {
    return x >= x0 && y >= y0;
  }

  /**
   * @return true when the size is inside
   */
  public boolean test(Size s) {
    return x >= s.getX() && y >= s.getY();
  }


public static final Codec<Size> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.fieldOf("min").forGetter(Size::getX),
            Codec.INT.fieldOf("max").forGetter(Size::getY))
            .apply(inst, Size::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Size> STREAM_CODEC = StreamCodec
            .composite(
                    ByteBufCodecs.INT, Size::getX,
                    ByteBufCodecs.INT, Size::getY,
                    Size::new);

}