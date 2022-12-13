package net.minecraft.src.render.objects;

import net.minecraft.src.blocks.Block;
import net.minecraft.src.entity.EntityMinecart;
import net.minecraft.src.entity.EntityMinecartMobSpawner;

public class RenderMinecartMobSpawner extends RenderMinecart {
    protected void func_98192_a(EntityMinecartMobSpawner par1EntityMinecartMobSpawner, float par2, Block par3Block, int par4) {
        super.renderBlockInMinecart(par1EntityMinecartMobSpawner, par2, par3Block, par4);
    }

    /**
     * Renders the block that is inside the minecart.
     */
    protected void renderBlockInMinecart(EntityMinecart par1EntityMinecart, float par2, Block par3Block, int par4) {
        this.func_98192_a((EntityMinecartMobSpawner) par1EntityMinecart, par2, par3Block, par4);
    }
}
