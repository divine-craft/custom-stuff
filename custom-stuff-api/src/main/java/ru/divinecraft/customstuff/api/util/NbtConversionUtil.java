package ru.divinecraft.customstuff.api.util;

import com.flowpowered.nbt.*;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTType;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
@Deprecated // this class is temporary until a better API is implemented
public class NbtConversionUtil {

    public @NotNull CompoundTag toCompoundTag(final @NotNull NBTCompound nbtCompound) {
        return new CompoundTag(nbtCompound.getName(), toCompoundMap(nbtCompound));
    }

    public @NotNull CompoundMap toCompoundMap(final @NotNull NBTCompound compound) {
        final Set<String> keys;
        if ((keys = compound.getKeys()).isEmpty()) return new CompoundMap();

        val compoundMap = new CompoundMap();
        for (val key : keys)
            switch (compound.getType(key)) {
                case NBTTagEnd: {
                    // this shouldn't actually happen
                    compoundMap.put(key, new EndTag());
                    break;
                }
                case NBTTagByte: {
                    compoundMap.put(new ByteTag(key, compound.getByte(key)));
                    break;
                }
                case NBTTagShort: {
                    compoundMap.put(new ShortTag(key, compound.getShort(key)));
                    break;
                }
                case NBTTagInt: {
                    compoundMap.put(new IntTag(key, compound.getInteger(key)));
                    break;
                }
                case NBTTagLong: {
                    compoundMap.put(new LongTag(key, compound.getLong(key)));
                    break;
                }
                case NBTTagFloat: {
                    compoundMap.put(new FloatTag(key, compound.getFloat(key)));
                    break;
                }
                case NBTTagDouble: {
                    compoundMap.put(new DoubleTag(key, compound.getDouble(key)));
                    break;
                }
                case NBTTagByteArray: {
                    compoundMap.put(new ByteArrayTag(key, compound.getByteArray(key)));
                    break;
                }
                case NBTTagIntArray: {
                    compoundMap.put(new IntArrayTag(key, compound.getIntArray(key)));
                    break;
                }
                case NBTTagString: {
                    compoundMap.put(new StringTag(key, compound.getString(key)));
                    break;
                }
                case NBTTagList: {
                    ListTag<?> listTag;
                    // here starts very bad code due to no API for discovering exact type of list
                    // this is a reason for normal API to be created
                    // --snip--
                    // attempt to find valid list type
                    listTypeLookup:
                    {
                        try {
                            //noinspection unchecked, rawtypes
                            listTag = new ListTag(key, Tag.class, compound.getIntegerList(key)
                                    .stream()
                                    .map(value -> new IntTag("", value))
                                    .collect(Collectors.toList())
                            );
                            break listTypeLookup;
                        } catch (final Throwable ignored) {}
                        try {
                            //noinspection unchecked, rawtypes
                            listTag = new ListTag(key, Tag.class, compound.getLongList(key)
                                    .stream()
                                    .map(value -> new LongTag("", value))
                                    .collect(Collectors.toList())
                            );
                            break listTypeLookup;
                        } catch (final Throwable ignored) {}
                        try {
                            //noinspection unchecked, rawtypes
                            listTag = new ListTag(key, Tag.class, compound.getFloatList(key)
                                    .stream()
                                    .map(value -> new FloatTag("", value))
                                    .collect(Collectors.toList())
                            );
                            break listTypeLookup;
                        } catch (final Throwable ignored) {}
                        try {
                            //noinspection unchecked, rawtypes
                            listTag = new ListTag(key, Tag.class, compound.getDoubleList(key)
                                    .stream()
                                    .map(value -> new DoubleTag("", value))
                                    .collect(Collectors.toList())
                            );
                            break listTypeLookup;
                        } catch (final Throwable ignored) {}
                        try {
                            //noinspection unchecked, rawtypes
                            listTag = new ListTag(key, Tag.class, compound.getStringList(key)
                                    .stream()
                                    .map(value -> new StringTag("", value))
                                    .collect(Collectors.toList())
                            );
                            break listTypeLookup;
                        } catch (final Throwable ignored) {}
                        try {
                            //noinspection unchecked, rawtypes
                            listTag = new ListTag(key, Tag.class, compound.getCompoundList(key)
                                    .stream()
                                    .map(NbtConversionUtil::toCompoundTag)
                                    .collect(Collectors.toList())
                            );
                            break listTypeLookup;
                        } catch (final Throwable ignored) {}

                        throw new Error("Unknown NBT list type");
                    }
                    compoundMap.put(listTag);
                    break;
                }
                case NBTTagCompound: {
                    compoundMap.put(toCompoundTag(compound.getCompound(key)));
                    break;
                }
                default: throw new IllegalStateException("Unknown tag type: " + compound.getType(key));
            }

        return compoundMap;
    }

