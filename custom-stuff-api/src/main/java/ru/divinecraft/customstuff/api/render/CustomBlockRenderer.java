package ru.divinecraft.customstuff.api.render;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface CustomBlockRenderer extends AutoCloseable {

    @NotNull RenderedCustomBlock create(@NotNull Location location, @NotNull CustomBlockRenderingProperties block,
                                        float xRotation, float yRotation, float zRotation);

    @Deprecated // Use rotation-specific equivalent instead
    default @NotNull RenderedCustomBlock create(@NotNull Location location,
                                                @NotNull CustomBlockRenderingProperties block,
                                                @NotNull BlockFace direction) {
        switch (direction) {
            case SOUTH: return create(location, block, 0, 0, 0);
            case SOUTH_SOUTH_WEST: return create(location, block, 0, 30, 0);
            case SOUTH_WEST: return create(location, block, 0, 45, 0);
            case WEST_SOUTH_WEST: return create(location, block, 0, 60, 0);
            case WEST: return create(location, block, 0, 90, 0);
            case WEST_NORTH_WEST: return create(location, block, 0, 120, 0);
            case NORTH_WEST: return create(location, block, 0, 135, 0);
            case NORTH_NORTH_WEST: return create(location, block, 0, 150, 0);
            case NORTH: return create(location, block, 0, 180, 0);
            case NORTH_NORTH_EAST: return create(location, block, 0, 210, 0);
            case NORTH_EAST: return create(location, block, 0, 225, 0);
            case EAST_NORTH_EAST: return create(location, block, 0, 240, 0);
            case EAST: return create(location, block, 0, 270, 0);
            case EAST_SOUTH_EAST: return create(location, block, 0, 300, 0);
            case SOUTH_EAST: return create(location, block, 0, 315, 0);
            case SOUTH_SOUTH_EAST: return create(location, block, 0, 330, 0);
            case UP: return create(location, block, 270, 0, 0);
            case DOWN: return create(location, block, 90, 0, 0);
            default: throw new IllegalArgumentException("Unsupported direction: " + direction);
        }
    }


    @Override
    void close();
}
