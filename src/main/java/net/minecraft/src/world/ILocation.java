package net.minecraft.src.world;

import net.minecraft.src.position.IPosition;
import net.minecraft.src.world.World;

public interface ILocation extends IPosition {
    World getWorld();
}
