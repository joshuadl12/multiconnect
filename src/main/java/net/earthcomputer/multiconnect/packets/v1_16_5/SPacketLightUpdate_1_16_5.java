package net.earthcomputer.multiconnect.packets.v1_16_5;

import net.earthcomputer.multiconnect.ap.Argument;
import net.earthcomputer.multiconnect.ap.Length;
import net.earthcomputer.multiconnect.ap.Message;

@Message
public class SPacketLightUpdate_1_16_5 {
    public int chunkX;
    public int chunkZ;
    public boolean trustEdges;
    public int skyLightMask;
    public int blockLightMask;
    public int emptySkyLightMask;
    public int emptyBlockLightMask;
    @Length(compute = "computeSkyLightArraysLength")
    public byte[][] skyLightArrays;
    @Length(compute = "computeBlockLightArraysLength")
    public byte[][] blockLightArrays;

    public static int computeSkyLightArraysLength(@Argument("skyLightMask") int skyLightMask) {
        return Integer.bitCount(skyLightMask & 0x3ffff);
    }

    public static int computeBlockLightArraysLength(@Argument("blockLightMask") int blockLightMask) {
        return Integer.bitCount(blockLightMask & 0x3ffff);
    }
}
