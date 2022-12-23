package net.minecraft.src.render.misc;

import net.minecraft.src.blocks.Block;
import net.minecraft.src.tileentity.TileEntityChest;
import net.minecraft.src.tileentity.TileEntityEnderChest;
import net.minecraft.src.tileentity.TileEntityRenderer;

public class ChestItemRenderHelper {
    /**
     * The static instance of ChestItemRenderHelper.
     */
    public static ChestItemRenderHelper instance = new ChestItemRenderHelper();

    /**
     * Instance of Chest's Tile Entity.
     */
    private final TileEntityChest theChest = new TileEntityChest();

    /**
     * Instance of Ender Chest's Tile Entity.
     */
    private final TileEntityEnderChest theEnderChest = new TileEntityEnderChest();

    /**
     * Renders a chest at 0,0,0 - used for item rendering
     */
    public void renderChest(Block par1Block, int par2, float par3) {
        if (par1Block.blockID == Block.enderChest.blockID) {
            TileEntityRenderer.instance.renderTileEntityAt(this.theEnderChest, 0.0D, 0.0D, 0.0D, 0.0F);
        } else {
            TileEntityRenderer.instance.renderTileEntityAt(this.theChest, 0.0D, 0.0D, 0.0D, 0.0F);
        }
    }
}