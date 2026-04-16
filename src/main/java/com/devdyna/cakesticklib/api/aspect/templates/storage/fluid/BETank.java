package com.devdyna.cakesticklib.api.aspect.templates.storage.fluid;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.setup.registry.types.zHandlers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

public abstract class BETank extends BlockEntity implements SimpleFluidStorage {

    public static final int DEFAULT_TANK_STORAGE = 16_000;

    public BETank(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public FluidStacksResourceHandler getFluidStorage() {
        return getData(zHandlers.FLUID_STORAGE);
    }

    @Override
    public int getTankCapacity() {
        return DEFAULT_TANK_STORAGE;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        getFluidStorage().serialize(output);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        getFluidStorage().deserialize(input);
        super.loadAdditional(input);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

}
