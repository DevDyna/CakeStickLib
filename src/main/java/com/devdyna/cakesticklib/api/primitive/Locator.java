package com.devdyna.cakesticklib.api.primitive;

import static com.devdyna.cakesticklib.Main.MODULE_ID;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
/**
 * Originally made to replace <code>com.devdyna.cakesticklib.api.utils.x.rl(String)</code> but reverted due pointless
 */
@Deprecated
public class Locator {

    private String modid = MODULE_ID;
    private String path = "";

    protected Locator(String modid) {
        this.modid = modid;
    }

    public static Locator of(String modid) {
        return new Locator(modid);
    }
    public static Locator of() {
        return new Locator(MODULE_ID);
    }

    public Locator path(String s) {
        this.path = s;
        return this;
    }
    public Locator modid(String m) {
        this.modid = m;
        return this;
    }

    public Identifier rl() {
        return Identifier.fromNamespaceAndPath(modid, path);
    }

    public boolean hasPath() {
        return path.isBlank() || path.isEmpty() || path == "";
    }

    public boolean isDefaultModID() {
        return path.equals(MODULE_ID);
    }

    public String getModid() {
        return modid;
    }

    public String getPath() {
        return path;
    }


    public static final Codec<Identifier> CODEC = Identifier.CODEC;

    public static final StreamCodec<ByteBuf,Identifier> STREAM_CODEC = Identifier.STREAM_CODEC;

}