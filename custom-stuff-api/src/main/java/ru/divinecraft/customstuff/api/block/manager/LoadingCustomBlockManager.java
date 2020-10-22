package ru.divinecraft.customstuff.api.block.manager;

import lombok.NonNull;

/**
 * Public APi for registering custom blocks before {@link CustomBlockManager} is created.
 * This should be registered as a service for the time of loading.
 */
public interface LoadingCustomBlockManager {

    void registerCustomBlock(@NonNull String customBlockTypeName,
                             @NonNull CustomBlockManager.BlockFactory blockFactory);
}
