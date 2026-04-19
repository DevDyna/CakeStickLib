package com.devdyna.cakesticklib.setup.registry;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.function.Supplier;

import com.devdyna.cakesticklib.CakeStickLib;
import com.devdyna.cakesticklib.api.RegistryUtils;
import com.devdyna.cakesticklib.api.aspect.logic.EnergyBlock;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.utils.UpgradeComponents;
import com.devdyna.cakesticklib.setup.RecipeRegister;
import com.devdyna.cakesticklib.setup.common.recipes.oxidation.CopperOxidationRecipe;
import com.devdyna.cakesticklib.setup.registry.builders.CakeStick;
import com.devdyna.cakesticklib.setup.registry.builders.Chisel;
import com.devdyna.cakesticklib.setup.registry.builders.HoneySolution;
import com.devdyna.cakesticklib.setup.registry.builders.RedstoneAcid;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class zLibrary {

    /**
     * DON'T OVERRIDE OR IT WILL LOSE ANY RECIPE TYPES AND HANDLERS!
     */
    public static void register(IEventBus bus) {
        zHandlers.register(bus);
        zItems.register(bus);
        zComponents.register(bus);
        zRecipeTypes.register(bus);
    }

    public class zComponents {
        public static void register(IEventBus bus) {
            zComponents.register(bus);

        }
        // ---------------------------------------------------------------------------------------//

        public static final DeferredRegister<DataComponentType<?>> zComponents = DeferredRegister
                .createDataComponents(Registries.DATA_COMPONENT_TYPE, MODULE_ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<UpgradeComponents>> UPGRADE_COMPONENTS = zComponents
                .register("upgrade_components",
                        () -> DataComponentType.<UpgradeComponents>builder()
                                .persistent(UpgradeComponents.CODEC)
                                .networkSynchronized(UpgradeComponents.STREAM_CODEC)
                                .build());

        public static final DeferredHolder<DataComponentType<?>, DataComponentType<Identifier>> IDENTIFIER = zComponents
                .register("identifier",
                        () -> DataComponentType.<Identifier>builder()
                                .persistent(Identifier.CODEC)
                                .networkSynchronized(Identifier.STREAM_CODEC)
                                .build());

    }

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
                        return new SimpleEnergyHandler(be.getMaxEnergy(), be.getMaxInsertEnergy(),
                                be.getMaxExtractEnergy(), be.getBaseEnergyStored());
                    return null;
                }).build());

    }

    public class zItems {
        public static void register(IEventBus bus) {
            zItem.register(bus);
        }

        public static final DeferredRegister.Items zItem = DeferredRegister.createItems(CakeStickLib.MODULE_ID);

        public static final DeferredItem<Item> CAKE_STICK = zItem.registerItem("cake_stick",
                p -> new CakeStick(p));

        public static final DeferredHolder<Item, Item> REDSTONE_ACID = zItem.registerItem("redstone_acid",
                p -> new RedstoneAcid(p));

        public static final DeferredHolder<Item, Item> HONEY_SOLUTION = zItem.registerItem("honey_solution",
                p -> new HoneySolution(p));

        public static final DeferredHolder<Item, Item> CHISEL = zItem.registerItem("chisel",
                p -> new Chisel(p));

        public static final DeferredHolder<Item, Item> PATINA = zItem.registerSimpleItem("patina");

    }

    public class zItemTags {

        public static final TagKey<Item> OXIDIZER = RegistryUtils.tagItem(MODULE_ID, "oxidizer");
        public static final TagKey<Item> WAXING = RegistryUtils.tagItem(MODULE_ID, "waxing");
    }

    public class zRecipeTypes {
        // ------------------------------------------------------------------------------------------------------------------------------------//
        public static void register(IEventBus bus) {
            SERIALIZERS.register(bus);
            TYPES.register(bus);
        }

        // ------------------------------------------------------------------------------------------------------------------------------------//
        public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
                .create(Registries.RECIPE_SERIALIZER, MODULE_ID);
        public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE,
                MODULE_ID);
        // ------------------------------------------------------------------------------------------------------------------------------------//

        public static final RecipeRegister<CopperOxidationRecipe> COPPER_OXIDATION = RecipeRegister.of(
                "copper_oxidation",
                () -> CopperOxidationRecipe.serializer());

        // ------------------------------------------------------------------------------------------------------------------------------------//
    }
}
