package net.minecraft.src.render.objects;

import net.lax1dude.eaglercraft.EaglerAdapter;
import net.minecraft.src.tileentity.TileEntitySkullRenderer;
import net.minecraft.src.blocks.Block;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPlayerSP;
import net.minecraft.src.enums.EnumArmorMaterial;
import net.minecraft.src.items.Item;
import net.minecraft.src.items.objects.ItemArmor;
import net.minecraft.src.items.objects.ItemStack;
import net.minecraft.src.model.objects.ModelBiped;
import net.minecraft.src.render.components.RenderLiving;

public abstract class RenderBiped extends RenderLiving {
    protected ModelBiped modelBipedMain;
    protected float field_77070_b;
    protected ModelBiped field_82423_g;
    protected ModelBiped field_82425_h;

    /**
     * List of armor texture filenames.
     */
    private static final String[] bipedArmorFilenamePrefix = new String[]{"cloth", "chain", "iron", "diamond", "gold"};

    public RenderBiped(ModelBiped par1ModelBiped, float par2) {
        this(par1ModelBiped, par2, 1.0F);
    }

    public RenderBiped(ModelBiped par1ModelBiped, float par2, float par3) {
        super(par1ModelBiped, par2);
        this.modelBipedMain = par1ModelBiped;
        this.field_77070_b = par3;
        this.func_82421_b();
    }

