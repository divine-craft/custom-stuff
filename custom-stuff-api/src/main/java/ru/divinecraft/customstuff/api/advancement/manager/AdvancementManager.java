package ru.divinecraft.customstuff.api.advancement.manager;

import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.intellij.lang.annotations.Language;

public interface AdvancementManager {

    void registerAdvancement(@NonNull NamespacedKey key, @Language("json") @NonNull String advancement);

    void unregisterAdvancement(@NonNull NamespacedKey key);
}
