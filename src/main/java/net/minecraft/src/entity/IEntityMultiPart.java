package net.minecraft.src.entity;

import net.minecraft.src.entity.EntityDragonPart;
import net.minecraft.src.player.actions.DamageSource;
import net.minecraft.src.world.World;

public interface IEntityMultiPart {
    World func_82194_d();

    boolean attackEntityFromPart(EntityDragonPart var1, DamageSource var2, int var3);
}
