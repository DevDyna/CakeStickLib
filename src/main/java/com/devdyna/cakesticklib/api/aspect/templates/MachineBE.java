package com.devdyna.cakesticklib.api.aspect.templates;

import java.util.List;

import com.devdyna.cakesticklib.api.aspect.logic.AreaOfEffect;
import com.devdyna.cakesticklib.api.aspect.logic.EnergyBlock;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.aspect.templates.storage.item.BEStorage;

import net.minecraft.core.HolderLookup.Provider;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

/**
 * <b>STANDALONE BASE BE</b>
 * <br/>
 * <br/>
 * Base BE storage with menu and a custom handler to filter multiple slots on
 * automation handling
 * <br/>
 * <br/>
 * This Base BE is inspired from
 * <code>com.devdyna.synergy.api.machine.BaseMachineBE</code> to be used to
 * create simple-complex machines
 * <br/>
 * <br/>
 * |-----------------------------------------------------------------|<br/>
 * <br/>
 * <br/>
 * credit: @DevDyna
 */
public abstract class MachineBE extends BEStorage {

    public final static String ENERGY = "energy";

    public final static String WIDTH = "width";
    public final static String HEIGHT = "height";

    protected int width;
    protected int height;
    protected List<BlockPos> area = null;

    protected EnergyHandler energyStorage;
    protected FluidStacksResourceHandler fluid_tank;

    public MachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        if (this instanceof AreaOfEffect be) {
            this.width = be.getWidth();
            this.height = be.getHeight();
        }

        if (this instanceof EnergyBlock be) 
            this.energyStorage = be.getEnergyStorage();
        

        if (this instanceof SimpleFluidStorage be) 
            this.fluid_tank = be.getFluidStorage();
        
    }

    /**
     * Server only ticking
     * Useful for block events
     */
    public void tickServer() {
    }

    /**
     * Client only ticking
     * Useful for player events
     */
    public void tickClient() {
    }

    /**
     * Client and Server ticking
     * 
     * Usefull for particles
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

    @Override
    protected void loadAdditional(ValueInput input) {

        if (this instanceof AreaOfEffect) {
            if (input.getInt(HEIGHT).isPresent())
                height = input.getInt(HEIGHT).get();
            if (input.getInt(WIDTH).isPresent())
                width = input.getInt(WIDTH).get();
        }

        if (this instanceof EnergyBlock) 
            if (input.getInt(ENERGY).isPresent())
                try (var tx = Transaction.openRoot()) {
                    energyStorage.insert(Math.min(input.getInt(ENERGY).get(), energyStorage.getCapacityAsInt()), tx);
                    tx.commit();
                }

        if(this instanceof SimpleFluidStorage f)
        f.getFluidStorage().deserialize(input);

        super.loadAdditional(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        if (this instanceof AreaOfEffect) {
            output.putInt(HEIGHT, height);
            output.putInt(WIDTH, width);
        }

        if (this instanceof EnergyBlock) 
            output.putInt("energy", energyStorage.getAmountAsInt());
        
        if(this instanceof SimpleFluidStorage f)
        f.getFluidStorage().serialize(output);

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
