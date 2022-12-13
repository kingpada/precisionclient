package net.minecraft.src.blocks;

import net.minecraft.src.items.objects.ItemStack;

public interface IBehaviorDispenseItem {

    /**
     * Dispenses the specified ItemStack from a dispenser.
     */
    ItemStack dispense(IBlockSource var1, ItemStack var2);
}
