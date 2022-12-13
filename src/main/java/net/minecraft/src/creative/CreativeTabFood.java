package net.minecraft.src.creative;

import net.minecraft.src.items.Item;

public final class CreativeTabFood extends CreativeTabs {
    CreativeTabFood(int par1, String par2Str) {
        super(par1, par2Str);
    }

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {
        return Item.appleRed.itemID;
    }
}
