package ru.divinecraft.customstuff.api.render;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Properties of a rendered custom block.
 */
public interface CustomBlockRenderingProperties {

    /**
     * Gets the item which should be used for displaying this block.
     *
     * @return item used for displaying this block
     */
    @NotNull ItemStack displayedItem();

    // no default implementation so that callers store the value in constant field
    @NotNull RenderingHint @NotNull [] renderingHints();

    /**
     * Extra hints for the block renderer.
     */
    enum RenderingHint {
        ;

        private static final @NotNull RenderingHint @NotNull [] NONE = new RenderingHint[0];

        public static @NotNull RenderingHint @NotNull [] none() {
            return NONE;
        }
    }
}
