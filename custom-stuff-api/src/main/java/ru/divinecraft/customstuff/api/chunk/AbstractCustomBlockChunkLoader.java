package ru.divinecraft.customstuff.api.chunk;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;

import javax.annotation.WillNotClose;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static ru.progrm_jarvis.javacommons.io.wrapper.IOWrappers.nonAutoCloseable;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class AbstractCustomBlockChunkLoader implements CustomBlockChunkLoader {

    protected @NonNull CustomBlockChunk.Reader chunkReader;

    // null indicates no chunk found
    protected abstract @WillNotClose @Nullable InputStream openInputStream(@NotNull Chunk chunk) throws IOException;

    protected abstract @WillNotClose @NotNull OutputStream openOutputStream(@NotNull Chunk chunk) throws IOException;

    protected abstract void deleteData(@NotNull Chunk chunk) throws IOException;

    @Override
    public @NotNull CustomBlockChunk load(final @NotNull Chunk chunk,
                                          final @NotNull CustomBlockManager blockManager) throws IOException {
        try (val chunkInputStream = openInputStream(chunk)) {
            return chunkInputStream == null
                    ? chunkReader.createNew(blockManager, chunk)
                    : chunkReader.read(blockManager, chunk, nonAutoCloseable(chunkInputStream));
        }
    }

    @Override
    public void unload(final @NotNull CustomBlockChunk chunk) throws IOException {
        if (chunk.isDirty() || chunk.hasDirtyBlocks()) {
            if (chunk.isEmpty()) deleteData(chunk.getChunk());
            else try (val chunkOutputStream = openOutputStream(chunk.getChunk())) {
                chunk.writeAndUnload(nonAutoCloseable(chunkOutputStream));
            }

            chunk.notDirty();
        }
    }
}
