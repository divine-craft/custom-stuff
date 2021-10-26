package ru.divinecraft.customstuff.api.render;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Value
@Accessors(fluent = true)
public class SimpleCustomBlockRenderingProperties implements CustomBlockRenderingProperties {

    @NotNull ItemStack displayedItem;
    @NotNull RenderingHint @NotNull [] renderingHints;

    public static @NotNull CustomBlockRenderingProperties create(final @NonNull ItemStack renderItem) {
        return new SimpleCustomBlockRenderingProperties(renderItem, RenderingHint.none());
    }

    public static @NotNull CustomBlockRenderingProperties create(
            final @NonNull ItemStack renderItem,
            final @NotNull RenderingHint @NonNull ... renderingHints
    ) {
        return new SimpleCustomBlockRenderingProperties(renderItem, renderingHints);
    }

    /**
     * Creates empty rendering properties.
     *
     * @return empty rendering properties
     */
    public static @NotNull CustomBlockRenderingProperties empty() {
        return EmptyHolder.INSTANCE;
    }

    /**
     * Holder of empty rendering properties.
     */
    private static final class EmptyHolder {

        /**
         * Singleton instance of empty rendering properties.
         */
        private static final @NotNull CustomBlockRenderingProperties INSTANCE = create(new ItemStack(Material.AIR));
    }
}
