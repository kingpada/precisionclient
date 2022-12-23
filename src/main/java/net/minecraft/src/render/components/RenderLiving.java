package net.minecraft.src.render.components;

import net.lax1dude.eaglercraft.*;
import net.lax1dude.eaglercraft.adapter.Tessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.src.render.FontRenderer;
import net.minecraft.src.utils.MathHelper;
import net.minecraft.src.render.OpenGlHelper;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityArrow;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityOtherPlayerMP;
import net.minecraft.src.model.ModelBase;
import net.minecraft.src.model.objects.ModelBox;
import net.minecraft.src.model.objects.ModelRenderer;
import net.minecraft.src.render.Render;
import net.minecraft.src.render.RenderHelper;


public abstract class RenderLiving extends Render {
    protected ModelBase mainModel;

    /**
     * The model to be used during the render passes.
     */
    protected ModelBase renderPassModel;

    public RenderLiving(ModelBase par1ModelBase, float par2) {
        this.mainModel = par1ModelBase;
        this.shadowSize = par2;
    }

    /**
     * Sets the model to be used in the current render pass (the first render pass
     * is done after the primary model is rendered) Args: model
     */
    public void setRenderPassModel(ModelBase par1ModelBase) {
        this.renderPassModel = par1ModelBase;
    }

    /**
     * Returns a rotation angle that is inbetween two other rotation angles. par1
     * and par2 are the angles between which to interpolate, par3 is probably a
     * float between 0.0 and 1.0 that tells us where "between" the two angles we
     * are. Example: par1 = 30, par2 = 50, par3 = 0.5, then return = 40
     */
    private float interpolateRotation(float par1, float par2, float par3) {
        float var4;

        for (var4 = par2 - par1; var4 < -180.0F; var4 += 360.0F) {
        }

        while (var4 >= 180.0F) {
            var4 -= 360.0F;
        }

        return par1 + par3 * var4;
    }

    private static final TextureLocation glint = new TextureLocation("%blur%/misc/glint.png");

