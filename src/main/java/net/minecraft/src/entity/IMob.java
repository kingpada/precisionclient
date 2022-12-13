package net.minecraft.src.entity;

public interface IMob extends IAnimals {
    /**
     * Entity selector for IMob types.
     */
    IEntitySelector mobSelector = new FilterIMob();
}
