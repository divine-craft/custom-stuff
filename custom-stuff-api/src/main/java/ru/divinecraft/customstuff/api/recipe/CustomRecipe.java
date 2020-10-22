package ru.divinecraft.customstuff.api.recipe;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.Contract;

public interface CustomRecipe extends Recipe  {

    @Override
    @Contract(pure = true)
    ItemStack getResult();
}
