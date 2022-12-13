package net.minecraft.src.items.objects;

import net.minecraft.src.items.Item;

public class ItemSimpleFoiled extends Item {
    public ItemSimpleFoiled(int par1) {
        super(par1);
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }
}
