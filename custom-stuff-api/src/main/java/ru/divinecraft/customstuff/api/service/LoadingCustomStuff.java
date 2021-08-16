package ru.divinecraft.customstuff.api.service;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.manager.LoadingCustomBlockManager;
import ru.divinecraft.customstuff.api.item.manager.LoadingCustomItemManager;
import ru.progrm_jarvis.javacommons.service.PendingService;

@FunctionalInterface
public interface LoadingCustomStuff<O> extends PendingService<O, LoadingCustomStuff.PreLoaders, CustomStuff> {

    interface PreLoaders {

        @Contract(pure = true)
        @NotNull LoadingCustomItemManager itemManager();

        @Contract(pure = true)
        @NotNull LoadingCustomBlockManager blockManager();
    }
}
