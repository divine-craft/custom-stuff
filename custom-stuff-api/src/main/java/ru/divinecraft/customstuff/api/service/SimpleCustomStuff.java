package ru.divinecraft.customstuff.api.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.inventory.manager.CustomInventoryManager;
import ru.divinecraft.customstuff.api.item.manager.CustomItemManager;
import ru.divinecraft.customstuff.api.recipe.manager.CustomRecipeManager;
import ru.divinecraft.customstuff.api.render.CustomBlockRenderer;

@Value
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@NonFinal public class SimpleCustomStuff implements CustomStuff {

    @NonNull CustomBlockManager blockManager;
    @NonNull CustomItemManager itemManager;
    @NonNull CustomInventoryManager inventoryManager;
    @NonNull CustomRecipeManager recipeManager;
    @NonNull CustomBlockRenderer blockRenderer;

    public static CustomStuff create(final @NonNull CustomBlockManager blockManager,
                                     final @NonNull CustomItemManager itemManager,
                                     final @NonNull CustomInventoryManager inventoryManager,
                                     final @NonNull CustomRecipeManager recipeManager,
                                     final @NonNull CustomBlockRenderer blockRenderer) {
        return new SimpleCustomStuff(blockManager, itemManager, inventoryManager, recipeManager, blockRenderer);
    }
}
