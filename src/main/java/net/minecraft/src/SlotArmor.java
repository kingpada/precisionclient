package net.minecraft.src;

import net.minecraft.src.blocks.Block;
import net.minecraft.src.containers.ContainerPlayer;

public class SlotArmor extends Slot {
    /**
     * The armor type that can be placed on that slot, it uses the same values of
     * armorType field on ItemArmor.
     */
    final int armorType;

    /**
     * The parent class of this clot, ContainerPlayer, SlotArmor is a Anon inner
     * class.
     */
    final ContainerPlayer parent;

    public SlotArmor(ContainerPlayer par1ContainerPlayer, IInventory par2IInventory, int par3, int par4, int par5, int par6) {
        super(par2IInventory, par3, par4, par5);
        this.parent = par1ContainerPlayer;
        this.armorType = par6;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as
     * getInventoryStackLimit(), but 1 in the case of armor slots)
     */
    public int getSlotStackLimit() {
        return 1;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the
     * armor slots.
     */
    public boolean isItemValid(ItemStack par1ItemStack) {
        return par1ItemStack != null && (par1ItemStack.getItem() instanceof ItemArmor ? ((ItemArmor) par1ItemStack.getItem()).armorType == this.armorType
                : ((par1ItemStack.getItem().itemID == Block.pumpkin.blockID || par1ItemStack.getItem().itemID == Item.skull.itemID) && this.armorType == 0));
    }

    /**
     * Returns the icon index on items.png that is used as background image of the
     * slot.
     */
    public Icon getBackgroundIconIndex() {
        return ItemArmor.func_94602_b(this.armorType);
    }
}
