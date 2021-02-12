package ru.divinecraft.customstuff.api.item.manager;

import lombok.NonNull;
import org.bukkit.NamespacedKey;

/**
 * Public APi for registering custom blocks before {@link CustomItemManager} is created.
 * This should be registered as a service for the time of loading.
 */
@FunctionalInterface
public interface LoadingCustomItemManager {

    void registerCustomItem(@NonNull NamespacedKey type,
                            @NonNull CustomItemManager.ItemFactory itemFactory);
}
