package net.minecraft.src.items.objects;

import net.minecraft.src.gui.Icon;
import net.minecraft.src.gui.IconRegister;
import net.minecraft.src.world.World;
import net.minecraft.src.blocks.Block;
import net.minecraft.src.creative.CreativeTabs;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.enums.EnumRarity;
import net.minecraft.src.items.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRecord extends Item {
    /**
     * List of all record items and their names.
     */
    private static final Map records = new HashMap();

    /**
     * The name of the record.
     */
    public final String recordName;

    public ItemRecord(int par1, String par2Str) {
        super(par1);
        this.recordName = par2Str;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabMisc);
        records.put(par2Str, this);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1) {
        return this.itemIcon;
    }

    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return par3World.getBlockId(par4, par5, par6) == Block.jukebox.blockID && par3World.getBlockMetadata(par4, par5, par6) == 0;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(this.getRecordTitle());
    }

    /**
     * Return the title for this record.
     */
    public String getRecordTitle() {
        return "C418 - " + this.recordName;
    }

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    /**
     * Return the record item corresponding to the given name.
     */
    public static ItemRecord getRecord(String par0Str) {
        return (ItemRecord) records.get(par0Str);
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("record_" + this.recordName);
    }
}