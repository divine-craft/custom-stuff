package ru.divinecraft.customstuff.api.item;

import com.flowpowered.nbt.CompoundMap;
import com.flowpowered.nbt.StringTag;
import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.TagType;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.NonNull;
import lombok.val;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import ru.divinecraft.customstuff.api.item.properties.ItemProperties;
import ru.divinecraft.customstuff.api.item.properties.StaticItemProperties;
import ru.divinecraft.customstuff.api.recipe.ItemStackMatcher;

import java.util.Collections;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public interface CustomItem {

    @NotNull String CUSTOM_ITEM_TAG_NAME = "custom_item", TYPE_TAG_NAME = "type";

    @Contract(pure = true)
    @NotNull String getTypeName();

    @Contract(pure = true)
    default @NotNull ItemProperties getProperties() {
        return StaticItemProperties.stackable();
    }

    @Contract(pure = true)
    @Nullable String getBlockTypeName();

    @Nullable CompoundMap getBlockNbtTags();

    @NotNull ItemStack asItemStack();

    /*
    Events
     */

    default boolean onClick(@NotNull Player player, @NotNull Action action, @NotNull BlockFace blockFace,
                            boolean mainHand, @NotNull Block block) { return false; }

    ///////////////////////////////////////////////////////////////////////////
    // Utility-methods for tag-writing
    ///////////////////////////////////////////////////////////////////////////

    static @NotNull Tag<?> typeNameTag(final @NotNull String typeName) {
        return new StringTag(TYPE_TAG_NAME, typeName);
    }

    static void writeType(final @NotNull CompoundMap tags, final @NotNull String typeName) {
        tags.put(typeNameTag(typeName));
    }

    static @Nullable String readType(final @NotNull CompoundMap tags) {
        final Tag<?> tag;
        if ((tag = tags.get(TYPE_TAG_NAME)) != null
                && tag.getType() == TagType.TAG_STRING) return ((StringTag) tag).getValue();

        return null;
    }

    static @Nullable String readType(final @NotNull NBTCompound tags) {
        return tags.getString(TYPE_TAG_NAME);
    }

    static @Nullable String readType(final @NotNull NBTItem item) {
        final NBTCompound tags;
        return (tags = item.getCompound(CUSTOM_ITEM_TAG_NAME)) == null ? null : readType(tags);
    }

    static void writeType(final @NotNull NBTCompound tags, final @NotNull String type) {
        tags.setString(TYPE_TAG_NAME, type);
    }

    static void writeType(final @NotNull NBTItem tags, final @NotNull String type) {
        writeType(tags.addCompound(CUSTOM_ITEM_TAG_NAME), type);
    }

    static @NotNull ItemStackMatcher matcherForType(final @NonNull String type) {
        return item -> {
            final String actualType;
            return (actualType = readType(new NBTItem(item))) != null && actualType.equals(type);
        };
    }

    static @NotNull ItemStackMatcher matcherForType(final @NonNull String type,
                                                    final @Unmodifiable @Nullable Set<@Unmodifiable @NonNull ItemStack>
                                                            icons) {
        if (icons != null) for (val icon
                : icons) checkNotNull(icon, "None of the items can be null");

        return new ItemStackMatcher() {
            @Override
            public boolean matches(@NotNull final ItemStack item) {
                final String actualType;
                return (actualType = readType(new NBTItem(item))) != null && actualType.equals(type);
            }

            @Override
            public @Unmodifiable @Nullable Set<ItemStack> icons() {
                return icons;
            }
        };
    }

    static @NotNull ItemStackMatcher matcherForType(final @NonNull String type,
                                                    final @Nullable ItemStack icon) {
        return matcherForType(type, icon == null ? null : Collections.singleton(icon));
    }
}
