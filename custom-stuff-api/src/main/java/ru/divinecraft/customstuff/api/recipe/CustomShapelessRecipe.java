package ru.divinecraft.customstuff.api.recipe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface CustomShapelessRecipe extends CustomRecipe {

    // note: while list is used, its ordering is not essential
    @Unmodifiable @NotNull List<ItemStackMatcher> getComponentMatchers();
}
