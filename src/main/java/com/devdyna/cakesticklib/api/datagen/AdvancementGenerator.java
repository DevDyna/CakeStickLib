package com.devdyna.cakesticklib.api.datagen;

import java.util.List;
import java.util.function.Consumer;

import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.datafixers.util.Pair;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ItemLike;

//TODO API : unify with RecipeGenerator when possible
public interface AdvancementGenerator {

        public class VanillaAdvancments {
                public static final String ADVENTURING_TIME = "minecraft:adventure/adventuring_time";
                public static final String ARBALISTIC = "minecraft:adventure/arbalistic";
                public static final String AVOID_VIBRATION = "minecraft:adventure/avoid_vibration";
                public static final String BLOWBACK = "minecraft:adventure/blowback";
                public static final String BRUSH_ARMADILLO = "minecraft:adventure/brush_armadillo";
                public static final String BULLSEYE = "minecraft:adventure/bullseye";
                public static final String CRAFT_DECORATED_POT_USING_ONLY_SHERDS = "minecraft:adventure/craft_decorated_pot_using_only_sherds";
                public static final String CRAFTERS_CRAFTING_CRAFTERS = "minecraft:adventure/crafters_crafting_crafters";
                public static final String FALL_FROM_WORLD_HEIGHT = "minecraft:adventure/fall_from_world_height";
                public static final String HEART_TRANSPLANTER = "minecraft:adventure/heart_transplanter";
                public static final String HERO_OF_THE_VILLAGE = "minecraft:adventure/hero_of_the_village";
                public static final String HONEY_BLOCK_SLIDE = "minecraft:adventure/honey_block_slide";
                public static final String KILL_A_MOB = "minecraft:adventure/kill_a_mob";
                public static final String KILL_ALL_MOBS = "minecraft:adventure/kill_all_mobs";
                public static final String KILL_MOB_NEAR_SCULK_CATALYST = "minecraft:adventure/kill_mob_near_sculk_catalyst";
                public static final String LIGHTEN_UP = "minecraft:adventure/lighten_up";
                public static final String LIGHTNING_ROD_WITH_VILLAGER_NO_FIRE = "minecraft:adventure/lightning_rod_with_villager_no_fire";
                public static final String MINECRAFT_TRIALS_EDITION = "minecraft:adventure/minecraft_trials_edition";
                public static final String OL_BETSY = "minecraft:adventure/ol_betsy";
                public static final String OVEROVERKILL = "minecraft:adventure/overoverkill";
                public static final String PLAY_JUKEBOX_IN_MEADOWS = "minecraft:adventure/play_jukebox_in_meadows";
                public static final String READ_POWER_OF_CHISELED_BOOKSHELF = "minecraft:adventure/read_power_of_chiseled_bookshelf";
                public static final String REVAULTING = "minecraft:adventure/revaulting";
                public static final String ADVENTURE_ROOT = "minecraft:adventure/root";
                public static final String SALVAGE_SHERD = "minecraft:adventure/salvage_sherd";
                public static final String SHOOT_ARROW = "minecraft:adventure/shoot_arrow";
                public static final String SLEEP_IN_BED = "minecraft:adventure/sleep_in_bed";
                public static final String SNIPER_DUEL = "minecraft:adventure/sniper_duel";
                public static final String SPEAR_MANY_MOBS = "minecraft:adventure/spear_many_mobs";
                public static final String SPYGLASS_AT_DRAGON = "minecraft:adventure/spyglass_at_dragon";
                public static final String SPYGLASS_AT_GHAST = "minecraft:adventure/spyglass_at_ghast";
                public static final String SPYGLASS_AT_PARROT = "minecraft:adventure/spyglass_at_parrot";
                public static final String SUMMON_IRON_GOLEM = "minecraft:adventure/summon_iron_golem";
                public static final String THROW_TRIDENT = "minecraft:adventure/throw_trident";
                public static final String TOTEM_OF_UNDYING = "minecraft:adventure/totem_of_undying";
                public static final String TRADE = "minecraft:adventure/trade";
                public static final String TRADE_AT_WORLD_HEIGHT = "minecraft:adventure/trade_at_world_height";
                public static final String TRIM_WITH_ALL_EXCLUSIVE_ARMOR_PATTERNS = "minecraft:adventure/trim_with_all_exclusive_armor_patterns";
                public static final String TRIM_WITH_ANY_ARMOR_PATTERN = "minecraft:adventure/trim_with_any_armor_pattern";
                public static final String TWO_BIRDS_ONE_ARROW = "minecraft:adventure/two_birds_one_arrow";
                public static final String UNDER_LOCK_AND_KEY = "minecraft:adventure/under_lock_and_key";
                public static final String USE_LODESTONE = "minecraft:adventure/use_lodestone";
                public static final String VERY_VERY_FRIGHTENING = "minecraft:adventure/very_very_frightening";
                public static final String VOLUNTARY_EXILE = "minecraft:adventure/voluntary_exile";
                public static final String WALK_ON_POWDER_SNOW_WITH_LEATHER_BOOTS = "minecraft:adventure/walk_on_powder_snow_with_leather_boots";
                public static final String WHO_NEEDS_ROCKETS = "minecraft:adventure/who_needs_rockets";
                public static final String WHOS_THE_PILLAGER_NOW = "minecraft:adventure/whos_the_pillager_now";
                public static final String DRAGON_BREATH = "minecraft:end/dragon_breath";
                public static final String DRAGON_EGG = "minecraft:end/dragon_egg";
                public static final String ELYTRA = "minecraft:end/elytra";
                public static final String ENTER_END_GATEWAY = "minecraft:end/enter_end_gateway";
                public static final String FIND_END_CITY = "minecraft:end/find_end_city";
                public static final String KILL_DRAGON = "minecraft:end/kill_dragon";
                public static final String LEVITATE = "minecraft:end/levitate";
                public static final String RESPAWN_DRAGON = "minecraft:end/respawn_dragon";
                public static final String END_ROOT = "minecraft:end/root";
                public static final String ALLAY_DELIVER_CAKE_TO_NOTE_BLOCK = "minecraft:husbandry/allay_deliver_cake_to_note_block";
                public static final String ALLAY_DELIVER_ITEM_TO_PLAYER = "minecraft:husbandry/allay_deliver_item_to_player";
                public static final String AXOLOTL_IN_A_BUCKET = "minecraft:husbandry/axolotl_in_a_bucket";
                public static final String BALANCED_DIET = "minecraft:husbandry/balanced_diet";
                public static final String BRED_ALL_ANIMALS = "minecraft:husbandry/bred_all_animals";
                public static final String BREED_AN_ANIMAL = "minecraft:husbandry/breed_an_animal";
                public static final String COMPLETE_CATALOGUE = "minecraft:husbandry/complete_catalogue";
                public static final String FEED_SNIFFLET = "minecraft:husbandry/feed_snifflet";
                public static final String FISHY_BUSINESS = "minecraft:husbandry/fishy_business";
                public static final String FROGLIGHTS = "minecraft:husbandry/froglights";
                public static final String KILL_AXOLOTL_TARGET = "minecraft:husbandry/kill_axolotl_target";
                public static final String LEASH_ALL_FROG_VARIANTS = "minecraft:husbandry/leash_all_frog_variants";
                public static final String MAKE_A_SIGN_GLOW = "minecraft:husbandry/make_a_sign_glow";
                public static final String OBTAIN_NETHERITE_HOE = "minecraft:husbandry/obtain_netherite_hoe";
                public static final String OBTAIN_SNIFFER_EGG = "minecraft:husbandry/obtain_sniffer_egg";
                public static final String PLACE_DRIED_GHAST_IN_WATER = "minecraft:husbandry/place_dried_ghast_in_water";
                public static final String PLANT_ANY_SNIFFER_SEED = "minecraft:husbandry/plant_any_sniffer_seed";
                public static final String PLANT_SEED = "minecraft:husbandry/plant_seed";
                public static final String REMOVE_WOLF_ARMOR = "minecraft:husbandry/remove_wolf_armor";
                public static final String REPAIR_WOLF_ARMOR = "minecraft:husbandry/repair_wolf_armor";
                public static final String RIDE_A_BOAT_WITH_A_GOAT = "minecraft:husbandry/ride_a_boat_with_a_goat";
                public static final String HUSBANDRY_ROOT = "minecraft:husbandry/root";
                public static final String SAFELY_HARVEST_HONEY = "minecraft:husbandry/safely_harvest_honey";
                public static final String SILK_TOUCH_NEST = "minecraft:husbandry/silk_touch_nest";
                public static final String TACTICAL_FISHING = "minecraft:husbandry/tactical_fishing";
                public static final String TADPOLE_IN_A_BUCKET = "minecraft:husbandry/tadpole_in_a_bucket";
                public static final String TAME_AN_ANIMAL = "minecraft:husbandry/tame_an_animal";
                public static final String UH_OH = "minecraft:husbandry/uh_oh";
                public static final String WAX_OFF = "minecraft:husbandry/wax_off";
                public static final String WAX_ON = "minecraft:husbandry/wax_on";
                public static final String WHOLE_PACK = "minecraft:husbandry/whole_pack";
                public static final String ALL_EFFECTS = "minecraft:nether/all_effects";
                public static final String ALL_POTIONS = "minecraft:nether/all_potions";
                public static final String BREW_POTION = "minecraft:nether/brew_potion";
                public static final String CHARGE_RESPAWN_ANCHOR = "minecraft:nether/charge_respawn_anchor";
                public static final String CREATE_BEACON = "minecraft:nether/create_beacon";
                public static final String CREATE_FULL_BEACON = "minecraft:nether/create_full_beacon";
                public static final String DISTRACT_PIGLIN = "minecraft:nether/distract_piglin";
                public static final String EXPLORE_NETHER = "minecraft:nether/explore_nether";
                public static final String FAST_TRAVEL = "minecraft:nether/fast_travel";
                public static final String FIND_BASTION = "minecraft:nether/find_bastion";
                public static final String FIND_FORTRESS = "minecraft:nether/find_fortress";
                public static final String GET_WITHER_SKULL = "minecraft:nether/get_wither_skull";
                public static final String LOOT_BASTION = "minecraft:nether/loot_bastion";
                public static final String NETHERITE_ARMOR = "minecraft:nether/netherite_armor";
                public static final String OBTAIN_ANCIENT_DEBRIS = "minecraft:nether/obtain_ancient_debris";
                public static final String OBTAIN_BLAZE_ROD = "minecraft:nether/obtain_blaze_rod";
                public static final String OBTAIN_CRYING_OBSIDIAN = "minecraft:nether/obtain_crying_obsidian";
                public static final String RETURN_TO_SENDER = "minecraft:nether/return_to_sender";
                public static final String RIDE_STRIDER = "minecraft:nether/ride_strider";
                public static final String RIDE_STRIDER_IN_OVERWORLD_LAVA = "minecraft:nether/ride_strider_in_overworld_lava";
                public static final String NETHER_ROOT = "minecraft:nether/root";
                public static final String SUMMON_WITHER = "minecraft:nether/summon_wither";
                public static final String UNEASY_ALLIANCE = "minecraft:nether/uneasy_alliance";
                public static final String CURE_ZOMBIE_VILLAGER = "minecraft:story/cure_zombie_villager";
                public static final String DEFLECT_ARROW = "minecraft:story/deflect_arrow";
                public static final String ENCHANT_ITEM = "minecraft:story/enchant_item";
                public static final String ENTER_THE_END = "minecraft:story/enter_the_end";
                public static final String ENTER_THE_NETHER = "minecraft:story/enter_the_nether";
                public static final String FOLLOW_ENDER_EYE = "minecraft:story/follow_ender_eye";
                public static final String FORM_OBSIDIAN = "minecraft:story/form_obsidian";
                public static final String IRON_TOOLS = "minecraft:story/iron_tools";
                public static final String LAVA_BUCKET = "minecraft:story/lava_bucket";
                public static final String MINE_DIAMOND = "minecraft:story/mine_diamond";
                public static final String MINE_STONE = "minecraft:story/mine_stone";
                public static final String OBTAIN_ARMOR = "minecraft:story/obtain_armor";
                public static final String STORY_ROOT = "minecraft:story/root";
                public static final String SHINY_GEAR = "minecraft:story/shiny_gear";
                public static final String SMELT_IRON = "minecraft:story/smelt_iron";
                public static final String UPGRADE_TOOLS = "minecraft:story/upgrade_tools";
        }

