package com.devdyna.cakesticklib.setup;

import com.devdyna.cakesticklib.api.config.ConfigUtils;
import com.devdyna.cakesticklib.api.utils.ModAddonUtil;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.*;

public class Config {

        public static BooleanValue HARVESTABLE_ACTION;
        public static IntValue TREE_CUTTING_LIMIT;
        public static BooleanValue ENDER_EYE_RETURN_EVENT;
        public static BooleanValue PATINA_DROP_EVENT;
        public static BooleanValue REDSTONE_ACID_EVENT;
        public static BooleanValue HONEY_SOLUTION_EVENT;
        public static BooleanValue CROOK_DROP_EVENT;
        public static BooleanValue MINED_RETURN_CRAFT_EVENT;

        public static IntValue MACHINE_MAX_SPEED_UPGRADES_TYPE;
        public static IntValue MACHINE_MAX_ENERGY_UPGRADES_TYPE;
        public static IntValue MACHINE_MAX_LUCK_UPGRADES_TYPE;
        public static IntValue MACHINE_MAX_FLUID_UPGRADES_TYPE;

        public static IntValue MACHINE_MINIMAL_TICK_DELAY;
        public static IntValue MACHINE_MINIMAL_FE_COST;
        public static IntValue MACHINE_MINIMAL_FLUID_COST;
        public static IntValue MACHINE_MAXIMAL_LUCK;

        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static void register(ModContainer c) {
                gameplay();
                upgrades();
                c.registerConfig(ModConfig.Type.SERVER, BUILDER.build());
        }

        public static void gameplay() {
                BUILDER.comment("Gameplay Additions").push("events");

                HARVESTABLE_ACTION = ConfigUtils.bool(BUILDER,
                                "Player can right-click on crops to collect intend of breaking" +
                                                "\nIf detected Farmers Delight it will turn off by default",
                                "harvestable_action",
                                !ModAddonUtil.checkMod("farmersdelight"));

                TREE_CUTTING_LIMIT = ConfigUtils.num(BUILDER,
                                "Max number of harvestable blocks foreach interaction",
                                "tree_cutting_limit", 2048);

                ENDER_EYE_RETURN_EVENT = ConfigUtils.bool(BUILDER,
                                "End Portal Frame can be interacted to remove Eye of Ender",
                                "ender_eye_return", true);

                PATINA_DROP_EVENT = ConfigUtils.bool(BUILDER,
                                "Patina will drop when scrapped any oxidized copper block",
                                "patina_drop", true);

                REDSTONE_ACID_EVENT = ConfigUtils.bool(BUILDER,
                                "Redstone Acid can be used to increase a stage of oxidation at the copper block clicked",
                                "redstone_acid_oxide", true);

                HONEY_SOLUTION_EVENT = ConfigUtils.bool(BUILDER,
                                "Honey Solution can be used to wax copper blocks clicked",
                                "honey_solution_wax", true);

                CROOK_DROP_EVENT = ConfigUtils.bool(BUILDER,
                                "Leaves drop more items when broken with a crook item",
                                "crook_drop_event", true);
                MINED_RETURN_CRAFT_EVENT = ConfigUtils.bool(BUILDER,
                                "Mined blocks with specific tools will return the result",
                                "mined_return_craft_event", true);

                BUILDER.pop();
        }

        public static void upgrades() {
                BUILDER.comment("Machine Upgrades").push("upgrades");

                MACHINE_MAX_SPEED_UPGRADES_TYPE = ConfigUtils.num(BUILDER,
                                "Max Speed Increaser Upgrade Types usable foreach machine",
                                "max_speed_upgrades", 4, 0, 16);

                MACHINE_MAX_ENERGY_UPGRADES_TYPE = ConfigUtils.num(BUILDER,
                                "Max Energy Efficiency Upgrade Types usable foreach machine",
                                "max_energy_upgrades", 16, 0, 16);

                MACHINE_MAX_LUCK_UPGRADES_TYPE = ConfigUtils.num(BUILDER,
                                "Max Secondary Output Increaser Upgrade Types usable foreach machine",
                                "max_luck_upgrades", 4, 0, 16);

                MACHINE_MAX_FLUID_UPGRADES_TYPE = ConfigUtils.num(BUILDER,
                                "Max Fluid Efficiency Upgrade Types usable foreach machine",
                                "max_fluid_upgrades", 4, 0, 16);

                MACHINE_MINIMAL_TICK_DELAY = ConfigUtils.num(BUILDER,
                                "Minimal tick delay based on upgrade installed",
                                "min_tick_rate", 1);

                MACHINE_MINIMAL_FE_COST = ConfigUtils.num(BUILDER,
                                "Minimal Energy cost based on upgrade installed",
                                "min_fe_cost", 5);

                MACHINE_MINIMAL_FLUID_COST = ConfigUtils.num(BUILDER,
                                "Minimal Fluid cost based on upgrade installed",
                                "min_mb_cost", 1);
                MACHINE_MAXIMAL_LUCK = ConfigUtils.num(BUILDER,
                                "Maximal Secondary Chance based on upgrade installed",
                                "max_luck", 100);

                BUILDER.pop();
        }

}