package ru.divinecraft.customstuff.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Utilities related to {@link Listeners Bukkit's event listeners}.
 */
@UtilityClass
public class Listeners {

    /**
     * Creates a new empty {@link Listener Bukkit's event listener}.
     * This is intended to be used with {@link
     * org.bukkit.plugin.PluginManager#registerEvent(Class, Listener, EventPriority, EventExecutor, Plugin)
     * } and {@link
     * org.bukkit.plugin.PluginManager#registerEvent(Class, Listener, EventPriority, EventExecutor, Plugin, boolean)
     * } as they require passing an instance of {@link Listener} to them
     * requiring it to be unique to use it in {@link org.bukkit.event.HandlerList#unregister(Listener)}.
     *
     * @return newly created unique {@link Listener Bukkit's event listener}
     */
    @Contract("-> new")
    public @NotNull Listener newEmptyListener() {
        return new EmptyListener();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class EmptyListener implements Listener {}
}
