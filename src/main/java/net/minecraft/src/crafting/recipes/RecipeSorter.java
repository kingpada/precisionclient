package net.minecraft.src.crafting.recipes;

import net.minecraft.src.crafting.CraftingManager;
import net.minecraft.src.crafting.IRecipe;

import java.util.Comparator;

public class RecipeSorter implements Comparator {
    final CraftingManager craftingManager;

    public RecipeSorter(CraftingManager par1CraftingManager) {
        this.craftingManager = par1CraftingManager;
    }

    public int compareRecipes(IRecipe par1IRecipe, IRecipe par2IRecipe) {
        return par1IRecipe instanceof ShapelessRecipes && par2IRecipe instanceof ShapedRecipes ? 1
                : (par2IRecipe instanceof ShapelessRecipes && par1IRecipe instanceof ShapedRecipes ? -1
                : (par2IRecipe.getRecipeSize() < par1IRecipe.getRecipeSize() ? -1 : (par2IRecipe.getRecipeSize() > par1IRecipe.getRecipeSize() ? 1 : 0)));
    }

    public int compare(Object par1Obj, Object par2Obj) {
        return this.compareRecipes((IRecipe) par1Obj, (IRecipe) par2Obj);
    }
}
