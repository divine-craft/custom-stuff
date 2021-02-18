package ru.divinecraft.customstuff.api.item;

import com.flowpowered.nbt.CompoundMap;
import com.flowpowered.nbt.StringTag;
import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.TagType;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.item.properties.ItemProperties;
import ru.divinecraft.customstuff.api.item.properties.StaticItemProperties;
import ru.divinecraft.customstuff.api.util.NamespacedKeys;

public interface CustomItem {

    @NotNull String CUSTOM_ITEM_TAG_NAME = "custom_item", TYPE_TAG_NAME = "type";

    @Contract(pure = true)
    @NotNull NamespacedKey getType();

    @Contract(pure = true)
    default @NotNull ItemProperties getProperties() {
        return StaticItemProperties.stackable();
    }

    @Contract(pure = true)
    @Nullable NamespacedKey getBlockType();

    @Nullable CompoundMap getBlockNbtTags();

    @NotNull ItemStack asItemStack();

    /*
    Events
     */

    default boolean onClick(@NotNull Player player, @NotNull Action action, @NotNull BlockFace blockFace,
                            boolean mainHand, @Nullable Block block) { return false; }

    ///////////////////////////////////////////////////////////////////////////
    // Utility-methods for tag-writing
    ///////////////////////////////////////////////////////////////////////////

    static @NotNull Tag<?> typeNameTag(final @NotNull NamespacedKey type) {
        return new StringTag(TYPE_TAG_NAME, type.toString());
    }

    static void writeType(final @NotNull CompoundMap tags, final @NotNull NamespacedKey type) {
        tags.put(typeNameTag(type));
    }

    static @Nullable NamespacedKey readType(final @NotNull CompoundMap tags) {
        final Tag<?> tag;
        if ((tag = tags.get(TYPE_TAG_NAME)) != null
                && tag.getType() == TagType.TAG_STRING) return NamespacedKeys.parse(((StringTag) tag).getValue());

        return null;
    }

    static @Nullable String readRawType(final @NotNull NBTCompound tags) {
        return tags.getString(TYPE_TAG_NAME);
    }

    static @Nullable NamespacedKey readType(final @NotNull NBTCompound tags) {
        final String tagValue;
        return (tagValue = tags.getString(TYPE_TAG_NAME)) == null ? null : NamespacedKeys.parse(tagValue);
    }

    static @Nullable String readRawType(final @NotNull NBTItem item) {
        final NBTCompound tags;
        return (tags = item.getCompound(CUSTOM_ITEM_TAG_NAME)) == null ? null : readRawType(tags);
    }

    static @Nullable NamespacedKey readType(final @NotNull NBTItem item) {
        final NBTCompound tags;
        return (tags = item.getCompound(CUSTOM_ITEM_TAG_NAME)) == null ? null : readType(tags);
    }

    static void writeType(final @NotNull NBTCompound tags, final @NotNull NamespacedKey type) {
        tags.setString(TYPE_TAG_NAME, type.toString());
    }

    static void writeType(final @NotNull NBTItem tags, final @NotNull NamespacedKey type) {
        writeType(tags.addCompound(CUSTOM_ITEM_TAG_NAME), type);
    }
}
