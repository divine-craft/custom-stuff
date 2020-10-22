package ru.divinecraft.customstuff.api.chunk;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.progrm_jarvis.javacommons.io.wrapper.NonAutoCloseableInputStream;
import ru.progrm_jarvis.javacommons.io.wrapper.NonAutoCloseableOutputStream;
import ru.divinecraft.customstuff.api.block.CustomBlock;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;

import java.io.IOException;
import java.util.function.UnaryOperator;

public interface CustomBlockChunk {

    @Contract(pure = true)
    boolean isDirty();

    @Contract(pure = true)
    boolean hasDirtyBlocks();

    void dirty();

    void notDirty();

    @Contract(pure = true)
    boolean isEmpty();

    @NotNull Location getGlobalLocation(byte x, byte y, byte z);

    @Contract(pure = true)
    @NotNull Chunk getChunk();

    @Contract(pure = true)
    @Nullable CustomBlock get(byte x, byte y, byte z);

    @Contract(pure = true)
    @Nullable CustomBlock get(@NotNull Location location);

    void set(byte x, byte y, byte z, CustomBlock block);

    void set(@NotNull Location location, CustomBlock block);

    void update(byte x, byte y, byte z, UnaryOperator<@Nullable CustomBlock> updater);

    void update(@NotNull Location location, UnaryOperator<@Nullable CustomBlock> updater);

    @Nullable CustomBlock remove(byte x, byte y, byte z);

    @Nullable CustomBlock remove(@NotNull Location location);

    boolean isEqualTo(@NotNull CustomBlockChunk other);

    void write(@NotNull NonAutoCloseableOutputStream output) throws IOException;

    void unload();

    void writeAndUnload(@NotNull NonAutoCloseableOutputStream output) throws IOException;

    interface Reader {

        CustomBlockChunk createNew(@NotNull CustomBlockManager blockManager,
                                   @NotNull Chunk chunk);

        CustomBlockChunk read(@NotNull CustomBlockManager blockManager,
                              @NotNull Chunk chunk, @NotNull NonAutoCloseableInputStream input) throws IOException;
    }
}
