package net.minecraft.src.blocks;

import net.minecraft.src.creative.CreativeTabs;
import net.minecraft.src.materials.Material;

public class BlockDirt extends Block {
    protected BlockDirt(int par1) {
        super(par1, Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
