package net.minecraft.src.blocks;

import net.minecraft.src.creative.CreativeTabs;
import net.minecraft.src.materials.Material;

public class BlockOreStorage extends Block {
    public BlockOreStorage(int par1) {
        super(par1, Material.iron);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
