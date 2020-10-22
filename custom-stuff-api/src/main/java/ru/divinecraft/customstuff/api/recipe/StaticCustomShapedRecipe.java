package ru.divinecraft.customstuff.api.recipe;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StaticCustomShapedRecipe implements CustomShapedRecipe {

    @NotNull ItemStack result;
    @Unmodifiable @NotNull List<@Nullable ItemStackMatcher> slotMatchers;

    public static @NotNull CustomShapedRecipe create(final @NonNull ItemStack result,
                                                     final @NonNull @Unmodifiable List<@Nullable ItemStackMatcher>
                                                             slotMatchers) {
        return new StaticCustomShapedRecipe(result, slotMatchers);
    }
}
