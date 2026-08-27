package com.devdyna.cakesticklib.setup.common.recipes.tool_durability;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class InputToolDurability {

    protected Ingredient tool;
    protected int durability;

    public InputToolDurability(Ingredient tool, int durability) {
        this.tool = tool;
        this.durability = durability;
    }

    public static InputToolDurability of(Ingredient tool, int durability) {
        return new InputToolDurability(tool, durability);
    }

    public int getDurability() {
        return durability;
    }

    public Ingredient getTool() {
        return tool;
    }

    public boolean test(CraftingInput input, Level level) {
        return input.items().stream()
                .anyMatch(stack -> tool.test(stack)
                        && stack.getDamageValue() + durability <= stack.getMaxDamage());
    }

    public boolean isTool(ItemStack input) {
        return tool.test(input);
    }

    public ItemStack getRemainItem(ItemStack stack) {
        var copy = stack.copy();
        var damage = copy.getDamageValue() + durability;
        if (copy.getMaxDamage() - damage <= 0)
            return stack.getCraftingRemainder().create();
        copy.setDamageValue(damage);
        return copy;
    }

    public static final MapCodec<InputToolDurability> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("tool")
                    .forGetter(InputToolDurability::getTool),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("damage")
                    .forGetter(InputToolDurability::getDurability))
            .apply(inst, InputToolDurability::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, InputToolDurability> STREAM_CODEC = StreamCodec
            .composite(
                    Ingredient.CONTENTS_STREAM_CODEC, InputToolDurability::getTool,
                    ByteBufCodecs.INT, InputToolDurability::getDurability,
                    InputToolDurability::new);

}
