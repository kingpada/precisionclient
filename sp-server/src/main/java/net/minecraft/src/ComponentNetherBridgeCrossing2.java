package net.minecraft.src;

import net.lax1dude.eaglercraft.sp.EaglercraftRandom;

import java.util.List;

public class ComponentNetherBridgeCrossing2 extends ComponentNetherBridgePiece {
    public ComponentNetherBridgeCrossing2(int par1, EaglercraftRandom par2Random, StructureBoundingBox par3StructureBoundingBox,
                                          int par4) {
        super(par1);
        this.coordBaseMode = par4;
        this.boundingBox = par3StructureBoundingBox;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current
     * Location of StructGen
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, EaglercraftRandom par3Random) {
        this.getNextComponentNormal((ComponentNetherBridgeStartPiece) par1StructureComponent, par2List, par3Random, 1,
                0, true);
        this.getNextComponentX((ComponentNetherBridgeStartPiece) par1StructureComponent, par2List, par3Random, 0, 1,
                true);
        this.getNextComponentZ((ComponentNetherBridgeStartPiece) par1StructureComponent, par2List, par3Random, 0, 1,
                true);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find
     * enough room to place it.
     */
    public static ComponentNetherBridgeCrossing2 createValidComponent(List par0List, EaglercraftRandom par1Random, int par2,
                                                                      int par3, int par4, int par5, int par6) {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, 0, 0, 5, 7,
                5, par5);
        return isAboveGround(var7) && StructureComponent.findIntersecting(par0List, var7) == null
                ? new ComponentNetherBridgeCrossing2(par6, par1Random, var7, par5)
                : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob
     * Spawners, it closes Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, EaglercraftRandom par2Random,
                                     StructureBoundingBox par3StructureBoundingBox) {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 1, 4, Block.netherBrick.blockID,
                Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 4, 5, 4, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 5, 0, Block.netherBrick.blockID,
                Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 0, 4, 5, 0, Block.netherBrick.blockID,
                Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 4, 0, 5, 4, Block.netherBrick.blockID,
                Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 4, 4, 5, 4, Block.netherBrick.blockID,
                Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 0, 4, 6, 4, Block.netherBrick.blockID,
                Block.netherBrick.blockID, false);

        for (int var4 = 0; var4 <= 4; ++var4) {
            for (int var5 = 0; var5 <= 4; ++var5) {
                this.fillCurrentPositionBlocksDownwards(par1World, Block.netherBrick.blockID, 0, var4, -1, var5,
                        par3StructureBoundingBox);
            }
        }

        return true;
    }
}