        // abstract HolderGetter<Item> getItems();

        abstract String getModName();

        default Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike item) {
                return InventoryChangeTrigger.TriggerInstance.hasItems(item);
        }

        default String getCraftItem(String i) {
                return "craft_" + i;
        }

        default String getCraftItem(ItemLike i) {
                return getCraftItem(x.name(i));
        }

        default AdvancementRequirements getRequireCraft(String i) {
                return AdvancementRequirements.allOf(List.of(getCraftItem(i)));
        }

        default AdvancementRequirements getRequireCraft(ItemLike i) {
                return AdvancementRequirements.allOf(List.of(getCraftItem(i)));
        }

        default AdvancementHolder simpleTask(AdvancementHolder parent, ItemLike item, String path,
                        Consumer<AdvancementHolder> c) {
                return AdvancementsUtils.getExistingParent(parent, item,
                                getModName(), x.name(item), AdvancementType.TASK, true, true, false)
                                .addCriterion(getCraftItem(item), has(item))
                                .requirements(getRequireCraft(item))
                                .save(c, path + x.name(item));
        }

        default AdvancementHolder simpleTask(Pair<AdvancementHolder, AdvancementHolder> parent, ItemLike item,
                        String path,
                        Consumer<AdvancementHolder> c) {
                return AdvancementsUtils.getExistingParent(parent.getFirst(), item,
                                getModName(), x.name(item), AdvancementType.TASK, true, true, false)
                                .addCriterion(getCraftItem(item), has(item))
                                .requirements(getRequireCraft(item))
                                .save(c, path + x.name(item));
        }

        default AdvancementHolder simpleTask(String parent, ItemLike item, String path,
                        Consumer<AdvancementHolder> c) {
                return AdvancementsUtils.getExistingParent(parent, item,
                                getModName(), x.name(item), AdvancementType.TASK, true, true, false)
                                .addCriterion(getCraftItem(item), has(item))
                                .requirements(getRequireCraft(item))
                                .save(c, path + x.name(item));
        }

        default AdvancementHolder simpleRoot(ItemLike item, String path, Identifier bg,
                        String name,
                        Consumer<AdvancementHolder> c) {
                return Advancement.Builder.advancement().display(item,
                                Component.translatable(getModName() + ".advancement.root." + name),
                                Component.translatable(getModName() + ".advancement.root." + name + ".desc"),
                                bg,
                                AdvancementType.TASK, false, false, false)
                                .addCriterion(getCraftItem(item), has(item))
                                .requirements(getRequireCraft(item))
                                .save(c, path + "root");
        }

        /**
         * return {@code <Root,Task>}
         * <br/>
         * <br/>
         * Use the {@code .getSecond()} (Task) to reference on same tab<br/>
         * <br/>
         * Use the {@code .getFirst()} (Root) to reference to the new tab
         */
        default Pair<AdvancementHolder, AdvancementHolder> simpleDependRoot(String require,
                        ItemLike item, String path, Identifier bg, String name, Consumer<AdvancementHolder> c) {
                return Pair.of(simpleRoot(item, path, bg, name, c), simpleTask(require, item, path, c));
        }

        /**
         * return {@code <Root,Task>}
         * <br/>
         * <br/>
         * Use the {@code Task} to reference on same tab<br/>
         * <br/>
         * Use the {@code Root} to reference to the new tab
         */
        default Pair<AdvancementHolder, AdvancementHolder> simpleDependRoot(AdvancementHolder require,
                        ItemLike item, String path, Identifier bg, String name, Consumer<AdvancementHolder> c) {
                return Pair.of(simpleRoot(item, path, bg, name, c), simpleTask(require, item, path, c));
        }

}
