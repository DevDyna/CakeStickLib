package com.devdyna.cakesticklib.setup.registry;

import java.util.function.Supplier;

import com.devdyna.cakesticklib.CakeStickLib;
import com.devdyna.cakesticklib.api.FluidStorageTank;
import com.devdyna.cakesticklib.api.aspect.logic.EnergyBlock;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.MachineItemAutomation;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class zHandlers {
                public static void register(IEventBus bus) {
                        zHandler.register(bus);
                }

                public static final DeferredRegister<AttachmentType<?>> zHandler = DeferredRegister.create(
                                Keys.ATTACHMENT_TYPES,
                                CakeStickLib.MODULE_ID);

                public static final Supplier<AttachmentType<ItemStacksResourceHandler>> ITEM_STORAGE = zHandler
                                .register(
                                                "item_storage", () -> AttachmentType.serializable(h -> {
                                                        if (h instanceof ItemStorageBlock be)
                                                                return new ItemStacksResourceHandler(be.getSlots());
                                                        if (h instanceof MachineItemAutomation be)
                                                                return be.getAutomationItemStorage();
                                                        return null;
                                                }).build());

                public static final Supplier<AttachmentType<FluidStorageTank>> FLUID_STORAGE = zHandler.register(
                                "fluid_storage", () -> AttachmentType.serializable(h -> {
                                        // if (h instanceof MachineFluidAutomation be)
                                        // return new FluidStorageTank((BlockEntity) be, be.getTanks(),
                                        // be.getTankCapacity());
                                        if (h instanceof SimpleFluidStorage be)
                                                return new FluidStorageTank((BlockEntity) be, be.getTanks(),
                                                                be.getTankCapacity());
                                        return null;
                                }).build());

                public static final Supplier<AttachmentType<SimpleEnergyHandler>> ENERGY_STORAGE = zHandler.register(
                                "energy_storage", () -> AttachmentType.serializable(h -> {
                                        if (h instanceof EnergyBlock be)
                                                return new SimpleEnergyHandler(be.getMaxEnergy(),
                                                                be.getMaxInsertEnergy(),
                                                                be.getMaxExtractEnergy(), be.getBaseEnergyStored());
                                        return null;
                                }).build());

        }
