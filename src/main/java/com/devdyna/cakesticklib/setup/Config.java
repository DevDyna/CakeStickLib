package com.devdyna.cakesticklib.setup;

import com.devdyna.cakesticklib.api.ConfigUtils;
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

        public static IntValue MACHINE_MAX_SPEED_UPGRADES_TYPE;
        public static IntValue MACHINE_MAX_ENERGY_UPGRADES_TYPE;
        public static IntValue MACHINE_MAX_LUCK_UPGRADES_TYPE;
        public static IntValue MACHINE_MAX_FLUID_UPGRADES_TYPE;

        public static IntValue MACHINE_MINIMAL_TICK_DELAY;
        public static IntValue MACHINE_MINIMAL_FE_COST;
        public static IntValue MACHINE_MINIMAL_FLUID_COST;
        public static IntValue MACHINE_MAXIMAL_LUCK;

        private static final ModConfigSpec.Builder qCOMMON = new ModConfigSpec.Builder();

        public static void register(ModContainer c) {
                gameplay();
                upgrades();
                c.registerConfig(ModConfig.Type.COMMON, qCOMMON.build());
        }

        public static void gameplay() {
                qCOMMON.comment("Gameplay Additions").push("events");

                HARVESTABLE_ACTION = ConfigUtils.bool(qCOMMON,
                                "Player can right-click on crops to collect intend of breaking" +
                                                "\nIf detected Farmers Delight it will turn off by default",
                                "harvestable_action",
                                !ModAddonUtil.checkMod("farmersdelight"));

                TREE_CUTTING_LIMIT = ConfigUtils.num(qCOMMON,
                                "Max number of harvestable blocks foreach interaction",
                                "tree_cutting_limit", 2048);

                ENDER_EYE_RETURN_EVENT = ConfigUtils.bool(qCOMMON,
                                "End Portal Frame can be interacted to remove Eye of Ender",
                                "ender_eye_return", true);

                PATINA_DROP_EVENT = ConfigUtils.bool(qCOMMON,
                                "Patina will drop when scrapped any oxidized copper block",
                                "patina_drop", true);

                REDSTONE_ACID_EVENT = ConfigUtils.bool(qCOMMON,
                                "Redstone Acid can be used to increase a stage of oxidation at the copper block clicked",
                                "redstone_acid_oxide", true);

                HONEY_SOLUTION_EVENT = ConfigUtils.bool(qCOMMON,
                                "Honey Solution can be used to wax copper blocks clicked",
                                "honey_solution_wax", true);


                qCOMMON.pop();
        }

        public static void upgrades() {
                qCOMMON.comment("Machine Upgrades").push("upgrades");

                MACHINE_MAX_SPEED_UPGRADES_TYPE = ConfigUtils.num(qCOMMON,
                                "Max Speed Increaser Upgrade Types usable foreach machine",
                                "max_speed_upgrades", 4, 0, 16);

                MACHINE_MAX_ENERGY_UPGRADES_TYPE = ConfigUtils.num(qCOMMON,
                                "Max Energy Efficiency Upgrade Types usable foreach machine",
                                "max_energy_upgrades", 16, 0, 16);

                MACHINE_MAX_LUCK_UPGRADES_TYPE = ConfigUtils.num(qCOMMON,
                                "Max Secondary Output Increaser Upgrade Types usable foreach machine",
                                "max_luck_upgrades", 4, 0, 16);

                MACHINE_MAX_FLUID_UPGRADES_TYPE = ConfigUtils.num(qCOMMON,
                                "Max Fluid Efficiency Upgrade Types usable foreach machine",
                                "max_fluid_upgrades", 4, 0, 16);

                MACHINE_MINIMAL_TICK_DELAY = ConfigUtils.num(qCOMMON,
                                "Minimal tick delay based on upgrade installed",
                                "min_tick_rate", 1);

                MACHINE_MINIMAL_FE_COST = ConfigUtils.num(qCOMMON,
                                "Minimal Energy cost based on upgrade installed",
                                "min_fe_cost", 5);

                MACHINE_MINIMAL_FLUID_COST = ConfigUtils.num(qCOMMON,
                                "Minimal Fluid cost based on upgrade installed",
                                "min_mb_cost", 1);
                MACHINE_MAXIMAL_LUCK = ConfigUtils.num(qCOMMON,
                                "Maximal Secondary Chance based on upgrade installed",
                                "max_luck", 100);

                qCOMMON.pop();
        }

}