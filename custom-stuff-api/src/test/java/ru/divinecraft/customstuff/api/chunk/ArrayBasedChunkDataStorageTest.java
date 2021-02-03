package ru.divinecraft.customstuff.api.chunk;

import lombok.val;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.progrm_jarvis.javacommons.pair.SimplePair;

import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayBasedChunkDataStorageTest {

    private ChunkDataStorage<String> chunkDataStorage;

    @BeforeEach
    void setUp() {
        chunkDataStorage = ArrayBasedChunkDataStorage.withAllocator(
                new ArrayBasedChunkDataStorage.LayerAllocator<String>() {
                    @Override
                    public @Nullable String @NotNull [] @Nullable [] allocateLayers() {
                        return new String[LAYERS_COUNT][];
                    }

                    @Override
                    public @Nullable String @NotNull [] allocateLayer() {
                        return new String[LAYER_SIZE];
                    }
                });
    }

    @Test
    void testEmptySize() {
        assertEquals(0, chunkDataStorage.getSize());
    }

    @Test
    void testSetAndGet() {
        chunkDataStorage.set((byte) 1, (byte) 2, (byte) 3, "value");
        assertEquals(1, chunkDataStorage.getSize());
        assertEquals("value", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
    }

    @Test
    void testSetAndGetWithBigXZ() {
        chunkDataStorage.set((byte) 0xA, (byte) 7, (byte) 0xA, "value");
        assertEquals(1, chunkDataStorage.getSize());
        assertEquals("value", chunkDataStorage.get((byte) 0xA, (byte) 7, (byte) 0xA));
    }

    @Test
    void testSetAndGetWithMaximalXZ() {
        chunkDataStorage.set((byte) 0xF, (byte) 99, (byte) 0xF, "value");
        assertEquals(1, chunkDataStorage.getSize());
        assertEquals("value", chunkDataStorage.get((byte) 0xF, (byte) 99, (byte) 0xF));
    }

    @Test
    void testSetAndGetWithNegativeY() {
        chunkDataStorage.set((byte) 12, (byte) -123, (byte) 11, "value");
        assertEquals(1, chunkDataStorage.getSize());
        assertEquals("value", chunkDataStorage.get((byte) 12, (byte) -123, (byte) 11));
    }

    @Test
    void testSetAndGetWithMaxCoordinate() {
        chunkDataStorage.set((byte) 0xF, (byte) 0xFF, (byte) 0xF, "value");
        assertEquals(1, chunkDataStorage.getSize());
        assertEquals("value", chunkDataStorage.get((byte) 0xF, (byte) 0xFF, (byte) 0xF));
    }

    @Test
    void testSetFromEmpty() {
        for (int y = 0; y < 256; ++y)
            for (byte x = 0; x < 16; ++x)
                for (byte z = 0; z < 16; ++z) {
                    val byteY = (byte) y;
                    Assertions.assertNull(chunkDataStorage.get(x, byteY, z));
                }
    }

    @Test
    void testSetGetUnset() {
        chunkDataStorage.set((byte) 1, (byte) 2, (byte) 3, "OMG");
        assertEquals(1, chunkDataStorage.getSize());
        assertEquals("OMG", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        Assertions.assertNull(chunkDataStorage.get((byte) 15, (byte) 20, (byte) 5));
        assertEquals("OMG", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
    }

    @Test
    void testRemove() {
        chunkDataStorage.set((byte) 4, (byte) 5, (byte) 6, "some");
        assertEquals(1, chunkDataStorage.getSize());

        assertEquals("some", chunkDataStorage.remove((byte) 4, (byte) 5, (byte) 6));
        assertEquals(0, chunkDataStorage.getSize());
    }

    @Test
    void testSetToNullIsRemove() {
        chunkDataStorage.set((byte) 7, (byte) 8, (byte) 9, "r/woosh");
        assertEquals(1, chunkDataStorage.getSize());
        chunkDataStorage.set((byte) 7, (byte) 8, (byte) 9, null);
        assertEquals(0, chunkDataStorage.getSize());
        Assertions.assertNull(chunkDataStorage.get((byte) 7, (byte) 8, (byte) 9));
    }

    @Test
    void testMultipleSetsToSingleCoordinateDontChangeSize() {
        assertEquals(0, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "original value");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "original value");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "updated value");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "some other value");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "original value");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "or nor");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "lol");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, null);
        assertEquals(0, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "smth again");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "and this");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "original");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.remove((byte) 1, (byte) 100, (byte) 2);
        assertEquals(0, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "127");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "original");
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 100, (byte) 2, "pseudo-null");
        assertEquals(1, chunkDataStorage.getSize());
    }

    @Test
    void testMultipleOperationsToSingleCoordinate() {
        assertEquals(0, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 2, (byte) 3, "hello");
        assertEquals(1, chunkDataStorage.getSize());
        assertEquals("hello", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        assertEquals("hello", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        assertEquals(1, chunkDataStorage.getSize());

        assertEquals("hello", chunkDataStorage.remove((byte) 1, (byte) 2, (byte) 3));
        assertEquals(0, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 2, (byte) 3, "world");
        assertEquals(1, chunkDataStorage.getSize());
        assertEquals("world", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        assertEquals("world", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        assertEquals(1, chunkDataStorage.getSize());

        assertEquals("world", chunkDataStorage.remove((byte) 1, (byte) 2, (byte) 3));
        assertEquals(0, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 4, (byte) 123, (byte) 9, "hmm");
        assertEquals("hmm", chunkDataStorage.get((byte) 4, (byte) 123, (byte) 9));
        assertEquals(1, chunkDataStorage.getSize());

        Assertions.assertNull(chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        assertEquals(1, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 2, (byte) 3, "wow");
        assertEquals(2, chunkDataStorage.getSize());
        assertEquals("wow", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        assertEquals("wow", chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        assertEquals(2, chunkDataStorage.getSize());

        chunkDataStorage.set((byte) 1, (byte) 2, (byte) 3, null);
        assertEquals(1, chunkDataStorage.getSize());
        Assertions.assertNull(chunkDataStorage.get((byte) 1, (byte) 2, (byte) 3));
        assertEquals(1, chunkDataStorage.getSize());
    }

    @Test
    void testEmptyIterator() {
        val iterator = chunkDataStorage.iterator();

        Assertions.assertFalse(iterator.hasNext());
        Assertions.assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void singleElementIterator() {
        chunkDataStorage.set((byte) 1, (byte) 7, (byte) 2, "cyanide");

        assertThat(StreamSupport.stream(chunkDataStorage.spliterator(), false)).containsExactly(
                SimplePair.of(new Vector(1, 7, 2), "cyanide")
        );
    }

    @Test
    void multipleElementIterator() {
        chunkDataStorage.set((byte) 1, (byte) 7, (byte) 2, "cyanide");
        chunkDataStorage.set((byte) 1, (byte) 8, (byte) 0, "and");
        chunkDataStorage.set((byte) 1, (byte) 8, (byte) 10, "happiness");

        assertThat(StreamSupport.stream(chunkDataStorage.spliterator(), false)).containsExactly(
                SimplePair.of(new Vector(1, 7, 2), "cyanide"),
                SimplePair.of(new Vector(1, 8, 0), "and"),
                SimplePair.of(new Vector(1, 8, 10), "happiness")
        );
    }

    @Test
    void multipleComplicatedElementIterator() {
        chunkDataStorage.set((byte) 0, (byte) 0, (byte) 0, "zero");
        chunkDataStorage.set((byte) 0, (byte) 0, (byte) 1, "practically zero");
        chunkDataStorage.set((byte) 1, (byte) 7, (byte) 2, "cyanide");
        chunkDataStorage.set((byte) 1, (byte) 8, (byte) 0, "and");
        chunkDataStorage.set((byte) 1, (byte) 8, (byte) 10, "happiness");
        chunkDataStorage.set((byte) 1, (byte) 254, (byte) 10, "close to border #1");
        chunkDataStorage.set((byte) 1, (byte) 255, (byte) 10, "close to border #2");
        chunkDataStorage.set((byte) 14, (byte) 255, (byte) 14, "close to border #3");
        chunkDataStorage.set((byte) 14, (byte) 255, (byte) 15, "close to border #4");
        chunkDataStorage.set((byte) 15, (byte) 255, (byte) 14, "close to border #5");
        chunkDataStorage.set((byte) 15, (byte) 255, (byte) 15, "close to border #6");

        assertThat(StreamSupport.stream(chunkDataStorage.spliterator(), false)).containsExactly(
                SimplePair.of(new Vector(0, 0, 0), "zero"),
                SimplePair.of(new Vector(0, 0, 1), "practically zero"),
                SimplePair.of(new Vector(1, 7, 2), "cyanide"),
                SimplePair.of(new Vector(1, 8, 0), "and"),
                SimplePair.of(new Vector(1, 8, 10), "happiness"),
                SimplePair.of(new Vector(1, 254, 10), "close to border #1"),
                SimplePair.of(new Vector(1, 255, 10), "close to border #2"),
                SimplePair.of(new Vector(14, 255, 14), "close to border #3"),
                SimplePair.of(new Vector(14, 255, 15), "close to border #4"),
                SimplePair.of(new Vector(15, 255, 14), "close to border #5"),
                SimplePair.of(new Vector(15, 255, 15), "close to border #6")
        );
    }
}