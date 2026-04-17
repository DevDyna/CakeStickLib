package com.devdyna.cakesticklib.setup.datagen.client;

import static com.devdyna.cakesticklib.Main.ID;
import static com.devdyna.cakesticklib.api.datagen.LangUtils.TIP_COLOR;

import com.devdyna.cakesticklib.setup.registry.types.zItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class DataLang extends LanguageProvider {

    public DataLang(PackOutput o) {
        super(o, ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // mainly for Item Tooltips
        add(ID + ".hold.shift", "§8Hold [" + TIP_COLOR + "Shift§8] to see more details");
        add(ID + ".item.disabled", TIP_COLOR + "Item-Form unobtainable");
        add(ID + ".item.placeable", TIP_COLOR + "Can be placed");
        add(ID + ".block.rotate_by_click", TIP_COLOR + "Can be rotated with right-click");
        add(ID + ".block.blast_proof", TIP_COLOR + "Blast resistance");
        add(ID + ".item.click.install", TIP_COLOR + "Right click to install");
        add(ID + ".block.safe_building", TIP_COLOR + "Safe for decoration");
        add(ID + ".item.crafting_ingredient", TIP_COLOR + "Crafting Ingredient");

        // mainly for menu or JEI categories
        add(ID + ".ui.dont_consume", "§cNot consume");
        //
        add(ID + ".tank_interact.empty", "Empty");
        add(ID + ".interaction.world.immutable", "In-World interaction not editable");

        // mainly for production descriptions
        add(ID + ".provider.generic", "Generate %s %s every %d ticks");
        add(ID + ".provider.every_tick", "Generate %s %s every tick");
        //
        add(ID + ".info.status.false", "Status: §cInactive");
        add(ID + ".info.status.true", "Status: §aActive");
        add(ID + ".info.color", "Color: %d");
        add(ID + ".info.blockpos", "BlockPos : ");
        add(ID + ".info.dir", "Dir : ");
        add(ID + ".info.dirs", "Dirs : ");

        // setup
        addItem(zItems.CAKE_STICK, "Cake Stick");
        add(ID + ".setup.cakestick.tip", TIP_COLOR + "Place cake slices");
        advKey("cake_stick", "The cake is(n't) a lie!", "The Cake stick is right!");

        add(ID + ".upgrades.title", TIP_COLOR + "Upgrade Modifiers");

        add(ID + ".upgrades.modifier.energy", TIP_COLOR + "Energy Usage: %s");
        add(ID + ".upgrades.modifier.speed", TIP_COLOR + "Recipe Speed: %s");
        add(ID + ".upgrades.modifier.luck", TIP_COLOR + "Secondary Output: %s");
        add(ID + ".upgrades.modifier.fluid", TIP_COLOR + "Fluid Usage: %s");

        add(ID + ".screen.upgrades", "Supported Upgrades:");

        add(ID + ".screen.modifier.energy", TIP_COLOR + "Energy Modifier §7[§f§a%s§7]");
        add(ID + ".screen.modifier.speed", TIP_COLOR + "Speed Modifier §7[§f§a%s§7]");
        add(ID + ".screen.modifier.luck", TIP_COLOR + "Luck Modifier §7[§f§a%s§7]");
        add(ID + ".screen.modifier.fluid", TIP_COLOR + "Fluid Modifier §7[§f§a%s§7]");

    }

    private void advKey(String k, String title, String desc) {
        add(ID + ".advancement.branch." + k, title);
        add(ID + ".advancement.branch." + k + ".desc", desc);
    }

}