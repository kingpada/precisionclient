package net.minecraft.src.entity;

public interface IBossDisplayData {
    int getMaxHealth();

    /**
     * Returns the health points of the dragon.
     */
    int getBossHealth();

    /**
     * Gets the username of the entity.
     */
    String getEntityName();
}
