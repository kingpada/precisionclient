package net.minecraft.src;

import net.minecraft.src.entity.EntityDragonPart;

public interface IEntityMultiPart {
    World func_82194_d();

    boolean attackEntityFromPart(EntityDragonPart var1, DamageSource var2, int var3);
}
