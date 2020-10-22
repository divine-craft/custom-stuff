package ru.divinecraft.customstuff.api.chunk.handler;

import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;

public interface ChunkLoadingHandler {

    void onChunkLoaded(@NotNull Chunk chunk);

    void onChunkUnloaded(@NotNull Chunk chunk);
}
