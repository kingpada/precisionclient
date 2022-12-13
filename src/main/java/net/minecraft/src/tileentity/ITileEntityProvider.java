package net.minecraft.src.tileentity;

import net.minecraft.src.tileentity.TileEntity;
import net.minecraft.src.world.World;

public interface ITileEntityProvider {
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the
     * block.
     */
    TileEntity createNewTileEntity(World var1);
}
