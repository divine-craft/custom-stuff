package ru.divinecraft.customstuff.api.block.manager;

import lombok.NonNull;
import org.bukkit.NamespacedKey;

/**
 * Public APi for registering custom blocks before {@link CustomBlockManager} is created.
 * This should be registered as a service for the time of loading.
 */
@FunctionalInterface
public interface LoadingCustomBlockManager {

    void registerCustomBlock(@NonNull NamespacedKey type,
                             @NonNull CustomBlockManager.BlockFactory blockFactory);
}
