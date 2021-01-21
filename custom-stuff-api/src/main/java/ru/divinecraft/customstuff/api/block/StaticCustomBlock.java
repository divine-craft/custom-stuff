package ru.divinecraft.customstuff.api.block;

import com.flowpowered.nbt.CompoundMap;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.block.properties.BlockProperties;

@ToString
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class StaticCustomBlock extends AbstractCustomBlock {

    @Getter @NotNull String typeName;
    @Getter @NotNull BlockProperties properties;
    @Nullable CompoundMap nbtTags;

    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType") // constructor
    protected StaticCustomBlock(final @NotNull String typeName, final @NotNull BlockProperties properties,
                                final @NotNull CustomBlockManager manager,
                                final @NotNull Location location,
                                final @Nullable CompoundMap nbtTags) {
        super(manager, location);

        this.typeName = typeName;
        this.properties = properties;
        this.nbtTags = nbtTags;
    }

    @Override
    public @Nullable CompoundMap readNbtTags() {
        final CompoundMap tags;
        return (tags = nbtTags) == null ? null : new CompoundMap(nbtTags);
    }

    public static CustomBlock create(final @NotNull String typeName,
                                     final @NotNull BlockProperties properties,
                                     final @NotNull CustomBlockManager manager,
                                     final @NotNull Location location,
                                     final @Nullable CompoundMap nbtTags) {
        return new StaticCustomBlock(typeName, properties, manager, location, nbtTags);
    }
}
