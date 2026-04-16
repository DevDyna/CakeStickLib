package com.devdyna.cakesticklib.api.aspect.templates;

import java.util.List;

import com.devdyna.cakesticklib.api.aspect.logic.AreaOfEffect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class TickingBE extends BlockEntity {

    public final static String WIDTH = "width";
    public final static String HEIGHT = "height";

    protected int width;
    protected int height;

    public TickingBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);

        if (this instanceof AreaOfEffect be) {
            this.width = be.getWidth();
            this.height = be.getHeight();
        }

    }

    /**
     * Server only ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Useful for block events
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickServer() {
    }

    /**
     * Client only ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Useful for player events
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickClient() {
    }

    /**
     * Client and Server ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Usefull for particles
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickBoth() {
    }

    // required to sync client to server data
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    /**
     * update this specific BE
     */
    public void update() {
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    protected List<BlockPos> area = null;

    @Override
    protected void loadAdditional(ValueInput input) {

        if (this instanceof AreaOfEffect) {
            if (input.getInt(HEIGHT).isPresent())
                height = input.getInt(HEIGHT).get();
            if (input.getInt(WIDTH).isPresent())
                width = input.getInt(WIDTH).get();
        }

        super.loadAdditional(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        if (this instanceof AreaOfEffect) {
            output.putInt(HEIGHT, height);
            output.putInt(WIDTH, width);
        }
        super.saveAdditional(output);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (this instanceof AreaOfEffect)
            if (level.isClientSide())
                rebuildArea();
    }

    private void rebuildArea() {
        if (this instanceof AreaOfEffect be)
            if (level != null)
                area = be.getArea();
    }

    @Override
    public CompoundTag getUpdateTag(Provider lookupProvider) {
        CompoundTag tag = super.getUpdateTag(lookupProvider);
        if (this instanceof AreaOfEffect) {
            tag.putInt(HEIGHT, height);
            tag.putInt(WIDTH, width);
        }
        return tag;
    }

    @Override
    public void handleUpdateTag(ValueInput input) {
        super.handleUpdateTag(input);
        if (this instanceof AreaOfEffect) {
            if (input.getInt(HEIGHT).isPresent())
                height = input.getInt(HEIGHT).get();
            if (input.getInt(WIDTH).isPresent())
                width = input.getInt(WIDTH).get();
            rebuildArea();
        }

    }

    public void resetAOE() {
        if (this instanceof AreaOfEffect)
            area = null;
    }

}
