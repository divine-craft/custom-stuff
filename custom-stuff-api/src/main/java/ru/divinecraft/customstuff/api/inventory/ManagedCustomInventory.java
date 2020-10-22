package ru.divinecraft.customstuff.api.inventory;

/**
 * A {@link CustomInventory custom inventory} managing its resources
 * thus being auto-closeable (as they should be released).
 */
public interface ManagedCustomInventory extends CustomInventory, AutoCloseable {

    @Override
    void close();
}
