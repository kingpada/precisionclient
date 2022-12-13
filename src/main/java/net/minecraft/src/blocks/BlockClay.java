package net.minecraft.src.blocks;

import net.lax1dude.eaglercraft.EaglercraftRandom;
import net.minecraft.src.creative.CreativeTabs;
import net.minecraft.src.items.Item;
import net.minecraft.src.materials.Material;

public class BlockClay extends Block {
    public BlockClay(int par1) {
        super(par1, Material.clay);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, EaglercraftRandom par2Random, int par3) {
        return Item.clay.itemID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(EaglercraftRandom par1Random) {
        return 4;
    }
}
