package net.minecraft.src;

import net.lax1dude.eaglercraft.sp.EaglercraftRandom;

import java.util.List;

public class ComponentStrongholdCrossing extends ComponentStronghold {
    protected final EnumDoor doorType;
    private final boolean field_74996_b;
    private final boolean field_74997_c;
    private final boolean field_74995_d;
    private final boolean field_74999_h;

    public ComponentStrongholdCrossing(int par1, EaglercraftRandom par2Random, StructureBoundingBox par3StructureBoundingBox,
                                       int par4) {
        super(par1);
        this.coordBaseMode = par4;
        this.doorType = this.getRandomDoor(par2Random);
        this.boundingBox = par3StructureBoundingBox;
        this.field_74996_b = par2Random.nextBoolean();
        this.field_74997_c = par2Random.nextBoolean();
        this.field_74995_d = par2Random.nextBoolean();
        this.field_74999_h = par2Random.nextInt(3) > 0;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current
     * Location of StructGen
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, EaglercraftRandom par3Random) {
        int var4 = 3;
        int var5 = 5;

        if (this.coordBaseMode == 1 || this.coordBaseMode == 2) {
            var4 = 8 - var4;
            var5 = 8 - var5;
        }

        this.getNextComponentNormal((ComponentStrongholdStairs2) par1StructureComponent, par2List, par3Random, 5, 1);

        if (this.field_74996_b) {
            this.getNextComponentX((ComponentStrongholdStairs2) par1StructureComponent, par2List, par3Random, var4, 1);
        }

        if (this.field_74997_c) {
            this.getNextComponentX((ComponentStrongholdStairs2) par1StructureComponent, par2List, par3Random, var5, 7);
        }

        if (this.field_74995_d) {
            this.getNextComponentZ((ComponentStrongholdStairs2) par1StructureComponent, par2List, par3Random, var4, 1);
        }

        if (this.field_74999_h) {
            this.getNextComponentZ((ComponentStrongholdStairs2) par1StructureComponent, par2List, par3Random, var5, 7);
        }
    }

    public static ComponentStrongholdCrossing findValidPlacement(List par0List, EaglercraftRandom par1Random, int par2, int par3,
                                                                 int par4, int par5, int par6) {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -4, -3, 0, 10,
                9, 11, par5);
        return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null
                ? new ComponentStrongholdCrossing(par6, par1Random, var7, par5)
                : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob
     * Spawners, it closes Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, EaglercraftRandom par2Random,
                                     StructureBoundingBox par3StructureBoundingBox) {
        if (this.isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox)) {
            return false;
        } else {
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 9, 8, 10, true, par2Random,
                    StructureStrongholdPieces.getStrongholdStones());
            this.placeDoor(par1World, par2Random, par3StructureBoundingBox, this.doorType, 4, 3, 0);

            if (this.field_74996_b) {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 1, 0, 5, 3, 0, 0, false);
            }

            if (this.field_74995_d) {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 3, 1, 9, 5, 3, 0, 0, false);
            }

            if (this.field_74997_c) {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 7, 0, 7, 9, 0, 0, false);
            }

            if (this.field_74999_h) {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 5, 7, 9, 7, 9, 0, 0, false);
            }

            this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 10, 7, 3, 10, 0, 0, false);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 2, 1, 8, 2, 6, false, par2Random,
                    StructureStrongholdPieces.getStrongholdStones());
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 1, 5, 4, 4, 9, false, par2Random,
                    StructureStrongholdPieces.getStrongholdStones());
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 8, 1, 5, 8, 4, 9, false, par2Random,
                    StructureStrongholdPieces.getStrongholdStones());
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 4, 7, 3, 4, 9, false, par2Random,
                    StructureStrongholdPieces.getStrongholdStones());
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 3, 5, 3, 3, 6, false, par2Random,
                    StructureStrongholdPieces.getStrongholdStones());
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 4, 3, 3, 4, Block.stoneSingleSlab.blockID,
                    Block.stoneSingleSlab.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 6, 3, 4, 6, Block.stoneSingleSlab.blockID,
                    Block.stoneSingleSlab.blockID, false);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 1, 7, 7, 1, 8, false, par2Random,
                    StructureStrongholdPieces.getStrongholdStones());
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 9, 7, 1, 9, Block.stoneSingleSlab.blockID,
                    Block.stoneSingleSlab.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 7, 7, 2, 7, Block.stoneSingleSlab.blockID,
                    Block.stoneSingleSlab.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 5, 7, 4, 5, 9, Block.stoneSingleSlab.blockID,
                    Block.stoneSingleSlab.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 5, 7, 8, 5, 9, Block.stoneSingleSlab.blockID,
                    Block.stoneSingleSlab.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 5, 7, 7, 5, 9, Block.stoneDoubleSlab.blockID,
                    Block.stoneDoubleSlab.blockID, false);
            this.placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 0, 6, 5, 6, par3StructureBoundingBox);
            return true;
        }
    }
}
