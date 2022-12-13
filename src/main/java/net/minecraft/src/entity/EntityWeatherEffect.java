package net.minecraft.src.entity;

import net.minecraft.src.world.World;

public abstract class EntityWeatherEffect extends Entity {
    public EntityWeatherEffect(World par1World) {
        super();
        this.setWorld(par1World);
    }
}