    public void addTagsToNbtCompound(final @NotNull NBTCompound compound,
                                     final @NotNull CompoundMap tags) {
        //@formatter:off
        for (val entry : tags.entrySet()) switch (entry.getValue().getType()) {
            //formatter:on
            case TAG_END: throw new RuntimeException("Raw END tag");
            case TAG_BYTE: {
                compound.setByte(entry.getKey(), ((ByteTag) entry.getValue()).getValue());
                break;
            }
            case TAG_SHORT: {
                compound.setShort(entry.getKey(), ((ShortTag) entry.getValue()).getValue());
                break;
            }
            case TAG_INT: {
                compound.setInteger(entry.getKey(), ((IntTag) entry.getValue()).getValue());
                break;
            }
            case TAG_LONG: {
                compound.setLong(entry.getKey(), ((LongTag) entry.getValue()).getValue());
                break;
            }
            case TAG_FLOAT: {
                compound.setFloat(entry.getKey(), ((FloatTag) entry.getValue()).getValue());
                break;
            }
            case TAG_DOUBLE: {
                compound.setDouble(entry.getKey(), ((DoubleTag) entry.getValue()).getValue());
                break;
            }
            case TAG_BYTE_ARRAY: {
                compound.setByteArray(entry.getKey(), ((ByteArrayTag) entry.getValue()).getValue());
                break;
            }
            case TAG_STRING: {
                compound.setString(entry.getKey(), ((StringTag) entry.getValue()).getValue());
                break;
            }
            case TAG_LIST: {
                val listTag = (ListTag<? extends Tag<?>>) entry.getValue();
                final Class<? extends Tag<?>> elementType;

                //noinspection ChainOfInstanceofChecks
                if ((elementType = listTag.getElementType()) == IntTag.class) {
                    val list = compound.getIntegerList(entry.getKey());
                    //noinspection unchecked
                    for (val tag : ((ListTag<IntTag>) listTag).getValue()) list.add(tag.getValue());
                } else if (elementType == LongTag.class) {
                    val list = compound.getLongList(entry.getKey());
                    //noinspection unchecked
                    for (val tag : ((ListTag<LongTag>) listTag).getValue()) list.add(tag.getValue());
                } else if (elementType == FloatTag.class) {
                    val list = compound.getFloatList(entry.getKey());
                    //noinspection unchecked
                    for (val tag : ((ListTag<FloatTag>) listTag).getValue()) list.add(tag.getValue());
                } else if (elementType == DoubleTag.class) {
                    val list = compound.getDoubleList(entry.getKey());
                    //noinspection unchecked
                    for (val tag : ((ListTag<DoubleTag>) listTag).getValue()) list.add(tag.getValue());
                } else if (elementType == StringTag.class) {
                    val list = compound.getStringList(entry.getKey());
                    //noinspection unchecked
                    for (val tag : ((ListTag<StringTag>) listTag).getValue()) list.add(tag.getValue());
                } else if (elementType == CompoundTag.class) {
                    val list = compound.getCompoundList(entry.getKey());
                    //noinspection unchecked
                    for (val tag : ((ListTag<CompoundTag>) listTag).getValue()) addTagsToNbtCompound(
                            list.addCompound(), tag.getValue()
                    );
                } else throw new RuntimeException("Unsupported List type");
                break;
            }
            case TAG_COMPOUND: {
                addTagsToNbtCompound(
                        compound.addCompound(entry.getKey()), ((CompoundTag) entry.getValue()).getValue()
                );
                break;
            }
            case TAG_INT_ARRAY: {
                compound.setIntArray(entry.getKey(), ((IntArrayTag) entry.getValue()).getValue());
                break;
            }
            case TAG_SHORT_ARRAY: throw new RuntimeException("short[]s are not supported");
        }
    }

    public Class<?> getFlowWrappedType(final @NotNull NBTType type) {
        switch (type) {
            case NBTTagEnd: return Object.class;
            case NBTTagByte: return Byte.class;
            case NBTTagShort: return Short.class;
            case NBTTagInt: return Integer.class;
            case NBTTagLong: return Long.class;
            case NBTTagFloat: return Float.class;
            case NBTTagDouble: return Double.class;
            case NBTTagByteArray: return byte[].class;
            case NBTTagIntArray: return int[].class;
            case NBTTagString: return String.class;
            case NBTTagList: return List.class;
            case NBTTagCompound: return CompoundMap.class;
            default: throw new IllegalStateException("Unknown tag type: " + type);
        }
    }
}
