package net.earthcomputer.multiconnect.protocols.v1_13_2;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class UseBedS2CPacket implements Packet<ClientPlayNetworkHandler> {
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final int playerId;
    private final BlockPos bedPos;

    public UseBedS2CPacket(PacketByteBuf buf) {
        playerId = buf.readVarInt();
        bedPos = buf.readBlockPos();
    }

    @Override
    public void write(PacketByteBuf buf) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void apply(ClientPlayNetworkHandler handler) {
        NetworkThreadUtils.forceMainThread(this, handler, this.client);
        ClientWorld world = this.client.world;
        assert world != null;
        Entity entity = world.getEntityById(playerId);
        if (entity instanceof PlayerEntity player) {
            player.trySleep(bedPos);
        }
    }
}
