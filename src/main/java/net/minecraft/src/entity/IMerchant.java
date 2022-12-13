package net.minecraft.src.entity;

import net.minecraft.src.crafting.recipes.MerchantRecipe;
import net.minecraft.src.crafting.recipes.MerchantRecipeList;
import net.minecraft.src.entity.EntityPlayer;

public interface IMerchant {
    void setCustomer(EntityPlayer var1);

    EntityPlayer getCustomer();

    MerchantRecipeList getRecipes(EntityPlayer var1);

    void setRecipes(MerchantRecipeList var1);

    void useRecipe(MerchantRecipe var1);
}
