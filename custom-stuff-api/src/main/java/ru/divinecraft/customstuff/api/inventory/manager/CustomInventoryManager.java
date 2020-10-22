package ru.divinecraft.customstuff.api.inventory.manager;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.inventory.CustomInventoryHandle;

public interface CustomInventoryManager {

    @NotNull CustomInventoryManager.Closer registerHandle(@NotNull Inventory inventory,
                                                          @NotNull CustomInventoryHandle handle);

    @Nullable CustomInventoryHandle matchHandle(@NotNull Inventory inventory);

    @FunctionalInterface
    interface Closer extends AutoCloseable {

        @Override
        void close();
    }
}
