package net.minecraft.src;

import net.lax1dude.eaglercraft.sp.EaglercraftRandom;

public class WorldGenTallGrass extends WorldGenerator {
    /**
     * Stores ID for WorldGenTallGrass
     */
    private final int tallGrassID;
    private final int tallGrassMetadata;

    public WorldGenTallGrass(int par1, int par2) {
        this.tallGrassID = par1;
        this.tallGrassMetadata = par2;
    }

    public boolean generate(World par1World, EaglercraftRandom par2Random, int par3, int par4, int par5) {
        int var11;

        for (boolean var6 = false; ((var11 = par1World.getBlockId(par3, par4, par5)) == 0
                || var11 == Block.leaves.blockID) && par4 > 0; --par4) {
        }

        for (int var7 = 0; var7 < 128; ++var7) {
            int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var8, var9, var10)
                    && Block.blocksList[this.tallGrassID].canBlockStay(par1World, var8, var9, var10)) {
                par1World.setBlock(var8, var9, var10, this.tallGrassID, this.tallGrassMetadata, 2);
            }
        }

        return true;
    }
}
