package ru.divinecraft.customstuff.api.chunk;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.progrm_jarvis.javacommons.object.Pair;

import java.util.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ArrayBasedChunkDataStorage<@NotNull T> implements ChunkDataStorage<T> {

    public static final int LAYERS_COUNT = 1 << 8, LAYER_SIZE = (1 << 4) * (1 << 4);

    @NonNull LayerAllocator<T> allocator;

    @Getter int size;
    /*
     * There are 65 536 possible block locations in a chunk which is 2^16.
     * X and Z are 0 to 15 each, which is 4 bits per value
     * Y is 0 to 255 which is 8 bits per value
     * Thus, in order not to pollute heap 256x256 arrays get allocated
     * where the column is indexed as (y) and the layer is indexed as ((x << 4) | (z)).
     */
    @Nullable T @Nullable [] @Nullable [] layers;
    /*
     * In order ro minimize heap size, array of bytes is used.
     * Though it leads to values being -128 to 127 ~> 0 to 255 after sign conversion
     * So 0 has dual meaning:
     * - max filling after increasing operation
     * - empty (meaning that the related layer should be deleted) after decreasing operation
     */
    byte occupiedLayers;
    /*
     * Same rules are applied as to `occupiedLayers`
     */
    byte @Nullable [] layerSizes;

    // inline me pls
    protected static int indexY(final byte y) {
        return y & 0xFF;
    }

    // inline me pls
    protected static int indexXZ(final byte x, final byte z) {
        return (x & 0xF) << 4 | z & 0xF;
    }

    // inline me pls
    protected static int xFromIndexXZ(final int indexXZ) {
        return indexXZ >> 4;
    }

    // inline me pls
    protected static int zFromIndexXZ(final int indexXZ) {
        return indexXZ & 0xF;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    @Contract(pure = true)
    public @Nullable T get(final byte x, final byte y, final byte z) {
        final T[][] layers;
        if ((layers = this.layers) == null) return null;

        final T[] layer;
        return (layer = layers[indexY(y)]) == null ? null : layer[indexXZ(x, z)];
    }

    @Override
    public void set(final byte x, final byte y, final byte z, final @Nullable T value) {
        final T[][] layers;
        if ((layers = this.layers) == null) {
            if (value == null) return; // value is already set to null

            // allocate layers and allocate new layer for the value
            final int indexY;
            ((this.layers = allocator.allocateLayers())[indexY = indexY(y)]
                    = allocator.allocateLayer())[indexXZ(x, z)] = value;

            // allocate `layerSizes` and set size to 1 for the current layer
            (layerSizes = new byte[LAYERS_COUNT])[indexY] = 1;
            occupiedLayers = 1; // there were no layers before
            size = 1;

            return;
        }

        final T[] layer;
        final int indexY;
        if ((layer = layers[indexY = indexY(y)]) == null) {
            if (value == null) return; // value is already set to null

            // allocate new layer for the value
            (layers[indexY] = allocator.allocateLayer())[indexXZ(x, z)] = value;
            // set size to 1 for the current layer
            layerSizes[indexY] = 1;
            ++occupiedLayers; // there were some layers before
            ++size;

            return;
        }

        val indexXZ = indexXZ(x, z);
        /*
         * Compare new value to the old one in order to free layer(s) if needed
         */
        if (value == null) {
            if (layer[indexXZ] != null) {
                // value gets removed
                if (--layerSizes[indexY] == 0) if (--occupiedLayers == 0) this.layers = null; // no more layers
                else layers[indexY] = null;
                --size;
            }
            // value is already set to null, nothing to do here
        } else { // value != null
            if (layer[indexXZ] == null) {
                // size should only change if it is an insertion
                ++layerSizes[indexY];
                ++size;
            }
            layer[indexXZ] = value;
        }
    }

    @Override
    public @Nullable T remove(final byte x, final byte y, final byte z) {
        final T[][] layers;
        if ((layers = this.layers) == null) return null;

        final T[] layer;
        final int indexY;
        if ((layer = layers[indexY = indexY(y)]) == null) return null;

        final int indexXZ;
        final T oldValue;
        if ((oldValue = (layer[indexXZ = indexXZ(x, z)])) != null) { // actual removal
            // layer may get freed:
            if (--layerSizes[indexY] == 0) if (--occupiedLayers == 0) this.layers = null; // no more layers
            else layers[indexY] = null;
            else layer[indexXZ] = null; // regular element removal
            --size;
        }

        return oldValue;
    }

    public static <T> ChunkDataStorage<T> withAllocator(final @NonNull ArrayBasedChunkDataStorage.LayerAllocator<T> allocator) {
        return new ArrayBasedChunkDataStorage<>(allocator);
    }

    @Override
    public @NotNull Iterator<@NotNull Pair<@NotNull Vector, @NotNull T>> iterator() {
        return new NonCheckingIterator();
    }

    @Override
    public Spliterator<@NotNull Pair<@NotNull Vector, @NotNull T>> spliterator() {
        return Spliterators.spliterator(
                new NonCheckingIterator(), size, Spliterator.DISTINCT | Spliterator.SIZED | Spliterator.NONNULL
        );
    }

    public interface LayerAllocator<T> {

        int LAYERS_COUNT = ArrayBasedChunkDataStorage.LAYERS_COUNT, LAYER_SIZE = ArrayBasedChunkDataStorage.LAYER_SIZE;

        @Nullable T @NotNull [] @Nullable [] allocateLayers();

        @Nullable T @NotNull [] allocateLayer();
    }

    @FieldDefaults(level = AccessLevel.PROTECTED)
    protected class NonCheckingIterator implements Iterator<@NotNull Pair<@NotNull Vector, @NotNull T>> {

        @Nullable Pair<@NotNull Vector, @NotNull T> next;

        // Variables are int as all operations with them happen as with ints in JVM
        int lastLayerY = -1, xz = 0, leftOnCurrentLayer = 0;
        T[] layer;
        boolean canRemove = false;

        { // note: this should be below all variable initialization
            computeNext();
        }

        protected void computeNext() {
            if (layers == null /* no data at all */) return;

            T[] layer;
            int leftOnCurrentLayer, xz;
            var y = lastLayerY;
            if ((layer = this.layer) == null) { // fine new layer
                val layerSizes = ArrayBasedChunkDataStorage.this.layerSizes;
                assert layerSizes != null; // nullabilty is same as the one of layers

                do if (++y == 256) { // no more possible layers
                    next = null;
                    return;
                } while ((leftOnCurrentLayer = 0xFF & layerSizes[y]) == 0);

                this.layer = layer = layers[lastLayerY = y];

                assert layer != null;
                xz = 0;
            } else {
                leftOnCurrentLayer = this.leftOnCurrentLayer;
                xz = this.xz;
            }

            // at this point we have a non-null non-empty layer and xz set to valid  value

            for (; xz < LAYER_SIZE; xz++) {
                T value;
                if ((value = layer[xz]) != null) {
                    // next was found
                    next = Pair.of(new Vector(xFromIndexXZ(xz), y, zFromIndexXZ(xz)), value);

                    // check if this layer has ended
                    if ((this.leftOnCurrentLayer = --leftOnCurrentLayer) == 0) this.layer = null;
                    else this.xz = xz + 1; // make the next iteration start from the new value

                    return;
                }
            }

            // xz exceeded size but leftOnCurrentLayer did not become 0
            throw new ConcurrentModificationException(
                    "Iterated ChunkDataStorage might have been modified while being iterated"
            );
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public @NotNull Pair<@NotNull Vector, @NotNull T> next() {
            final Pair<@NotNull Vector, @NotNull T> next;
            if ((next = this.next) == null) throw new NoSuchElementException("There are no more elements");

            computeNext();
            canRemove = true;

            return next;
        }

        @Override
        public void remove() {
            if (!canRemove) throw new IllegalStateException("Element cannot be removed");

            final int previousXz;
            ArrayBasedChunkDataStorage.this.remove(
                    (byte) xFromIndexXZ(previousXz = xz - 1), (byte) lastLayerY, (byte) zFromIndexXZ(previousXz)
            );

            canRemove = false;
        }
    }
}
