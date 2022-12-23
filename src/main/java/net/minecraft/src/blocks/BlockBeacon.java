package net.minecraft.src.blocks;

import net.minecraft.src.creative.CreativeTabs;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.gui.Icon;
import net.minecraft.src.gui.IconRegister;
import net.minecraft.src.items.objects.ItemStack;
import net.minecraft.src.materials.Material;
import net.minecraft.src.tileentity.TileEntity;
import net.minecraft.src.tileentity.TileEntityBeacon;
import net.minecraft.src.world.World;

public class BlockBeacon extends BlockContainer {
    private Icon theIcon;

    public BlockBeacon(int par1) {
        super(par1, Material.glass);
        this.setHardness(3.0F);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the
     * block.
     */
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityBeacon();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        return true;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether or
     * not to render the shared face of two adjacent blocks and also whether the
     * player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType() {
        return 34;
    }

    /**
     * When this method is called, your block should register all the icons it needs
     * with the given IconRegister. This is the only chance you get to register
     * icons.
     */
    public void registerIcons(IconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
        this.theIcon = par1IconRegister.registerIcon("beacon");
    }

    public Icon getBeaconIcon() {
        return this.theIcon;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
        super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving, par6ItemStack);

        if (par6ItemStack.hasDisplayName()) {
            ((TileEntityBeacon) par1World.getBlockTileEntity(par2, par3, par4)).func_94047_a(par6ItemStack.getDisplayName());
        }
    }
}