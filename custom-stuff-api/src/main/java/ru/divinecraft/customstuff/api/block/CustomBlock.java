package ru.divinecraft.customstuff.api.block;

import com.flowpowered.nbt.CompoundMap;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.block.properties.BlockProperties;
import ru.progrm_jarvis.javacommons.ownership.annotation.Own;

public interface CustomBlock {

    ///////////////////////////////////////////////////////////////////////////
    // Generic block properties
    ///////////////////////////////////////////////////////////////////////////

    @NotNull NamespacedKey getType();

    @NotNull BlockProperties getProperties();

    ///////////////////////////////////////////////////////////////////////////
    // Storage-related
    ///////////////////////////////////////////////////////////////////////////

    @Own @NotNull Location getLocation();

    ///////////////////////////////////////////////////////////////////////////
    // Storage-related with defaults
    // It is common for blocks to be non-mutable so methods are default
    // so that JIT will be happier with inline caching
    ///////////////////////////////////////////////////////////////////////////

    // default as
    default boolean isDirty() {
        return false;
    }

    // default as it is common for blocks to be non-mutable thus JIT will be happier with inline caching
    default @Own @Nullable CompoundMap readNbtTags() {
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Hooks
    ///////////////////////////////////////////////////////////////////////////

    // called whenever a block is created, no matter how
    default void onCreate() {}

    default void onRemove() {}

    default void onLoad() {}

    default void onUnload() {}

    default void onBreak(@NotNull Player player) {}

    default void onPlace(@NotNull Player player) {}

    default void onExplode(float yield) {}

    default boolean onClick(@NotNull Player player, @NotNull Action action, @NotNull BlockFace blockFace,
                            boolean mainHand, @Nullable ItemStack item) {
        return false;
    }
}
