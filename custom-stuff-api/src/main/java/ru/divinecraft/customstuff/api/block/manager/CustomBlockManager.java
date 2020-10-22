package ru.divinecraft.customstuff.api.block.manager;

import com.flowpowered.nbt.CompoundMap;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.block.CustomBlock;
import ru.progrm_jarvis.minecraft.commons.schedule.pool.LoopPool;

public interface CustomBlockManager {

    @Contract(pure = true)
    @NotNull LoopPool getLoopPool();

    @Contract(pure = true)
    boolean hasBlockFactory(@NotNull String blockTypeName);

    @Contract(pure = true)
    @Nullable BlockFactory getBlockFactory(@NotNull String blockTypeName);

    @Nullable CustomBlock getBlock(@NotNull Location location);

    @NotNull CustomBlock createBlock(@NotNull Location location,
                                     @NotNull String blockTypeName,
                                     @Nullable BlockFace direction,
                                     @Nullable CompoundMap nbtTags);

    // null if there is already a block at his location
    @Nullable CustomBlock placeBlock(@NotNull Location location,
                                     @NotNull String blockTypeName,
                                     @Nullable BlockFace direction,
                                     @Nullable CompoundMap nbtTags,
                                     @NotNull Player player);

    @Nullable CustomBlock placeOrGetBlock(@NotNull Location location,
                                          @NotNull String blockTypeName,
                                          @Nullable BlockFace direction,
                                          @Nullable CompoundMap nbtTags,
                                          @NotNull Player player);

    @Nullable CustomBlock removeBlock(@NotNull Location location);

    @Nullable CustomBlock breakBlock(@NotNull Location location,
                                     @NotNull Player player);

    // returns not-null value <=> block exists or existed
    @Nullable CustomBlock explodeBlock(@NotNull Location location, float yield);

    // returns not-null value <=> block exists but was not exploded
    @Nullable CustomBlock tryExplodeBlock(@NotNull Location location, float yield);

    // void loadBlockChunk(Chunk chunk);

    // void unloadBlockChunk(Chunk chunk);

    /**
     * An object responsible for creating exact blocks.
     */
    @FunctionalInterface
    interface BlockFactory {

        /**
         * Creates a new custom block.
         *
         * @param manager manager owning this block
         * @param location location of the block
         * @param direction direction in which the block is facing
         * @param nbtTags NBT tags of the item (may be {@code null} indicating no tags)
         * @return created custom block
         */
        @NotNull CustomBlock create(@NotNull CustomBlockManager manager,
                                    @NotNull Location location,
                                    @Nullable BlockFace direction,
                                    @Nullable CompoundMap nbtTags);
    }
}
