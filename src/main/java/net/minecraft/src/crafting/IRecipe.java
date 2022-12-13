package net.minecraft.src.crafting;

import net.minecraft.src.inventory.InventoryCrafting;
import net.minecraft.src.items.objects.ItemStack;
import net.minecraft.src.world.World;

public interface IRecipe {
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(InventoryCrafting var1, World var2);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(InventoryCrafting var1);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    ItemStack getRecipeOutput();
}
