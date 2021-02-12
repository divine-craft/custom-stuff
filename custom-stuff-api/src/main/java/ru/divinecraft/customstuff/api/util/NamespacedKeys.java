package ru.divinecraft.customstuff.api.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.var;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Utilities related to {@link org.bukkit.NamespacedKey namespaced keys}.
 */
@UtilityClass
public class NamespacedKeys {

    /**
     * Maximal length of the {@link NamespacedKey} represented as a {@link String}.
     */
    public static final int MAX_NAMESPACED_KEY_LENGTH = 255;

    /**
     * Maximal length of the {@link NamespacedKey}'s namespace.
     */
    public static final int MAX_NAMESPACE_LENGTH = MAX_NAMESPACED_KEY_LENGTH - 2;

    /**
     * Maximal length of the {@link NamespacedKey}'s key.
     */
    public static final int MAX_KEY_LENGTH = MAX_NAMESPACED_KEY_LENGTH - 2;

    /**
     * Creates a new {@link NamespacedKey namespaced key} from the given namespace and key.
     *
     * @param namespace namespace of the created namespaced key
     * @param key key of the created namespaced key
     * @return created namespaced key
     *
     * @throws IllegalArgumentException if namespaced key cannot be created from given namespace and key
     */
    public @NotNull NamespacedKey of(final @NotNull String namespace, final @NotNull String key) {
        //noinspection deprecation
        return new NamespacedKey(namespace, key);
    }

    /**
     * Creates a new {@link NamespacedKey namespaced key} from the given plugin and key.
     *
     * @param plugin plugin whose name will be used as namespace of the created namespaced key
     * @param key key of the created namespaced key
     * @return created namespaced key
     *
     * @throws IllegalArgumentException if namespaced key cannot be created from given plugin and key
     */
    public @NotNull NamespacedKey of(final @NotNull Plugin plugin, final @NotNull String key) {
        return new NamespacedKey(plugin, key);
    }

    /**
     * Parses {@link NamespacedKey namespaced key} from the given {@link String string}.
     *
     * @param namespacedKey string representation of the namespaced key
     * @return parsed namespaced key
     *
     * @throws IllegalArgumentException if namespaced key cannot be created from given string
     */
    public @NotNull NamespacedKey parse(final @NonNull String namespacedKey) {
        if (namespacedKey.length() > MAX_NAMESPACED_KEY_LENGTH) throw new IllegalArgumentException(
                "Namespaced key's string representation cannot be longer than "
                        + MAX_NAMESPACED_KEY_LENGTH + " characters"
        );

        final int delimiterIndex;
        if ((delimiterIndex = namespacedKey.indexOf(':')) == -1) throw new IllegalArgumentException(
                "Namespaced key should consist of colon-delimited namespace and key"
        );

        return new NamespacedKey(
                namespacedKey.substring(0, delimiterIndex),
                namespacedKey.substring(delimiterIndex + 1)
        );
    }

    /**
     * Checks that the given string is a valid namespace for a {@link NamespacedKey namespaced key}.
     *
     * @param namespace namespace which should be checked
     * @return {@code true} if the given string is a valid namespaced of a {@link NamespacedKey namespaced key}
     * and {@code false} otherwise
     */
    // `[a-z0-9._-]+`
    public boolean isValidNamespace(final @NotNull String namespace) {
        final int length;
        if ((length = namespace.length()) == 0) throw new IllegalArgumentException("Namespace cannot be empty");
        if (length > MAX_NAMESPACE_LENGTH) throw new IllegalArgumentException(
                "Namespace cannot be longer than " + MAX_NAMESPACE_LENGTH
        );

        for (var i = 0; i < length; i++) {
            final char character;
            if ('a' <= (character = namespace.charAt(i)) && character <= 'z'
                    || '0' <= character && character <= '9'
                    || character == '.' || character == '_' || character == '-'
            ) continue;
            return false;
        }

        return true;
    }

    /**
     * Checks that the given string is a valid namespace for a {@link NamespacedKey namespaced key}.
     *
     * @param key key which should be checked
     * @return {@code true} if the given string is a valid namespaced of a {@link NamespacedKey namespaced key}
     * and {@code false} otherwise
     */
    // `[a-z0-9/._-]+`
    public boolean isValidKey(final @NotNull String key) {
        final int length;
        if ((length = key.length()) == 0) throw new IllegalArgumentException("Key cannot be empty");
        if (length > MAX_KEY_LENGTH) throw new IllegalArgumentException(
                "Namespace cannot be longer than " + MAX_KEY_LENGTH
        );

        for (var i = 0; i < length; i++) {
            final char character;
            if ('a' <= (character = key.charAt(i)) && character <= 'z'
                    || '0' <= character && character <= '9'
                    || character == '/' || character == '.' || character == '_' || character == '-'
            ) continue;

            return false;
        }

        return true;
    }
}
