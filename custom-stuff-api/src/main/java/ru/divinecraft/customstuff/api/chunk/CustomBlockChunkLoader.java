package ru.divinecraft.customstuff.api.chunk;

import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;

import java.io.IOException;

public interface CustomBlockChunkLoader {

    @NotNull CustomBlockChunk load(@NotNull Chunk chunk, @NotNull CustomBlockManager blockManager) throws IOException;

    void unload(@NotNull CustomBlockChunk chunk) throws IOException;
}
