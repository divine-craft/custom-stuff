package ru.divinecraft.customstuff.api.util;

import lombok.experimental.UtilityClass;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class MaterialUtil {

    // see: {nms}.Blocks
    public boolean isReplaceable(final @NotNull Block block) {
        switch (block.getType()) {
            // air and liquids
            case AIR: case WATER: case STATIONARY_WATER: case LAVA: case STATIONARY_LAVA:
            // various plants
            case LONG_GRASS: case DEAD_BUSH: case VINE: case WATER_LILY: case DOUBLE_PLANT: return true;
            case SNOW: return block.getData() == (byte) 0; // snow_layer of minimal height
            default: return false;
        }
    }
}
