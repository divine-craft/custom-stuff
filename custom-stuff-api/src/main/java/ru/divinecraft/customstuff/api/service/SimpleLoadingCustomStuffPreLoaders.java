package ru.divinecraft.customstuff.api.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import ru.divinecraft.customstuff.api.block.manager.LoadingCustomBlockManager;
import ru.divinecraft.customstuff.api.item.manager.LoadingCustomItemManager;

@Value
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@NonFinal public class SimpleLoadingCustomStuffPreLoaders implements LoadingCustomStuff.PreLoaders {

    @NonNull LoadingCustomBlockManager blockManager;
    @NonNull LoadingCustomItemManager itemManager;

    public static LoadingCustomStuff.PreLoaders create(final @NonNull LoadingCustomBlockManager blockManager,
                                                       final @NonNull LoadingCustomItemManager itemManager) {
        return new SimpleLoadingCustomStuffPreLoaders(blockManager, itemManager);
    }
}
