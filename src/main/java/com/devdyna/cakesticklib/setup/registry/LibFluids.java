package com.devdyna.cakesticklib.setup.registry;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import com.devdyna.cakesticklib.api.FluidRegister;
import com.devdyna.cakesticklib.api.utils.ColorUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class LibFluids {
        public static void register(IEventBus bus) {
                zFluids.register(bus);
                zFluidTypes.register(bus);
        }

        public static final DeferredRegister<Fluid> zFluids = DeferredRegister.create(BuiltInRegistries.FLUID,
                        MODULE_ID);
        public static final DeferredRegister<FluidType> zFluidTypes = DeferredRegister.create(Keys.FLUID_TYPES,
                        MODULE_ID);

        public static final FluidRegister CRUDE_OIL = FluidRegister.heavy("crude_oil", ColorUtils.BLACK.COAL);

        public static final FluidRegister RESIN = FluidRegister.simple("resin", ColorUtils.ORANGE.TOPAZ);
        public static final FluidRegister LATEX = FluidRegister.simple("latex", ColorUtils.WHITE.CALCITE);
        public static final FluidRegister SAP = FluidRegister.simple("sap", ColorUtils.YELLOW.CITRINE);
        public static final FluidRegister GLUE = FluidRegister.heavy("glue", ColorUtils.GREEN.PALE_GREEN);
        public static final FluidRegister HONEY = FluidRegister.heavy("honey", ColorUtils.YELLOW.AMBER);

        public static final FluidRegister LIQUID_GLASS = FluidRegister.molten("liquid_glass",
                        ColorUtils.color(255, 255, 255, 64));

        public static final FluidRegister MOLTEN_GLOWSTONE = FluidRegister.molten("molten_glowstone", ColorUtils.YELLOW.NATIVE_GOLD);
       
        public static final FluidRegister MOLTEN_REDSTONE = FluidRegister.molten("molten_redstone", ColorUtils.RED.TOMATO);
       
        public static final FluidRegister MOLTEN_IRON = FluidRegister.molten("molten_iron", ColorUtils.GRAY.GRAY);

        public static final FluidRegister MOLTEN_COPPER = FluidRegister.molten("molten_copper",
                        ColorUtils.ORANGE.COPPER);

        public static final FluidRegister MOLTEN_GOLD = FluidRegister.molten("molten_gold", ColorUtils.YELLOW.GOLD);

        public static final FluidRegister MOLTEN_STEEL = FluidRegister.molten("molten_steel",
                        ColorUtils.GRAY.DARK_GRAY);

        public static final FluidRegister MOLTEN_NETHERITE = FluidRegister.molten("molten_netherite",
                        ColorUtils.BROWN.IRON_ORE);

        public static final FluidRegister MOLTEN_ANCIENT_DEBRIS = FluidRegister.molten("molten_ancient_debris",
                        ColorUtils.BROWN.BROWN);

        public static final FluidRegister SULFURIC_ACID = FluidRegister.molten("sulfuric_acid",
                        ColorUtils.YELLOW.YELLOW);

        public static final FluidRegister PLASTIC = FluidRegister.simple("plastic", ColorUtils.GRAY.SILVER_GRAY);

        public static final FluidRegister MOLTEN_BLAZING = FluidRegister.molten("molten_blazing",
                        ColorUtils.YELLOW.GOLDENROD);

}