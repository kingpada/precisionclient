package net.minecraft.src.entity;

import net.minecraft.src.entity.EntityLiving;

public interface IRangedAttackMob {
    /**
     * Attack the specified entity using a ranged attack.
     */
    void attackEntityWithRangedAttack(EntityLiving var1, float var2);
}
