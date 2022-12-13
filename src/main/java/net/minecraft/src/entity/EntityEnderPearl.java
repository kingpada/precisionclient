package net.minecraft.src.entity;

import net.minecraft.src.player.actions.DamageSource;
import net.minecraft.src.position.MovingObjectPosition;
import net.minecraft.src.world.World;

public class EntityEnderPearl extends EntityThrowable {
    public EntityEnderPearl() {
        super();
    }

    public EntityEnderPearl(World par1World, EntityLiving par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityEnderPearl(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.entityHit != null) {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
        }

        for (int var2 = 0; var2 < 32; ++var2) {
            this.worldObj.spawnParticle("portal", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
        }
    }
}
