package net.minecraft.src.inventory;

import net.minecraft.src.inventory.InventoryBasic;

public interface IInvBasic {
    /**
     * Called by InventoryBasic.onInventoryChanged() on a array that is never
     * filled.
     */
    void onInventoryChanged(InventoryBasic var1);
}
