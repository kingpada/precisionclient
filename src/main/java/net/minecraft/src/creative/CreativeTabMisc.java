package net.minecraft.src.creative;

import net.minecraft.src.enums.EnumEnchantmentType;
import net.minecraft.src.items.Item;

import java.util.List;

public final class CreativeTabMisc extends CreativeTabs {
    CreativeTabMisc(int par1, String par2Str) {
        super(par1, par2Str);
    }

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {
        return Item.bucketLava.itemID;
    }

    /**
     * only shows items which have tabToDisplayOn == this
     */
    public void displayAllReleventItems(List par1List) {
        super.displayAllReleventItems(par1List);
        this.func_92116_a(par1List, EnumEnchantmentType.all);
    }
}
