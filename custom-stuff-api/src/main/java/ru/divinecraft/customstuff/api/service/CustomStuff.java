package ru.divinecraft.customstuff.api.service;

import org.jetbrains.annotations.Contract;
import ru.divinecraft.customstuff.api.advancement.manager.AdvancementManager;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.inventory.manager.CustomInventoryManager;
import ru.divinecraft.customstuff.api.item.manager.CustomItemManager;
import ru.divinecraft.customstuff.api.recipe.manager.RecipeManager;
import ru.divinecraft.customstuff.api.render.CustomBlockRenderer;
import ru.divinecraft.zaraza.common.api.annotation.BukkitService;

@BukkitService("CustomStuff")
public interface CustomStuff {

    @Contract(pure = true)
    CustomBlockManager blockManager();

    @Contract(pure = true)
    CustomItemManager itemManager();

    @Contract(pure = true)
    CustomInventoryManager inventoryManager();

    @Contract(pure = true)
    AdvancementManager advancementManager();

    @Contract(pure = true)
    RecipeManager recipeManager();

    @Contract(pure = true)
    CustomBlockRenderer blockRenderer();
}
