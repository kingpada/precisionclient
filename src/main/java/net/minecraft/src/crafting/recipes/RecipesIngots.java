package net.minecraft.src.crafting.recipes;

import net.minecraft.src.crafting.CraftingManager;
import net.minecraft.src.blocks.Block;
import net.minecraft.src.items.Item;
import net.minecraft.src.items.objects.ItemStack;

public class RecipesIngots {
    private final Object[][] recipeItems;

    public RecipesIngots() {
        this.recipeItems = new Object[][]{{Block.blockGold, new ItemStack(Item.ingotGold, 9)}, {Block.blockIron, new ItemStack(Item.ingotIron, 9)}, {Block.blockDiamond, new ItemStack(Item.diamond, 9)},
                {Block.blockEmerald, new ItemStack(Item.emerald, 9)}, {Block.blockLapis, new ItemStack(Item.dyePowder, 9, 4)}, {Block.blockRedstone, new ItemStack(Item.redstone, 9)}};
    }

    /**
     * Adds the ingot recipes to the CraftingManager.
     */
    public void addRecipes(CraftingManager par1CraftingManager) {
        for (int var2 = 0; var2 < this.recipeItems.length; ++var2) {
            Block var3 = (Block) this.recipeItems[var2][0];
            ItemStack var4 = (ItemStack) this.recipeItems[var2][1];
            par1CraftingManager.addRecipe(new ItemStack(var3), "###", "###", "###", '#', var4);
            par1CraftingManager.addRecipe(var4, "#", '#', var3);
        }

        par1CraftingManager.addRecipe(new ItemStack(Item.ingotGold), "###", "###", "###", '#', Item.goldNugget);
        par1CraftingManager.addRecipe(new ItemStack(Item.goldNugget, 9), "#", '#', Item.ingotGold);
    }
}