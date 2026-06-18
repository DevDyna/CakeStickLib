package com.devdyna.cakesticklib.api.compat.jei;

import static com.devdyna.cakesticklib.CakeStickLib.MODULE_ID;

import java.util.Arrays;
import java.util.List;

import com.devdyna.cakesticklib.api.utils.x;

import mezz.jei.api.registration.IIngredientAliasRegistration;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

public class JEIAliasesHelper {

    public static class Aliases {
        public static final String BLOCK_BREAKER = MODULE_ID + ".jei.alias.block.break";
        public static final String DRILL = MODULE_ID + ".jei.alias.drill";
        public static final String MINER = MODULE_ID + ".jei.alias.miner";
        public static final String TREE_CUTTER = MODULE_ID + ".jei.alias.tree.cutter";
        public static final String TREE_FELLER = MODULE_ID + ".jei.alias.tree.feller";
        public static final String ENTITY_KILLER = MODULE_ID + ".jei.alias.entity.killer";
        public static final String FAKEPLAYER = MODULE_ID + ".jei.alias.fakeplayer";
        public static final String HOPPER = MODULE_ID + ".jei.alias.hopper";
        public static final String ITEM_COLLECTOR = MODULE_ID + ".jei.alias.item.collector";
        public static final String ENTITY_MOVER = MODULE_ID + ".jei.alias.entity.mover";
        public static final String REDSTONE_SENSIBLE = MODULE_ID + ".jei.alias.redstone.sensible";

    }

    public static void addAlias(IIngredientAliasRegistration r,
            List<DeferredHolder<? extends ItemLike, ? extends ItemLike>> i,
            String... alias) {

        i.stream()
                .map(DeferredHolder::get).map(x::item)
                .forEach(e -> Arrays.asList(alias).forEach(a -> r.addAlias(e, a)));

    }
}
