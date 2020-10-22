package ru.divinecraft.customstuff.api.chunk;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.progrm_jarvis.javacommons.pair.Pair;

import javax.annotation.Nonnegative;

public interface ChunkDataStorage<T> extends Iterable<@NotNull Pair<@NotNull Vector, T>> {

    @Contract(pure = true)
    boolean isEmpty();

    @Contract(pure = true)
    @Nonnegative int getSize();

    @Contract(pure = true)
    @Nullable T get(byte x, byte y, byte z);

    void set(byte x, byte y, byte z, @Nullable T value);

    @Nullable T remove(byte x, byte y, byte z);
}
