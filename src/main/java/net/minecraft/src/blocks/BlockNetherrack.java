package net.minecraft.src.blocks;

import net.minecraft.src.creative.CreativeTabs;
import net.minecraft.src.materials.Material;

public class BlockNetherrack extends Block {
    public BlockNetherrack(int par1) {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
