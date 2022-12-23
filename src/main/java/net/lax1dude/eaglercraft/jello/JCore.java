package net.lax1dude.eaglercraft.jello;

import net.minecraft.client.Minecraft;
import net.minecraft.src.entity.EntityClientPlayerMP;
import net.minecraft.src.world.WorldClient;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.packet.packets.Packet7UseEntity;
import net.minecraft.src.potion.Potion;

public class JCore {
    public static Minecraft mc;

    public EntityClientPlayerMP player() {
        return JCore.mc.thePlayer;
    }

    public WorldClient world() {
        return JCore.mc.theWorld;
    }

    public float normalise(double d, double d2, double d3) {
        double d4 = d3 - d2;
        double d5 = d - d2;
        return (float)(d5 - Math.floor(d5 / d4) * d4 + d2);
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}
