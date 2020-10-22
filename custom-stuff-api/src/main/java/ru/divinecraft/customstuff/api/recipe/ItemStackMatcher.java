package ru.divinecraft.customstuff.api.recipe;

import com.google.common.base.Preconditions;
import lombok.NonNull;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import ru.progrm_jarvis.minecraft.commons.util.ItemStackUtil;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@FunctionalInterface
public interface ItemStackMatcher extends Predicate<@NotNull ItemStack> {

    /*
    static @NonNull ItemStackMatcher empty() {
        return ItemUtil::isEmpty;
    }

    static @NonNull ItemStackMatcher notEmpty() {
        return ItemUtil::isNotEmpty;
    }
     */

    static boolean matches(final @Nullable ItemStackMatcher matcher, final @Nullable ItemStack item) {
        return ItemStackUtil.isEmpty(item) ? matcher == null : matcher != null && matcher.matches(item);
    }

    boolean matches(@NotNull ItemStack item);

    // null stands for unknown icon
    default @Unmodifiable @Nullable Set<ItemStack> icons() {
        return null;
    }

    @Override
    default boolean test(final @NotNull ItemStack itemStack) {
        return matches(itemStack);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Combiners
    ///////////////////////////////////////////////////////////////////////////

    @Override
    default @NotNull ItemStackMatcher negate() {
        return item -> !matches(item);
    }

    @SuppressWarnings("LambdaUnfriendlyMethodOverload") // same logic as in parent
    default @NotNull ItemStackMatcher and(final @NonNull ItemStackMatcher other) {
        return item -> matches(item) && other.matches(item);
    }

    @SuppressWarnings("LambdaUnfriendlyMethodOverload") // same logic as in parent
    default @NotNull ItemStackMatcher or(final @NonNull ItemStackMatcher other) {
        return item -> matches(item) || other.matches(item);
    }

    default @NotNull ItemStackMatcher withIcon(final @Unmodifiable @Nullable ItemStack icon) {
        return withIcons(icon == null ? null : Collections.singleton(icon));
    }

    default @NotNull ItemStackMatcher withIcons(final @Unmodifiable @Nullable Set<@NonNull ItemStack> icons) {
        if (icons != null) for (val icon : icons) Preconditions.checkNotNull(icon, "None of the icons can be null");

        return new ItemStackMatcher() {

            @Override
            public boolean matches(@NotNull final ItemStack item) {
                return ItemStackMatcher.this.matches(item);
            }

            @Override
            public @Nullable Set<ItemStack> icons() {
                return icons;
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////
    // Factories
    ///////////////////////////////////////////////////////////////////////////

    static @NotNull ItemStackMatcher forMaterial(final @NonNull Material material) {
        val icons = Collections.singleton(new ItemStack(material));

        return new ItemStackMatcher() {
            @Override
            public boolean matches(final @NotNull ItemStack item) {
                return item.getType() == material;
            }

            @Override
            public @Unmodifiable @NotNull Set<ItemStack> icons() {
                return icons;
            }
        };
    }

    static @NotNull ItemStackMatcher forMaterials(final @Unmodifiable @NonNull Set<@NonNull Material> materials) {
        val iconItems = new HashSet<ItemStack>(materials.size());
        for (val material : materials) {
            Preconditions.checkNotNull(material, "None of the materials can be null");

            iconItems.add(new ItemStack(material));
        }
        val icons = Collections.unmodifiableSet(iconItems);

        return new ItemStackMatcher() {
            @Override
            public boolean matches(final @NotNull ItemStack item) {
                return materials.contains(item.getType());
            }

            @Override
            public @Unmodifiable @NotNull Set<ItemStack> icons() {
                return icons;
            }
        };
    }

    static @NotNull ItemStackMatcher forMaterials(final @NonNull Material firstMaterial,
                                                  final @NonNull Material @Unmodifiable @NonNull ... restMaterials) {
        return forMaterials(EnumSet.of(firstMaterial, restMaterials));
    }
}