    public void doRenderLiving(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        EaglerAdapter.glPushMatrix();
        EaglerAdapter.glDisable(EaglerAdapter.GL_CULL_FACE);
        this.mainModel.onGround = this.renderSwingProgress(entity, par9);

        if (this.renderPassModel != null) {
            this.renderPassModel.onGround = this.mainModel.onGround;
        }

        this.mainModel.isRiding = entity.isRiding();

        if (this.renderPassModel != null) {
            this.renderPassModel.isRiding = this.mainModel.isRiding;
        }

        this.mainModel.isChild = entity.isChild();

        if (this.renderPassModel != null) {
            this.renderPassModel.isChild = this.mainModel.isChild;
        }

        try {
            float var10 = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, par9);
            float var11 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, par9);
            float var12 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9;
            this.renderLivingAt(entity, par2, par4, par6);
            float var13 = this.handleRotationFloat(entity, par9);
            this.rotateCorpse(entity, var13, var10, par9);
            float var14 = 0.0625F;
            EaglerAdapter.glEnable(EaglerAdapter.GL_RESCALE_NORMAL);
            EaglerAdapter.glScalef(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback(entity, par9);
            EaglerAdapter.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
            float var15 = entity.prevLimbYaw + (entity.limbYaw - entity.prevLimbYaw) * par9;
            float var16 = entity.limbSwing - entity.limbYaw * (1.0F - par9);

            if (entity.isChild()) {
                var16 *= 3.0F;
            }

            if (var15 > 1.0F) {
                var15 = 1.0F;
            }

            EaglerAdapter.glEnable(EaglerAdapter.GL_ALPHA_TEST);
            this.mainModel.setLivingAnimations(entity, var16, var15, par9);
            this.renderModel(entity, var16, var15, var13, var11 - var10, var12, var14);
            int var18;
            float var19;
            float var20;
            float var22;

            for (int var17 = 0; var17 < 4; ++var17) {
                var18 = this.shouldRenderPass(entity, var17, par9);

                if (var18 > 0) {
                    this.renderPassModel.setLivingAnimations(entity, var16, var15, par9);
                    this.renderPassModel.render(entity, var16, var15, var13, var11 - var10, var12, var14);

                    if ((var18 & 240) == 16) {
                        this.func_82408_c(entity, var17, par9);
                        this.renderPassModel.render(entity, var16, var15, var13, var11 - var10, var12, var14);
                    }

                    if ((var18 & 15) == 15) {
                        var19 = (float) entity.ticksExisted + par9;
                        glint.bindTexture();
                        EaglerAdapter.glEnable(EaglerAdapter.GL_BLEND);
                        var20 = 0.5F;
                        EaglerAdapter.glColor4f(var20, var20, var20, 1.0F);
                        EaglerAdapter.glDepthFunc(EaglerAdapter.GL_EQUAL);
                        EaglerAdapter.glDepthMask(false);

                        for (int var21 = 0; var21 < 2; ++var21) {
                            EaglerAdapter.glDisable(EaglerAdapter.GL_LIGHTING);
                            var22 = 0.76F;
                            EaglerAdapter.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
                            EaglerAdapter.glBlendFunc(EaglerAdapter.GL_SRC_COLOR, EaglerAdapter.GL_ONE);
                            EaglerAdapter.glMatrixMode(EaglerAdapter.GL_TEXTURE);
                            EaglerAdapter.glLoadIdentity();
                            float var23 = var19 * (0.001F + (float) var21 * 0.003F) * 20.0F;
                            float var24 = 0.33333334F;
                            EaglerAdapter.glScalef(var24, var24, var24);
                            EaglerAdapter.glRotatef(30.0F - (float) var21 * 60.0F, 0.0F, 0.0F, 1.0F);
                            EaglerAdapter.glTranslatef(0.0F, var23, 0.0F);
                            EaglerAdapter.glMatrixMode(EaglerAdapter.GL_MODELVIEW);
                            this.renderPassModel.render(entity, var16, var15, var13, var11 - var10, var12, var14);
                        }

                        EaglerAdapter.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        EaglerAdapter.glMatrixMode(EaglerAdapter.GL_TEXTURE);
                        EaglerAdapter.glDepthMask(true);
                        EaglerAdapter.glLoadIdentity();
                        EaglerAdapter.glMatrixMode(EaglerAdapter.GL_MODELVIEW);
                        EaglerAdapter.glEnable(EaglerAdapter.GL_LIGHTING);
                        EaglerAdapter.glDisable(EaglerAdapter.GL_BLEND);
                        EaglerAdapter.glDepthFunc(EaglerAdapter.GL_LEQUAL);
                    }

                    EaglerAdapter.glDisable(EaglerAdapter.GL_BLEND);
                    EaglerAdapter.glEnable(EaglerAdapter.GL_ALPHA_TEST);
                }
            }

            EaglerAdapter.glDepthMask(true);
            this.renderEquippedItems(entity, par9);
            float var26 = entity.getBrightness(par9);
            var18 = this.getColorMultiplier(entity, var26, par9);
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            EaglerAdapter.glDisable(EaglerAdapter.GL_TEXTURE_2D);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

            if ((var18 >> 24 & 255) > 0 || entity.hurtTime > 0 || entity.deathTime > 0) {
                EaglerAdapter.glDisable(EaglerAdapter.GL_TEXTURE_2D);
                EaglerAdapter.glDisable(EaglerAdapter.GL_ALPHA_TEST);
                EaglerAdapter.glEnable(EaglerAdapter.GL_BLEND);
                EaglerAdapter.glBlendFunc(EaglerAdapter.GL_SRC_ALPHA, EaglerAdapter.GL_ONE_MINUS_SRC_ALPHA);
                EaglerAdapter.glDepthFunc(EaglerAdapter.GL_EQUAL);

                if (entity.hurtTime > 0 || entity.deathTime > 0) {
                    EaglerAdapter.glColor4f(var26, 0.0F, 0.0F, 0.4F);
                    this.mainModel.render(entity, var16, var15, var13, var11 - var10, var12, var14);

                    for (int var27 = 0; var27 < 4; ++var27) {
                        if (this.inheritRenderPass(entity, var27, par9) >= 0) {
                            EaglerAdapter.glColor4f(var26, 0.0F, 0.0F, 0.4F);
                            this.renderPassModel.render(entity, var16, var15, var13, var11 - var10, var12, var14);
                        }
                    }
                }

                if ((var18 >> 24 & 255) > 0) {
                    var19 = (float) (var18 >> 16 & 255) / 255.0F;
                    var20 = (float) (var18 >> 8 & 255) / 255.0F;
                    float var28 = (float) (var18 & 255) / 255.0F;
                    var22 = (float) (var18 >> 24 & 255) / 255.0F;
                    EaglerAdapter.glColor4f(var19, var20, var28, var22);
                    this.mainModel.render(entity, var16, var15, var13, var11 - var10, var12, var14);

                    for (int var29 = 0; var29 < 4; ++var29) {
                        if (this.inheritRenderPass(entity, var29, par9) >= 0) {
                            EaglerAdapter.glColor4f(var19, var20, var28, var22);
                            this.renderPassModel.render(entity, var16, var15, var13, var11 - var10, var12, var14);
                        }
                    }
                }

                EaglerAdapter.glDepthFunc(EaglerAdapter.GL_LEQUAL);
                EaglerAdapter.glDisable(EaglerAdapter.GL_BLEND);
                EaglerAdapter.glEnable(EaglerAdapter.GL_ALPHA_TEST);
                EaglerAdapter.glEnable(EaglerAdapter.GL_TEXTURE_2D);
            }

            EaglerAdapter.glDisable(EaglerAdapter.GL_RESCALE_NORMAL);
        } catch (Exception var25) {
            var25.printStackTrace();
        }

        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        EaglerAdapter.glEnable(EaglerAdapter.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        EaglerAdapter.glEnable(EaglerAdapter.GL_CULL_FACE);
        EaglerAdapter.glPopMatrix();
        this.passSpecialRender(entity, par2, par4, par6);
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityLiving entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.bindTexture(entity);

        if (!entity.isInvisible()) {
            this.mainModel.render(entity, par2, par3, par4, par5, par6, par7);
        } else if (!entity.func_98034_c(Minecraft.getMinecraft().thePlayer)) {
            EaglerAdapter.glPushMatrix();
            EaglerAdapter.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
            EaglerAdapter.glDepthMask(false);
            EaglerAdapter.glEnable(EaglerAdapter.GL_BLEND);
            EaglerAdapter.glBlendFunc(EaglerAdapter.GL_SRC_ALPHA, EaglerAdapter.GL_ONE_MINUS_SRC_ALPHA);
            EaglerAdapter.glAlphaFunc(EaglerAdapter.GL_GREATER, 0.003921569F);
            this.mainModel.render(entity, par2, par3, par4, par5, par6, par7);
            EaglerAdapter.glDisable(EaglerAdapter.GL_BLEND);
            EaglerAdapter.glAlphaFunc(EaglerAdapter.GL_GREATER, 0.1F);
            EaglerAdapter.glPopMatrix();
            EaglerAdapter.glDepthMask(true);
        } else {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
        }
    }

