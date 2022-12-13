package net.minecraft.src.items.objects;

import net.minecraft.src.packet.Packet;
import net.minecraft.src.world.World;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.items.Item;

public class ItemMapBase extends Item {
    protected ItemMapBase(int par1) {
        super(par1);
    }

    /**
     * false for all Items except sub-classes of ItemMapBase
     */
    public boolean isMap() {
        return true;
    }

    /**
     * returns null if no update is to be sent
     */
    public Packet createMapDataPacket(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return null;
    }
}
