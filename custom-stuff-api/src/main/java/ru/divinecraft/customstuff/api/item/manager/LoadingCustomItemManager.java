package ru.divinecraft.customstuff.api.item.manager;

import lombok.NonNull;

/**
 * Public APi for registering custom blocks before {@link CustomItemManager} is created.
 * This should be registered as a service for the time of loading.
 */
public interface LoadingCustomItemManager {

    void registerCustomItem(@NonNull String customItemTypeName,
                            @NonNull CustomItemManager.ItemFactory itemFactory);
}
