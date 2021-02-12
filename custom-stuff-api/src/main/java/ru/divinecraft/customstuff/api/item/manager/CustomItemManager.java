package ru.divinecraft.customstuff.api.item.manager;

import com.flowpowered.nbt.CompoundMap;
import lombok.NonNull;
import lombok.val;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import ru.divinecraft.customstuff.api.item.CustomItem;

import java.util.*;

public interface CustomItemManager {

    @Contract(pure = true)
    @Nullable ItemFactory getItemFactory(@NotNull NamespacedKey type);

    @Contract(pure = true)
    boolean hasItemFactory(@NotNull NamespacedKey type);

    @Nullable CustomItem createNewCustomItem(@NotNull NamespacedKey type, @Nullable CompoundMap nbtTags);

    boolean isCustomItem(@Nullable ItemStack item);

    @Nullable CustomItem asCustomItem(@Nullable ItemStack item);

    @NotNull Item dropItem(@NotNull Location location,
                           @NotNull NamespacedKey type, @Nullable CompoundMap nbtTags);

    @NotNull Item dropItemNaturally(@NotNull Location location,
                                    @NotNull NamespacedKey type, @Nullable CompoundMap nbtTags);

    @FunctionalInterface
    interface ItemCreator {

        /**
         * Creates a new custom block.
         *
         * @param manager manager owning this block
         * @param nbtTags NBT tags of the item (may be {@code null} indicating no tags)
         * @return created custom block
         */
        @NotNull CustomItem create(@NotNull CustomItemManager manager,
                                   @Nullable CompoundMap nbtTags);
    }

    /**
     * An object responsible for creating exact items.
     */
    @FunctionalInterface
    interface ItemFactory extends ItemCreator {

        @SuppressWarnings("PublicStaticCollectionField") // contains only the null-value
        @Unmodifiable @NotNull Set<@Nullable CompoundMap> ONLY_NULL_VARIANT = Collections.singleton(null);

        /**
         * Gets the variants of the item created by this factory available for direct creation.
         *
         * @return variants of this item actually
         *
         * @apiNote the variants are represented as NBT-tags
         * which may be passed to {@link #create(CustomItemManager, CompoundMap)}
         */
        default @NotNull Enumeration<@Nullable CompoundMap> defaultVariants() {
            return Collections.enumeration(ONLY_NULL_VARIANT);
        }

        @Contract("_, _ -> new")
        static @NotNull ItemFactory create(final @NonNull ItemCreator creator,
                                           final @Nullable CompoundMap @NonNull ... defaultVariants) {
            val defaultVariantsEnumeration = Collections.enumeration(Arrays.asList(defaultVariants));

            return new ItemFactory() {
                @Override
                public @NotNull CustomItem create(@NotNull final CustomItemManager manager,
                                                  @Nullable final CompoundMap nbtTags) {
                    return creator.create(manager, nbtTags);
                }

                @Override
                public @NotNull Enumeration<@Nullable CompoundMap> defaultVariants() {
                    return defaultVariantsEnumeration;
                }
            };
        }

        @Contract("_ -> new")
        default @NotNull ItemFactory withVariants(final @Nullable CompoundMap @NonNull ... defaultVariants) {
            return create(this, defaultVariants);
        }
    }
}
