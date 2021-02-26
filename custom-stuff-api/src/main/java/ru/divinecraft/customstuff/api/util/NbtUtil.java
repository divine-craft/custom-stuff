package ru.divinecraft.customstuff.api.util;

import com.flowpowered.nbt.*;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

@UtilityClass
public class NbtUtil {

    public byte orDefaultByte(final @NotNull CompoundMap tags, final @NotNull String tagName,
                              final byte defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_BYTE
                ? defaultValue : ((ByteTag) tag).getValue();
    }


    public byte orDefaultByte(final @NotNull CompoundMap tags, final @NotNull String tagName,
                              final Supplier<@NotNull Byte> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_BYTE
                ? defaultValueSupplier.get() : ((ByteTag) tag).getValue();
    }

    public short orDefaultShort(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                final short defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_SHORT
                ? defaultValue : ((ShortTag) tag).getValue();
    }


    public short orDefaultShort(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                final Supplier<@NotNull Short> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_SHORT
                ? defaultValueSupplier.get() : ((ShortTag) tag).getValue();
    }

    public int orDefaultInt(final @NotNull CompoundMap tags, final @NotNull String tagName,
                            final int defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_INT
                ? defaultValue : ((IntTag) tag).getValue();
    }


    public int orDefaultInt(final @NotNull CompoundMap tags, final @NotNull String tagName,
                            final @NotNull IntSupplier defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_INT
                ? defaultValueSupplier.getAsInt() : ((IntTag) tag).getValue();
    }

    public long orDefaultLong(final @NotNull CompoundMap tags, final @NotNull String tagName,
                              final long defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_LONG
                ? defaultValue : ((LongTag) tag).getValue();
    }


    public long orDefaultLong(final @NotNull CompoundMap tags, final @NotNull String tagName,
                              final @NotNull LongSupplier defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_LONG
                ? defaultValueSupplier.getAsLong() : ((LongTag) tag).getValue();
    }

    public float orDefaultFloat(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                final float defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_FLOAT
                ? defaultValue : ((FloatTag) tag).getValue();
    }


    public float orDefaultFloat(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                final Supplier<@NotNull Float> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_FLOAT
                ? defaultValueSupplier.get() : ((FloatTag) tag).getValue();
    }

    public double orDefaultDouble(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                  final double defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_DOUBLE
                ? defaultValue : ((DoubleTag) tag).getValue();
    }


    public double orDefaultDouble(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                  final @NotNull DoubleSupplier defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_DOUBLE
                ? defaultValueSupplier.getAsDouble() : ((DoubleTag) tag).getValue();
    }

    public byte[] orDefaultByteArray(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                     final byte[] defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_BYTE_ARRAY
                ? defaultValue : ((ByteArrayTag) tag).getValue();
    }


    public byte[] orDefaultByteArray(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                     final Supplier<byte[]> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_BYTE_ARRAY
                ? defaultValueSupplier.get() : ((ByteArrayTag) tag).getValue();
    }

    public String orDefaultString(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                  final String defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_STRING
                ? defaultValue : ((StringTag) tag).getValue();
    }


    public String orDefaultString(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                  final Supplier<String> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_STRING
                ? defaultValueSupplier.get() : ((StringTag) tag).getValue();
    }

    @SuppressWarnings("unchecked")
    public <T extends Tag<?>> List<T> orDefaultList(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                                    final List<T> defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_LIST
                ? defaultValue : ((ListTag<T>) tag).getValue();
    }

    @SuppressWarnings("unchecked")
    public <T extends Tag<?>> List<T> orDefaultList(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                                    final Supplier<List<T>> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_LIST
                ? defaultValueSupplier.get() : ((ListTag<T>) tag).getValue();
    }

    public CompoundMap orDefaultCompound(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                         final CompoundMap defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_COMPOUND
                ? defaultValue : ((CompoundTag) tag).getValue();
    }


    public CompoundMap orDefaultCompound(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                         final Supplier<CompoundMap> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_COMPOUND
                ? defaultValueSupplier.get() : ((CompoundTag) tag).getValue();
    }

    public short[] orDefaultShortArray(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                       final short[] defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_SHORT_ARRAY
                ? defaultValue : ((ShortArrayTag) tag).getValue();
    }


    public short[] orDefaultShortArray(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                       final Supplier<short[]> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_SHORT_ARRAY
                ? defaultValueSupplier.get() : ((ShortArrayTag) tag).getValue();
    }

    public int[] orDefaultIntArray(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                   final int[] defaultValue) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_INT_ARRAY
                ? defaultValue : ((IntArrayTag) tag).getValue();
    }


    public int[] orDefaultIntArray(final @NotNull CompoundMap tags, final @NotNull String tagName,
                                   final Supplier<int[]> defaultValueSupplier) {
        final Tag<?> tag;
        return (tag = tags.get(tagName)) == null || tag.getType() != TagType.TAG_INT_ARRAY
                ? defaultValueSupplier.get() : ((IntArrayTag) tag).getValue();
    }

    public int readInt(final @NotNull CompoundMap tags, final @NotNull String tagName,
                       final int defaultValue) {
        final Tag<?> tag;
        if ((tag = tags.get(tagName)) == null) return defaultValue;

        switch (tag.getType()) {
            case TAG_BYTE: return ((ByteTag) tag).getValue();
            case TAG_SHORT: return ((ShortTag) tag).getValue();
            case TAG_INT: return ((IntTag) tag).getValue();
            case TAG_LONG: return ((LongTag) tag).getValue().intValue();
            default: return defaultValue;
        }
    }

    public int readInt(final @NotNull CompoundMap tags, final @NotNull String tagName,
                       final IntSupplier defaultValueSupplier) {
        final Tag<?> tag;
        if ((tag = tags.get(tagName)) == null) return defaultValueSupplier.getAsInt();

        switch (tag.getType()) {
            case TAG_BYTE: return ((ByteTag) tag).getValue();
            case TAG_SHORT: return ((ShortTag) tag).getValue();
            case TAG_INT: return ((IntTag) tag).getValue();
            case TAG_LONG: return ((LongTag) tag).getValue().intValue();
            default: return defaultValueSupplier.getAsInt();
        }
    }

    public long readLong(final @NotNull CompoundMap tags, final @NotNull String tagName,
                         final long defaultValue) {
        final Tag<?> tag;
        if ((tag = tags.get(tagName)) == null) return defaultValue;

        switch (tag.getType()) {
            case TAG_BYTE: return ((ByteTag) tag).getValue();
            case TAG_SHORT: return ((ShortTag) tag).getValue();
            case TAG_INT: return ((IntTag) tag).getValue();
            case TAG_LONG: return ((LongTag) tag).getValue();
            default: return defaultValue;
        }
    }

    public long readLong(final @NotNull CompoundMap tags, final @NotNull String tagName,
                         final LongSupplier defaultValueSupplier) {
        final Tag<?> tag;
        if ((tag = tags.get(tagName)) == null) return defaultValueSupplier.getAsLong();

        switch (tag.getType()) {
            case TAG_BYTE: return ((ByteTag) tag).getValue();
            case TAG_SHORT: return ((ShortTag) tag).getValue();
            case TAG_INT: return ((IntTag) tag).getValue();
            case TAG_LONG: return ((LongTag) tag).getValue();
            default: return defaultValueSupplier.getAsLong();
        }
    }
}
