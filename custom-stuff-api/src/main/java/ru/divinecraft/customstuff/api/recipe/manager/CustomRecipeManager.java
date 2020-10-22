package ru.divinecraft.customstuff.api.recipe.manager;

import lombok.NonNull;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.recipe.CustomShapedRecipe;
import ru.divinecraft.customstuff.api.recipe.CustomShapelessRecipe;

public interface CustomRecipeManager {

    void registerRecipe(@NonNull CustomShapedRecipe recipe);

    void registerRecipe(@NonNull CustomShapelessRecipe recipe);

    // null for no result override
    // non-null for result override
    @Nullable ItemStack matchRecipe(@NotNull CraftingInventory craftingInventory);
}
