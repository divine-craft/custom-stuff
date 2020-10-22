package ru.divinecraft.customstuff.api.recipe;

import lombok.NonNull;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface CustomShapedRecipe extends CustomRecipe {

    @Contract(pure = true)
    @NonNull @Unmodifiable List<@Nullable ItemStackMatcher> getSlotMatchers();
}
