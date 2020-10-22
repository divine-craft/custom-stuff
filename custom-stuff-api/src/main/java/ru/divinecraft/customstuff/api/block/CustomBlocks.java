package ru.divinecraft.customstuff.api.block;

import lombok.*;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.service.CustomStuff;
import ru.divinecraft.customstuff.api.service.CustomStuffDependantService;
import ru.divinecraft.customstuff.api.service.LoadingCustomStuff;
import ru.progrm_jarvis.javacommons.service.PendingService;

import java.util.Enumeration;

/**
 * A helper class for registering custom blocks.
 */
public interface CustomBlocks extends CustomStuffDependantService {

    @NotNull Enumeration<@NotNull CustomBlockRegistration> registrations();

    static void register(final @NonNull CustomBlocks customBlocks,
                         final @NonNull PendingService.OwnedService<
                                 LoadingCustomStuff.@NotNull PreLoaders, @NotNull CustomStuff
                                 > loadingCustomStuff) {
        {
            val registrations = customBlocks.registrations();
            val manager = loadingCustomStuff.service().blockManager();
            while (registrations.hasMoreElements()) {
                final CustomBlockRegistration registration;
                manager.registerCustomBlock(
                        (registration = registrations.nextElement()).name(), registration.blockFactory()
                );
            }
        }

        loadingCustomStuff.onceReady(customBlocks::setup);
    }

    interface CustomBlockRegistration {

        @Contract(pure = true)
        @NotNull String name();

        @Contract(pure = true)
        @NotNull CustomBlockManager.BlockFactory blockFactory();
    }


    @Value
    @Accessors(fluent = true)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class SimpleCustomBlockRegistration implements CustomBlockRegistration {

        @NonNull String name;
        @NonNull CustomBlockManager.BlockFactory blockFactory;

        public static @NotNull CustomBlockRegistration of(final @NonNull String name,
                                                          final @NonNull CustomBlockManager.BlockFactory blockFactory) {
            return new SimpleCustomBlockRegistration(name, blockFactory);
        }
    }
}
