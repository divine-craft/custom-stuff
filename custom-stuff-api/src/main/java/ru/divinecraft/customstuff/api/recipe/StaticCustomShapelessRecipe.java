package ru.divinecraft.customstuff.api.recipe;

import lombok.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StaticCustomShapelessRecipe implements CustomShapelessRecipe {

    @NotNull ItemStack result;
    @Unmodifiable @NotNull List<@Nullable ItemStackMatcher> componentMatchers;

    public static @NotNull CustomShapelessRecipe create(final @NonNull ItemStack result,
                                                        final @NonNull @Unmodifiable List<@NonNull ItemStackMatcher>
                                                                componentMatchers) {
        for (val componentMatcher : componentMatchers) checkNotNull(componentMatcher, "componentMatchers cannot contain null values");

        return new StaticCustomShapelessRecipe(result, componentMatchers);
    }
}
