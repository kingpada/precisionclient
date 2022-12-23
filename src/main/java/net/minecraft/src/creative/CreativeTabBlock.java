package net.minecraft.src.creative;

import net.minecraft.src.blocks.Block;

public final class CreativeTabBlock extends CreativeTabs {
    CreativeTabBlock(int par1, String par2Str) {
        super(par1, par2Str);
    }

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {
        return Block.brick.blockID;
    }
}