package net.minecraft.src;

public final class BehaviorDispenseItemProvider implements IBehaviorDispenseItem {
    /**
     * Dispenses the specified ItemStack from a dispenser.
     */
    public ItemStack dispense(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
        return par2ItemStack;
    }
}
