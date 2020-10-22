package ru.divinecraft.customstuff.api.chunk;

import lombok.NonNull;
import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;

public interface CustomBlockChunkRepository extends AutoCloseable {

    @NotNull CustomBlockChunk retrieve(@NotNull Chunk chunk,
                                       @NonNull CustomBlockManager blockManager);

    void store(@NotNull CustomBlockChunk chunk);
}
