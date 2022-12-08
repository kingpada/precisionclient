package net.minecraft.src.containers;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.entity.EntitySheep;

public class ContainerSheep extends Container {
    final EntitySheep field_90034_a;

    public ContainerSheep(EntitySheep par1EntitySheep) {
        this.field_90034_a = par1EntitySheep;
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return false;
    }
}
