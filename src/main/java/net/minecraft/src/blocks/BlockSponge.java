package net.minecraft.src.blocks;

import net.minecraft.src.creative.CreativeTabs;
import net.minecraft.src.materials.Material;

public class BlockSponge extends Block {
    protected BlockSponge(int par1) {
        super(par1, Material.sponge);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
