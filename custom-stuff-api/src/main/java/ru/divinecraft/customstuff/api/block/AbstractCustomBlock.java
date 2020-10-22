package ru.divinecraft.customstuff.api.block;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class AbstractCustomBlock implements CustomBlock {

    @NotNull CustomBlockManager manager;
    @NotNull Location location;

    @Override
    public @NotNull Location getLocation() {
        return location.clone();
    }
}
