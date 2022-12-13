package net.minecraft.src.entity;

import net.minecraft.src.world.WorldRenderer;

import java.util.Comparator;

public class EntitySorter implements Comparator {
    /**
     * Entity position X
     */
    private final double entityPosX;

    /**
     * Entity position Y
     */
    private final double entityPosY;

    /**
     * Entity position Z
     */
    private final double entityPosZ;

    private final boolean reverse;

    public EntitySorter(Entity par1Entity) {
        this.entityPosX = -par1Entity.posX;
        this.entityPosY = -par1Entity.posY;
        this.entityPosZ = -par1Entity.posZ;
        this.reverse = false;
    }

    public EntitySorter(Entity par1Entity, boolean reverse) {
        this.entityPosX = -par1Entity.posX;
        this.entityPosY = -par1Entity.posY;
        this.entityPosZ = -par1Entity.posZ;
        this.reverse = reverse;
    }

    /**
     * Sorts the two world renderers according to their distance to a given entity.
     */
    public int sortByDistanceToEntity(WorldRenderer par1WorldRenderer, WorldRenderer par2WorldRenderer) {
        double var3 = (double) par1WorldRenderer.posXPlus + this.entityPosX;
        double var5 = (double) par1WorldRenderer.posYPlus + this.entityPosY;
        double var7 = (double) par1WorldRenderer.posZPlus + this.entityPosZ;
        double var9 = (double) par2WorldRenderer.posXPlus + this.entityPosX;
        double var11 = (double) par2WorldRenderer.posYPlus + this.entityPosY;
        double var13 = (double) par2WorldRenderer.posZPlus + this.entityPosZ;
        return (int) ((var3 * var3 + var5 * var5 + var7 * var7 - (var9 * var9 + var11 * var11 + var13 * var13)) * 1024.0D);
    }

    public int compare(Object par1Obj, Object par2Obj) {
        return (reverse ? -1 : 1) * this.sortByDistanceToEntity((WorldRenderer) par1Obj, (WorldRenderer) par2Obj);
    }
}
