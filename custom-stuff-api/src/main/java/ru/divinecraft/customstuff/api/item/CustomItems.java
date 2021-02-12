package ru.divinecraft.customstuff.api.item;

import lombok.*;
import lombok.experimental.Accessors;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.item.manager.CustomItemManager;
import ru.divinecraft.customstuff.api.item.properties.ItemProperties;
import ru.divinecraft.customstuff.api.service.CustomStuff;
import ru.divinecraft.customstuff.api.service.CustomStuffDependantService;
import ru.divinecraft.customstuff.api.service.LoadingCustomStuff;
import ru.progrm_jarvis.javacommons.service.PendingService;

import java.util.Enumeration;

public interface CustomItems extends CustomStuffDependantService {

    @NotNull Enumeration<@NotNull CustomItemRegistration> registrations();

    static void register(final @NonNull CustomItems customItems,
                         final @NonNull PendingService.OwnedService<
                                 LoadingCustomStuff.@NotNull PreLoaders, @NotNull CustomStuff
                                 > loadingCustomStuff) {
        {
            val registrations = customItems.registrations();
            val manager = loadingCustomStuff.service().itemManager();
            while (registrations.hasMoreElements()) {
                final CustomItemRegistration registration;
                manager.registerCustomItem(
                        (registration = registrations.nextElement()).type(), registration.itemFactory()
                );
            }
        }

        loadingCustomStuff.onceReady(customItems::setup);
    }

    static @NotNull CustomItemRegistration blockItemRegistration(final @NotNull NamespacedKey type,
                                                                 final @NotNull ItemProperties properties,
                                                                 final @NotNull ItemStack bukkitItem) {
        return SimpleCustomItemRegistration.of(
                type,
                (manager, nbt) -> StaticCustomItem.create(type, properties, manager, bukkitItem, type, nbt)
        );
    }

    interface CustomItemRegistration {

        @Contract(pure = true)
        @NotNull NamespacedKey type();

        @Contract(pure = true)
        @NotNull CustomItemManager.ItemFactory itemFactory();
    }

    @Value
    @Accessors(fluent = true)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class SimpleCustomItemRegistration implements CustomItemRegistration {
        @NonNull NamespacedKey type;
        @NonNull CustomItemManager.ItemFactory itemFactory;

        public static @NotNull CustomItemRegistration of(final @NonNull NamespacedKey type,
                                                         final @NonNull CustomItemManager.ItemFactory itemFactory) {
            return new SimpleCustomItemRegistration(type, itemFactory);
        }
    }
}
