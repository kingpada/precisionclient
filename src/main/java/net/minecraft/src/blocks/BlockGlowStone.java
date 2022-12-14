package net.minecraft.src.blocks;

import net.lax1dude.eaglercraft.EaglercraftRandom;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;

public class BlockGlowStone extends Block {
    public BlockGlowStone(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i'
     * (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, EaglercraftRandom par2Random) {
        return MathHelper.clamp_int(this.quantityDropped(par2Random) + par2Random.nextInt(par1 + 1), 1, 4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(EaglercraftRandom par1Random) {
        return 2 + par1Random.nextInt(3);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, EaglercraftRandom par2Random, int par3) {
        return Item.lightStoneDust.itemID;
    }
}
