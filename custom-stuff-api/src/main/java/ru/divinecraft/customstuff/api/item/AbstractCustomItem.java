package ru.divinecraft.customstuff.api.item;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.item.manager.CustomItemManager;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class AbstractCustomItem implements CustomItem {

    @NotNull CustomItemManager itemManager;
}
