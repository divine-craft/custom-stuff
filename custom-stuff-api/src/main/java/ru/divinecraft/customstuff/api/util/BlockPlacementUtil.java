package ru.divinecraft.customstuff.api.util;

import lombok.experimental.UtilityClass;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Snow;
import org.jetbrains.annotations.NotNull;

/**
 * Utilities related to calculating correct block placement.
 */
@UtilityClass
public class BlockPlacementUtil {

    /**
     * Checks if the specified block should be replaced by a hand block whenever it is right-clicked.
     * Such behaviour is normal for air, liquids, some "green" blocks and snow single-layered snow.
     *
     * @param block block which should be checked
     * @return {@code true} if the block should be replaced on right click
     */
    public boolean isReplaceable(final @NotNull Block block) {
        /*
         On decompiled 1.16 this is looked up via regex
         `\(new Material\.a\(MaterialMapColor\.[a-zA-Z\d_]\)\)(\.[a-zA-Z\d_]+\(\))*\.e\(\)(\.[a-zA-Z\d_]+\(\))*\.h\(\)`
         because `Material.a` is a builder of Material and `h()` stands for `makeReplaceable()`:
         - AIR
         - STRUCTURE_VOID
         - REPLACEABLE_PLANT
         - h (nether plants)
         - REPLACEABLE_WATER_PLANT
         - WATER
         - BUBBLE_COLUMN
         - LAVA
         - PACKED_ICE // this is an error in Bukkit constants, this actually stands for snow (*)
         - FIRE
         (*) snow is replaceable whenever it is one layer high
         */

        if (block.isEmpty() /* should handle air */ || block.isLiquid() /* should handle water and lava */) return true;

        // note: there is no need to cover LEGACY_ IDs as the type is got from Block instance
        switch (block.getType()) {
            //<editor-fold desc="Trivially replaceable blocks">
            case AIR: case VOID_AIR: case CAVE_AIR: // AIR
            case STRUCTURE_VOID: // STRUCTURE_VOID
            case GRASS: case FERN: case DEAD_BUSH: case VINE: case SUNFLOWER:
            case LILAC: case ROSE_BUSH: case PEONY: case TALL_GRASS: case LARGE_FERN: // REPLACEABLE_PLANT
            case WARPED_ROOTS: case NETHER_SPROUTS: case CRIMSON_ROOTS: // h
            case SEAGRASS: case TALL_SEAGRASS: // REPLACEABLE_WATER_PLANT
            case WATER: // WATER
            case BUBBLE_COLUMN: // BUBBLE_COLUMN
            case LAVA: // LAVA
            case FIRE: case SOUL_FIRE: // FIRE
                return true;
            //</editor-fold>
            // note: previously data value 0 represented single layer but getLayers() returns the actual number (1)
            case SNOW: return ((Snow) block.getBlockData()).getLayers() == 1; // PACKED_ICE
            default: return false;
        }
    }
}
