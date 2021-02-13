package ru.divinecraft.customstuff.api.block;

import com.flowpowered.nbt.CompoundMap;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.block.properties.BlockProperties;

@ToString
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class StaticCustomBlock extends AbstractCustomBlock {

    @Getter @NotNull NamespacedKey type;
    @Getter @NotNull BlockProperties properties;
    @Nullable CompoundMap nbtTags;

    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType") // constructor
    protected StaticCustomBlock(final @NotNull NamespacedKey type, final @NotNull BlockProperties properties,
                                final @NotNull CustomBlockManager manager,
                                final @NotNull Location location,
                                final @Nullable CompoundMap nbtTags) {
        super(manager, location);

        this.type = type;
        this.properties = properties;
        this.nbtTags = nbtTags;
    }

    @Override
    public @Nullable CompoundMap readNbtTags() {
        final CompoundMap tags;
        return (tags = nbtTags) == null ? null : new CompoundMap(tags);
    }

    public static CustomBlock create(final @NotNull NamespacedKey type,
                                     final @NotNull BlockProperties properties,
                                     final @NotNull CustomBlockManager manager,
                                     final @NotNull Location location,
                                     final @Nullable CompoundMap nbtTags) {
        return new StaticCustomBlock(type, properties, manager, location, nbtTags);
    }
}
