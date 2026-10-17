package com.devdyna.cakesticklib.api.templates;

import java.util.Objects;

import com.devdyna.cakesticklib.api.aspect.logic.*;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;

/**
 * Utility BE that will sync packets to clients
 */
public abstract class SynchedBlockEntity extends BlockEntity implements SynchedClient {

    public SynchedBlockEntity(BlockEntityType<?> t, BlockPos p, BlockState s) {
        super(t, p, s);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider p) {
        return saveWithoutMetadata(p);
    }

    @Override
    public void handleUpdateTag(ValueInput i) {
        loadAdditional(i);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.setChanged();
        update();
    }

    @Override
    public void update() {
        if (level instanceof ServerLevel server) {
            var chunk = server.getChunkAt(getBlockPos());
            if (Objects.requireNonNull(chunk.getLevel()).getChunkSource() instanceof ServerChunkCache cache)
                cache.chunkMap.getPlayers(chunk.getPos(), false)
                        .forEach(p -> p.connection.send(Objects.requireNonNull(getUpdatePacket())));
        }
    }

    @Override
    public void onDataPacket(Connection c, ValueInput i) {
        super.onDataPacket(c, i);
        loadAdditional(i);
    }

}