    protected void func_82421_b() {
        this.field_82423_g = new ModelBiped(1.0F);
        this.field_82425_h = new ModelBiped(0.5F);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
        ItemStack var4 = par1EntityLiving.getCurrentArmor(3 - par2);

        if (var4 != null) {
            Item var5 = var4.getItem();

            if (var5 instanceof ItemArmor) {
                ItemArmor var6 = (ItemArmor) var5;
                this.loadTexture("/armor/" + bipedArmorFilenamePrefix[var6.renderIndex] + "_" + (par2 == 2 ? 2 : 1) + ".png");
                ModelBiped var7 = par2 == 2 ? this.field_82425_h : this.field_82423_g;
                var7.bipedHead.showModel = par2 == 0;
                var7.bipedHeadwear.showModel = par2 == 0;
                var7.bipedBody.showModel = par2 == 1 || par2 == 2;
                var7.bipedRightArm.showModel = par2 == 1;
                var7.bipedLeftArm.showModel = par2 == 1;
                var7.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
                var7.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
                this.setRenderPassModel(var7);

                if (var7 != null) {
                    var7.onGround = this.mainModel.onGround;
                }

                if (var7 != null) {
                    var7.isRiding = this.mainModel.isRiding;
                }

                if (var7 != null) {
                    var7.isChild = this.mainModel.isChild;
                }

                float var8 = 1.0F;

                if (var6.getArmorMaterial() == EnumArmorMaterial.CLOTH) {
                    int var9 = var6.getColor(var4);
                    float var10 = (float) (var9 >> 16 & 255) / 255.0F;
                    float var11 = (float) (var9 >> 8 & 255) / 255.0F;
                    float var12 = (float) (var9 & 255) / 255.0F;
                    EaglerAdapter.glColor3f(var8 * var10, var8 * var11, var8 * var12);

                    if (var4.isItemEnchanted()) {
                        return 31;
                    }

                    return 16;
                }

                EaglerAdapter.glColor3f(var8, var8, var8);

                if (var4.isItemEnchanted()) {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }

    protected void func_82408_c(EntityLiving par1EntityLiving, int par2, float par3) {
        ItemStack var4 = par1EntityLiving.getCurrentArmor(3 - par2);

        if (var4 != null) {
            Item var5 = var4.getItem();

            if (var5 instanceof ItemArmor) {
                ItemArmor var6 = (ItemArmor) var5;
                this.loadTexture("/armor/" + bipedArmorFilenamePrefix[var6.renderIndex] + "_" + (par2 == 2 ? 2 : 1) + "_b.png");
                float var7 = 1.0F;
                EaglerAdapter.glColor3f(var7, var7, var7);
            }
        }
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        float var10 = 1.0F;
        EaglerAdapter.glColor3f(var10, var10, var10);
        ItemStack var11 = par1EntityLiving.getHeldItem();
        this.func_82420_a(par1EntityLiving, var11);
        double var12 = par4 - (double) par1EntityLiving.yOffset;

        if (par1EntityLiving.isSneaking() && !(par1EntityLiving instanceof EntityPlayerSP)) {
            var12 -= 0.125D;
        }

        super.doRenderLiving(par1EntityLiving, par2, var12, par6, par8, par9);
        this.field_82423_g.aimedBow = this.field_82425_h.aimedBow = this.modelBipedMain.aimedBow = false;
        this.field_82423_g.isSneak = this.field_82425_h.isSneak = this.modelBipedMain.isSneak = false;
        this.field_82423_g.heldItemRight = this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = 0;
    }

    protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {
        this.field_82423_g.heldItemRight = this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = par2ItemStack != null ? 1 : 0;
        this.field_82423_g.isSneak = this.field_82425_h.isSneak = this.modelBipedMain.isSneak = par1EntityLiving.isSneaking();
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
        float var3 = 1.0F;
        EaglerAdapter.glColor3f(var3, var3, var3);
        super.renderEquippedItems(par1EntityLiving, par2);
        ItemStack var4 = par1EntityLiving.getHeldItem();
        ItemStack var5 = par1EntityLiving.getCurrentArmor(3);
        float var6;

        if (var5 != null) {
            EaglerAdapter.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);

            if (var5.getItem().itemID < 256) {
                if (RenderBlocks.renderItemIn3d(Block.blocksList[var5.itemID].getRenderType())) {
                    var6 = 0.625F;
                    EaglerAdapter.glTranslatef(0.0F, -0.25F, 0.0F);
                    EaglerAdapter.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    EaglerAdapter.glScalef(var6, -var6, -var6);
                }

                this.renderManager.itemRenderer.renderItem(par1EntityLiving, var5, 0);
            } else if (var5.getItem().itemID == Item.skull.itemID) {
                var6 = 1.0625F;
                EaglerAdapter.glScalef(var6, -var6, -var6);
                String var7 = "";

                if (var5.hasTagCompound() && var5.getTagCompound().hasKey("SkullOwner")) {
                    var7 = var5.getTagCompound().getString("SkullOwner");
                }

                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var5.getItemDamage(), var7);
            }

            EaglerAdapter.glPopMatrix();
        }

        if (var4 != null) {
            EaglerAdapter.glPushMatrix();

            if (this.mainModel.isChild) {
                var6 = 0.5F;
                EaglerAdapter.glTranslatef(0.0F, 0.625F, 0.0F);
                EaglerAdapter.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
                EaglerAdapter.glScalef(var6, var6, var6);
            }

            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            EaglerAdapter.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            if (var4.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())) {
                var6 = 0.5F;
                EaglerAdapter.glTranslatef(0.0F, 0.1875F, -0.3125F);
                var6 *= 0.75F;
                EaglerAdapter.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                EaglerAdapter.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                EaglerAdapter.glScalef(-var6, -var6, var6);
            } else if (var4.itemID == Item.bow.itemID) {
                var6 = 0.625F;
                EaglerAdapter.glTranslatef(0.0F, 0.125F, 0.3125F);
                EaglerAdapter.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                EaglerAdapter.glScalef(var6, -var6, var6);
                EaglerAdapter.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                EaglerAdapter.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (Item.itemsList[var4.itemID].isFull3D()) {
                var6 = 0.625F;

                if (Item.itemsList[var4.itemID].shouldRotateAroundWhenRendering()) {
                    EaglerAdapter.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    EaglerAdapter.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                this.func_82422_c();
                EaglerAdapter.glScalef(var6, -var6, var6);
                EaglerAdapter.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                EaglerAdapter.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                var6 = 0.375F;
                EaglerAdapter.glTranslatef(0.25F, 0.1875F, -0.1875F);
                EaglerAdapter.glScalef(var6, var6, var6);
                EaglerAdapter.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                EaglerAdapter.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                EaglerAdapter.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            this.renderManager.itemRenderer.renderItem(par1EntityLiving, var4, 0);

            if (var4.getItem().requiresMultipleRenderPasses()) {
                this.renderManager.itemRenderer.renderItem(par1EntityLiving, var4, 1);
            }

            EaglerAdapter.glPopMatrix();
        }
    }

    protected void func_82422_c() {
        EaglerAdapter.glTranslatef(0.0F, 0.1875F, 0.0F);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method,
     * always casting down its argument and then handing it off to a worker function
     * which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void
     * doRender(T entity, double d, double d1, double d2, float f, float f1). But
     * JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderLiving((EntityLiving) par1Entity, par2, par4, par6, par8, par9);
    }

}