package net.minecraft.src.blocks;

import net.minecraft.src.world.ILocatableSource;
import net.minecraft.src.tileentity.TileEntity;

public interface IBlockSource extends ILocatableSource {
    double getX();

    double getY();

    double getZ();

    int getXInt();

    int getYInt();

    int getZInt();

    int getBlockMetadata();

    TileEntity getBlockTileEntity();
}
