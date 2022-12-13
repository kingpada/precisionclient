package net.minecraft.src.entity;


import net.minecraft.src.utils.MathHelper;
import net.minecraft.src.world.World;
import net.minecraft.src.nbt.NBTTagCompound;

public class EntityEnderEye extends Entity {
    public int field_70226_a = 0;

    /**
     * 'x' location the eye should float towards.
     */
    private double targetX;

    /**
     * 'y' location the eye should float towards.
     */
    private double targetY;

    /**
     * 'z' location the eye should float towards.
     */
    private double targetZ;
    private int despawnTimer;
    private boolean shatterOrDrop;

    public EntityEnderEye() {
        super();
        this.setSize(0.25F, 0.25F);
    }

    protected void entityInit() {
    }

    /**
     * Checks if the entity is in range to render by using the past in distance and
     * comparing it to its average edge length * 64 * renderDistanceWeight Args:
     * distance
     */
    public boolean isInRangeToRenderDist(double par1) {
        double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        var3 *= 64.0D;
        return par1 < var3 * var3;
    }

    public EntityEnderEye(World par1World, double par2, double par4, double par6) {
        super();
        this.setWorld(par1World);
        this.despawnTimer = 0;
        this.setSize(0.25F, 0.25F);
        this.setPosition(par2, par4, par6);
        this.yOffset = 0.0F;
    }

    /**
     * The location the eye should float/move towards. Currently used for moving
     * towards the nearest stronghold. Args: strongholdX, strongholdY, strongholdZ
     */
    public void moveTowards(double par1, int par3, double par4) {
        double var6 = par1 - this.posX;
        double var8 = par4 - this.posZ;
        float var10 = MathHelper.sqrt_double(var6 * var6 + var8 * var8);

        if (var10 > 12.0F) {
            this.targetX = this.posX + var6 / (double) var10 * 12.0D;
            this.targetZ = this.posZ + var8 / (double) var10 * 12.0D;
            this.targetY = this.posY + 8.0D;
        } else {
            this.targetX = par1;
            this.targetY = par3;
            this.targetZ = par4;
        }

        this.despawnTimer = 0;
        this.shatterOrDrop = this.rand.nextInt(5) > 0;
    }

    /**
     * Sets the velocity to the args. Args: x, y, z
     */
    public void setVelocity(double par1, double par3, double par5) {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, var7) * 180.0D / Math.PI);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float) (Math.atan2(this.motionY, var1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

        float var10 = 0.25F;

        if (this.isInWater()) {
            for (int var3 = 0; var3 < 4; ++var3) {
                this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double) var10, this.posY - this.motionY * (double) var10, this.posZ - this.motionZ * (double) var10, this.motionX, this.motionY, this.motionZ);
            }
        } else {
            this.worldObj.spawnParticle("portal", this.posX - this.motionX * (double) var10 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * (double) var10 - 0.5D,
                    this.posZ - this.motionZ * (double) var10 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
    }

    public float getShadowSize() {
        return 0.0F;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1) {
        return 1.0F;
    }

    public int getBrightnessForRender(float par1) {
        return 15728880;
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    public boolean canAttackWithItem() {
        return false;
    }
}
