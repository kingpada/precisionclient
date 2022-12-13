package net.minecraft.src.enchantments;

import net.minecraft.src.enchantments.Enchantment;

public interface IEnchantmentModifier {
    /**
     * Generic method use to calculate modifiers of offensive or defensive
     * enchantment values.
     */
    void calculateModifier(Enchantment var1, int var2);
}
