package net.minecraft.src.events;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.nbt.NBTTagCompound;

public interface IPlayerFileData {
    /**
     * Writes the player data to disk from the specified PlayerEntityMP.
     */
    void writePlayerData(EntityPlayer var1);

    /**
     * Reads the player data from disk into the specified PlayerEntityMP.
     */
    NBTTagCompound readPlayerData(EntityPlayer var1);

    /**
     * Returns an array of usernames for which player.dat exists for.
     */
    String[] getAvailablePlayerDat();
}