    protected abstract void bindTexture(EntityLiving entity);

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(EntityLiving entity, double par2, double par4, double par6) {
        EaglerAdapter.glTranslatef((float) par2, (float) par4, (float) par6);
    }

    protected void rotateCorpse(EntityLiving entity, float par2, float par3, float par4) {
        EaglerAdapter.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);

        if (entity.deathTime > 0) {
            float var5 = ((float) entity.deathTime + par4 - 1.0F) / 20.0F * 1.6F;
            var5 = MathHelper.sqrt_float(var5);

            if (var5 > 1.0F) {
                var5 = 1.0F;
            }

            EaglerAdapter.glRotatef(var5 * this.getDeathMaxRotation(entity), 0.0F, 0.0F, 1.0F);
        }
    }

    protected float renderSwingProgress(EntityLiving entity, float par2) {
        return entity.getSwingProgress(par2);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving entity, float par2) {
        return (float) entity.ticksExisted + par2;
    }

    protected void renderEquippedItems(EntityLiving entity, float par2) {
    }

    /**
     * renders arrows the Entity has been attacked with, attached to it
     */
    protected void renderArrowsStuckInEntity(EntityLiving entity, float par2) {
        int var3 = entity.getArrowCountInEntity();

        if (var3 > 0) {
            EntityArrow var4 = new EntityArrow(entity.worldObj, entity.posX, entity.posY, entity.posZ);
            EaglercraftRandom var5 = new EaglercraftRandom(entity.entityId);
            RenderHelper.disableStandardItemLighting();

            for (int var6 = 0; var6 < var3; ++var6) {
                EaglerAdapter.glPushMatrix();
                ModelRenderer var7 = this.mainModel.getRandomModelBox(var5);
                ModelBox var8 = (ModelBox) var7.cubeList.get(var5.nextInt(var7.cubeList.size()));
                var7.postRender(0.0625F);
                float var9 = var5.nextFloat();
                float var10 = var5.nextFloat();
                float var11 = var5.nextFloat();
                float var12 = (var8.posX1 + (var8.posX2 - var8.posX1) * var9) / 16.0F;
                float var13 = (var8.posY1 + (var8.posY2 - var8.posY1) * var10) / 16.0F;
                float var14 = (var8.posZ1 + (var8.posZ2 - var8.posZ1) * var11) / 16.0F;
                EaglerAdapter.glTranslatef(var12, var13, var14);
                var9 = var9 * 2.0F - 1.0F;
                var10 = var10 * 2.0F - 1.0F;
                var11 = var11 * 2.0F - 1.0F;
                var9 *= -1.0F;
                var10 *= -1.0F;
                var11 *= -1.0F;
                float var15 = MathHelper.sqrt_float(var9 * var9 + var11 * var11);
                var4.prevRotationYaw = var4.rotationYaw = (float) (Math.atan2(var9, var11) * 180.0D / Math.PI);
                var4.prevRotationPitch = var4.rotationPitch = (float) (Math.atan2(var10, var15) * 180.0D / Math.PI);
                double var16 = 0.0D;
                double var18 = 0.0D;
                double var20 = 0.0D;
                float var22 = 0.0F;
                this.renderManager.renderEntityWithPosYaw(var4, var16, var18, var20, var22, par2);
                EaglerAdapter.glPopMatrix();
            }

            RenderHelper.enableStandardItemLighting();
        }
    }

    protected int inheritRenderPass(EntityLiving entity, int par2, float par3) {
        return this.shouldRenderPass(entity, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving entity, int par2, float par3) {
        return -1;
    }

    protected void func_82408_c(EntityLiving entity, int par2, float par3) {
    }

    protected float getDeathMaxRotation(EntityLiving entity) {
        return 90.0F;
    }

    /**
     * Returns an ARGB int color back. Args: entityLiving, lightBrightness,
     * partialTickTime
     */
    protected int getColorMultiplier(EntityLiving entity, float par2, float par3) {
        return 0;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the
     * model is rendered. Args: entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving entity, float par2) {
    }

    /**
     * Passes the specialRender and renders it
     */
    protected void passSpecialRender(EntityLiving entity, double par2, double par4, double par6) {
        if (Minecraft.isGuiEnabled() && entity != this.renderManager.livingPlayer && !entity.func_98034_c(Minecraft.getMinecraft().thePlayer)
                && (entity.func_94059_bO() || entity.func_94056_bM() && entity == this.renderManager.field_96451_i)) {
            float var8 = 1.6F;
            float var9 = 0.016666668F * var8;
            double var10 = entity.getDistanceSqToEntity(this.renderManager.livingPlayer);
            float var12 = entity.isSneaking() ? 32.0F : 64.0F;

            if (var10 < (double) (var12 * var12)) {
                String entityName = entity.getTranslatedEntityName();

//                if (entityName.equals("KingPada")) {
//                    String Title = EaglerAdapter.coloredText("&4[&cOwner&4]&c ");
//
//                    entityName = Title + entityName;
//                }

                // ADD NAME THING HERE //

                if (entity.isSneaking()) {
                    FontRenderer var14 = this.getFontRendererFromRenderManager();
                    EaglerAdapter.glPushMatrix();
                    EaglerAdapter.glTranslatef((float) par2 + 0.0F, (float) par4 + entity.height + 0.5F, (float) par6);
                    EaglerAdapter.glNormal3f(0.0F, 1.0F, 0.0F);
                    EaglerAdapter.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                    EaglerAdapter.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                    EaglerAdapter.glScalef(-var9, -var9, var9);
                    EaglerAdapter.glDisable(EaglerAdapter.GL_LIGHTING);

                    if (entity instanceof EntityOtherPlayerMP) {
                        int renderType = DefaultSkinRenderer.getPlayerRenderer((EntityOtherPlayerMP) entity);
                        if (renderType == 19) {
                            EaglerAdapter.glTranslatef(0.0F, -32.0f, 0.0F);
                        } else if (DefaultSkinRenderer.isHighPoly(renderType) && Minecraft.getMinecraft().gameSettings.allowFNAWSkins) {
                            EaglerAdapter.glTranslatef(0.0F, 2.0f, 0.0F);
                            if (renderType == 37) {
                                EaglerAdapter.glTranslatef(0.0F, 30.0f, 0.0F);
                            }
                        }
                    } else {
                        EaglerAdapter.glTranslatef(0.0F, 0.25F / var9, 0.0F);
                    }

                    EaglerAdapter.glDepthMask(false);
                    EaglerAdapter.glEnable(EaglerAdapter.GL_BLEND);
                    EaglerAdapter.glBlendFunc(EaglerAdapter.GL_SRC_ALPHA, EaglerAdapter.GL_ONE_MINUS_SRC_ALPHA);
                    Tessellator var15 = Tessellator.instance;
                    EaglerAdapter.glDisable(EaglerAdapter.GL_TEXTURE_2D);
                    EaglerAdapter.glDisable(EaglerAdapter.GL_ALPHA_TEST);
                    var15.startDrawingQuads();
                    int var16 = var14.getStringWidth(entityName) / 2;
                    var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                    var15.addVertex(-var16 - 1, -1.0D, 0.0D);
                    var15.addVertex(-var16 - 1, 8.0D, 0.0D);
                    var15.addVertex(var16 + 1, 8.0D, 0.0D);
                    var15.addVertex(var16 + 1, -1.0D, 0.0D);
                    var15.draw();
                    EaglerAdapter.glEnable(EaglerAdapter.GL_ALPHA_TEST);
                    EaglerAdapter.glEnable(EaglerAdapter.GL_TEXTURE_2D);
                    EaglerAdapter.glDepthMask(true);
                    EaglerAdapter.glAlphaFunc(EaglerAdapter.GL_GREATER, 0.01f);
                    var14.drawString(entityName, -var14.getStringWidth(entityName) / 2, 0, 553648127);
                    EaglerAdapter.glAlphaFunc(EaglerAdapter.GL_GREATER, 0.1f);
                    EaglerAdapter.glEnable(EaglerAdapter.GL_LIGHTING);
                    EaglerAdapter.glDisable(EaglerAdapter.GL_BLEND);
                    EaglerAdapter.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    EaglerAdapter.glPopMatrix();
                } else {
                    this.func_96449_a(entity, par2, par4, par6, entityName, var9, var10);
                }
            }
        }
    }

    protected void func_96449_a(EntityLiving entity, double par2, double par4, double par6, String par8Str, float par9, double par10) {
        if (entity.isPlayerSleeping()) {
            this.renderLivingLabel(entity, par8Str, par2, par4 - 1.5D, par6, 64);
        } else {
            this.renderLivingLabel(entity, par8Str, par2, par4, par6, 64);
        }
    }

    private static final TextureLocation voiceGuiIcons = new TextureLocation("/gui/voice.png");

    /**
     * Draws the debug or playername text above a living
     */
    protected void renderLivingLabel(EntityLiving entity, String par2Str, double par3, double par5, double par7, int par9) {
        double var10 = entity.getDistanceSqToEntity(this.renderManager.livingPlayer);


        if (var10 <= (double) (par9 * par9)) {
            FontRenderer var12 = this.getFontRendererFromRenderManager();
            float var13 = 1.6F;
            float var14 = 0.016666668F * var13;
            EaglerAdapter.glPushMatrix();
            EaglerAdapter.glTranslatef((float) par3 + 0.0F, (float) par5 + entity.height + 0.5F, (float) par7);
            EaglerAdapter.glNormal3f(0.0F, 1.0F, 0.0F);
            EaglerAdapter.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            EaglerAdapter.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            EaglerAdapter.glScalef(-var14, -var14, var14);
            EaglerAdapter.glDisable(EaglerAdapter.GL_LIGHTING);
            EaglerAdapter.glDepthMask(false);
            EaglerAdapter.glDisable(EaglerAdapter.GL_DEPTH_TEST);
            EaglerAdapter.glEnable(EaglerAdapter.GL_BLEND);
            EaglerAdapter.glBlendFunc(EaglerAdapter.GL_SRC_ALPHA, EaglerAdapter.GL_ONE_MINUS_SRC_ALPHA);
            Tessellator var15 = Tessellator.instance;
            byte var16 = 0;

            //if (par2Str.equals("deadmau5")) {
            //	var16 = -10;
            //}

            if (entity instanceof EntityOtherPlayerMP) {
                if (entity.isPlayerSleeping()) {
                    var16 = -60;
                } else {
                    int renderType = DefaultSkinRenderer.getPlayerRenderer((EntityOtherPlayerMP) entity);
                    if (renderType == 19) {
                        var16 = -32;
                    } else if (renderType == 37 && Minecraft.getMinecraft().gameSettings.allowFNAWSkins) {
                        var16 = 30;
                    }
                }
            }

            EaglerAdapter.glDisable(EaglerAdapter.GL_TEXTURE_2D);
            EaglerAdapter.glDisable(EaglerAdapter.GL_ALPHA_TEST);
            var15.startDrawingQuads();
            int var17 = var12.getStringWidth(par2Str) / 2;
            var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            var15.addVertex(-var17 - 1, -1 + var16, 0.0D);
            var15.addVertex(-var17 - 1, 8 + var16, 0.0D);
            var15.addVertex(var17 + 1, 8 + var16, 0.0D);
            var15.addVertex(var17 + 1, -1 + var16, 0.0D);
            var15.draw();
            EaglerAdapter.glEnable(EaglerAdapter.GL_TEXTURE_2D);
            EaglerAdapter.glEnable(EaglerAdapter.GL_ALPHA_TEST);
            EaglerAdapter.glAlphaFunc(EaglerAdapter.GL_GREATER, 0.02f);
            var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, 553648127);
            EaglerAdapter.glAlphaFunc(EaglerAdapter.GL_GREATER, 0.1f);
            EaglerAdapter.glEnable(EaglerAdapter.GL_DEPTH_TEST);
            EaglerAdapter.glDepthMask(true);
            var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, -1);
            EaglerAdapter.glEnable(EaglerAdapter.GL_LIGHTING);
            EaglerAdapter.glDisable(EaglerAdapter.GL_BLEND);
            EaglerAdapter.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            if (entity instanceof EntityOtherPlayerMP) {
                if (EaglerAdapter.getVoiceStatus() == Voice.VoiceStatus.CONNECTED) {

                    String nm = ((EntityOtherPlayerMP) entity).username;
                    boolean mute = EaglerAdapter.getVoiceMuted().contains(nm);
                    if ((mute || EaglerAdapter.getVoiceSpeaking().contains(nm)) && renderManager.voiceTagsDrawnThisFrame.add(nm)) {

                        EaglerAdapter.glDisable(EaglerAdapter.GL_LIGHTING);
                        EaglerAdapter.glDisable(EaglerAdapter.GL_TEXTURE_2D);
                        EaglerAdapter.glDisable(EaglerAdapter.GL_ALPHA_TEST);
                        EaglerAdapter.glDepthMask(false);
                        EaglerAdapter.glDisable(EaglerAdapter.GL_DEPTH_TEST);
                        EaglerAdapter.glEnable(EaglerAdapter.GL_BLEND);

                        EaglerAdapter.glPushMatrix();
                        EaglerAdapter.glTranslatef(-8.0f, -18.0f + var16, 0.0f);

                        EaglerAdapter.glScalef(16.0f, 16.0f, 16.0f);

                        var15.startDrawingQuads();
                        var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                        var15.addVertex(-0.02, -0.02, 0.0);
                        var15.addVertex(-0.02, 1.02, 0.0);
                        var15.addVertex(1.02, 1.02, 0.0);
                        var15.addVertex(1.02, -0.02, 0.0);
                        var15.draw();

                        EaglerAdapter.glEnable(EaglerAdapter.GL_TEXTURE_2D);
                        EaglerAdapter.glEnable(EaglerAdapter.GL_ALPHA_TEST);
                        EaglerAdapter.glAlphaFunc(EaglerAdapter.GL_GREATER, 0.02f);

                        voiceGuiIcons.bindTexture();

                        int u = 0;
                        int v = mute ? 160 : 128;

                        float var7 = 0.00390625F;
                        float var8 = 0.00390625F;

                        if (mute) {
                            EaglerAdapter.glColor4f(0.9F, 0.3F, 0.3F, 0.125F);
                        } else {
                            EaglerAdapter.glColor4f(1.0F, 1.0F, 1.0F, 0.125F);
                        }

                        var15.startDrawingQuads();
                        var15.addVertexWithUV(0, 1.0, 0, (u + 0.2f) * var7, (v + 32 - 0.2f) * var8);
                        var15.addVertexWithUV(1.0, 1.0, 0, (u + 32 - 0.2f) * var7, (v + 32 - 0.2f) * var8);
                        var15.addVertexWithUV(1.0, 0, 0, (u + 32 - 0.2f) * var7, (v + 0.2f) * var8);
                        var15.addVertexWithUV(0, 0, 0, (u + 0.2f) * var7, (v + 0.2f) * var8);
                        var15.draw();

                        EaglerAdapter.glAlphaFunc(EaglerAdapter.GL_GREATER, 0.1f);
                        EaglerAdapter.glEnable(EaglerAdapter.GL_DEPTH_TEST);
                        EaglerAdapter.glDepthMask(true);

                        if (mute) {
                            EaglerAdapter.glColor4f(0.9F, 0.3F, 0.3F, 1.0F);
                        } else {
                            EaglerAdapter.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        }

                        var15.startDrawingQuads();
                        var15.addVertexWithUV(0, 1.0, 0, (u + 0.2f) * var7, (v + 32 - 0.2f) * var8);
                        var15.addVertexWithUV(1.0, 1.0, 0, (u + 32 - 0.2f) * var7, (v + 32 - 0.2f) * var8);
                        var15.addVertexWithUV(1.0, 0, 0, (u + 32 - 0.2f) * var7, (v + 0.2f) * var8);
                        var15.addVertexWithUV(0, 0, 0, (u + 0.2f) * var7, (v + 0.2f) * var8);
                        var15.draw();

                        EaglerAdapter.glEnable(EaglerAdapter.GL_LIGHTING);
                        EaglerAdapter.glDisable(EaglerAdapter.GL_BLEND);
                        EaglerAdapter.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                        EaglerAdapter.glPopMatrix();

                    }
                }
            }

            EaglerAdapter.glPopMatrix();
        }
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
