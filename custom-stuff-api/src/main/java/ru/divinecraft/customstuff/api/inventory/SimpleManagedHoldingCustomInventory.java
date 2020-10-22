package ru.divinecraft.customstuff.api.inventory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import ru.divinecraft.customstuff.api.inventory.manager.CustomInventoryManager;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class SimpleManagedHoldingCustomInventory implements ManagedHoldingCustomInventory {

    @Getter @NonNull Inventory inventory;
    @NonNull CustomInventoryManager.Closer closer;

    // constructor has to hold logic due to inventory holders' logic:
    @SuppressWarnings("ThisEscapedInObjectConstruction")
    protected SimpleManagedHoldingCustomInventory(final @NonNull Plugin plugin,
                                                  final @NonNull CustomInventoryManager inventoryManager,
                                                  final @NonNull CustomInventoryHandle handle,
                                                  final InventoryType type) {
        inventory = plugin.getServer().createInventory(this, type);

        closer = inventoryManager.registerHandle(inventory, handle);
    }

    // constructor has to hold logic due to inventory holders' logic:
    @SuppressWarnings("ThisEscapedInObjectConstruction")
    protected SimpleManagedHoldingCustomInventory(final @NonNull Plugin plugin,
                                                  final @NonNull CustomInventoryManager inventoryManager,
                                                  final @NonNull CustomInventoryHandle handle,
                                                  final InventoryType type, final @NonNull String title) {
        inventory = plugin.getServer().createInventory(this, type, title);

        closer = inventoryManager.registerHandle(inventory, handle);
    }

    // constructor has to hold logic due to inventory holders' logic:
    @SuppressWarnings("ThisEscapedInObjectConstruction")
    protected SimpleManagedHoldingCustomInventory(final @NonNull Plugin plugin,
                                                  final @NonNull CustomInventoryManager inventoryManager,
                                                  final @NonNull CustomInventoryHandle handle,
                                                  final int size) {
        inventory = plugin.getServer().createInventory(this, size);

        closer = inventoryManager.registerHandle(inventory, handle);
    }

    // constructor has to hold logic due to inventory holders' logic:
    @SuppressWarnings("ThisEscapedInObjectConstruction")
    protected SimpleManagedHoldingCustomInventory(final @NonNull Plugin plugin,
                                                  final @NonNull CustomInventoryManager inventoryManager,
                                                  final @NonNull CustomInventoryHandle handle,
                                                  final int size, final @NonNull String title) {
        inventory = plugin.getServer().createInventory(this, size, title);

        closer = inventoryManager.registerHandle(inventory, handle);
    }

    @Override
    public void showTo(final HumanEntity viewer) {
        viewer.openInventory(inventory);
    }

    public static ManagedHoldingCustomInventory create(final @NonNull Plugin plugin,
                                                       final @NonNull CustomInventoryManager inventoryManager,
                                                       final @NonNull CustomInventoryHandle handle,
                                                       final InventoryType type) {
        return new SimpleManagedHoldingCustomInventory(plugin, inventoryManager, handle, type);
    }

    public static ManagedHoldingCustomInventory create(final @NonNull Plugin plugin,
                                                       final @NonNull CustomInventoryManager inventoryManager,
                                                       final @NonNull CustomInventoryHandle handle,
                                                       final InventoryType type, final @NonNull String title) {
        return new SimpleManagedHoldingCustomInventory(plugin, inventoryManager, handle, type, title);
    }

    public static ManagedHoldingCustomInventory create(final @NonNull Plugin plugin,
                                                       final @NonNull CustomInventoryManager inventoryManager,
                                                       final @NonNull CustomInventoryHandle handle,
                                                       final int size) {
        return new SimpleManagedHoldingCustomInventory(plugin, inventoryManager, handle, size);
    }

    public static ManagedHoldingCustomInventory create(final @NonNull Plugin plugin,
                                                       final @NonNull CustomInventoryManager inventoryManager,
                                                       final @NonNull CustomInventoryHandle handle,
                                                       final int size, final @NonNull String title) {
        return new SimpleManagedHoldingCustomInventory(plugin, inventoryManager, handle, size, title);
    }

    @Override
    public void close() {
        closer.close();
    }
}
