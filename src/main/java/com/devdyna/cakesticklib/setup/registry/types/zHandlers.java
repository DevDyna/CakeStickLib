package com.devdyna.cakesticklib.setup.registry.types;

import static com.devdyna.cakesticklib.Main.MODULE_ID;

import java.util.function.Supplier;

import com.devdyna.cakesticklib.api.aspect.logic.EnergyBlock;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class zHandlers {
    public static void register(IEventBus bus) {
        zHandler.register(bus);
    }

    // ---------------------------------------------------------------------------------------//

    public static final DeferredRegister<AttachmentType<?>> zHandler = DeferredRegister.create(
            Keys.ATTACHMENT_TYPES,
            MODULE_ID);

    public static final Supplier<AttachmentType<ItemStacksResourceHandler>> ITEM_STORAGE = zHandler.register(
            "item_storage", () -> AttachmentType.serializable(h -> {
                if (h instanceof ItemStorageBlock be)
                    return new ItemStacksResourceHandler(be.getSlots());
                return null;
            }).build());

    public static final Supplier<AttachmentType<FluidStacksResourceHandler>> FLUID_STORAGE = zHandler.register(
            "fluid_storage", () -> AttachmentType.serializable(h -> {
                if (h instanceof SimpleFluidStorage be)
                    return new FluidStacksResourceHandler(be.getTanks(), be.getTankCapacity());
                return null;
            }).build());

    public static final Supplier<AttachmentType<SimpleEnergyHandler>> ENERGY_STORAGE = zHandler.register(
            "energy_storage", () -> AttachmentType.serializable(h -> {
                if (h instanceof EnergyBlock be)
                    return new SimpleEnergyHandler(be.getMaxEnergy(),be.getMaxInsertEnergy(),be.getMaxExtractEnergy(),be.getBaseEnergyStored());
                return null;
            }).build());

}