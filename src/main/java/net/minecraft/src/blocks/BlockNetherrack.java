package net.minecraft.src.blocks;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockNetherrack extends Block {
    public BlockNetherrack(int par1) {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
