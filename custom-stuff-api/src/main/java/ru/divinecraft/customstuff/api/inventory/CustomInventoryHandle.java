package ru.divinecraft.customstuff.api.inventory;

import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.*;
import org.jetbrains.annotations.NotNull;

public interface CustomInventoryHandle {

    CustomInventoryHandle EMPTY = new CustomInventoryHandle() {};

    ///////////////////////////////////////////////////////////////////////////
    // org.bukkit.event.inventory
    ///////////////////////////////////////////////////////////////////////////

    /*
    default void onBrew(@NotNull  final BrewEvent event) {}

    default void onBrewingStandFuel(@NotNull  final BrewingStandFuelEvent event) {}
    */

    default void onCraftItem(@NotNull final CraftItemEvent event) {}

    /*
    default void onFurnaceBurn(@NotNull  final FurnaceBurnEvent event) {}

    default void onFurnaceExtract(@NotNull  final FurnaceExtractEvent event) {}

    default void onFurnaceSmelt(@NotNull  final FurnaceSmeltEvent event) {}
    */

    default void onPrimaryInventoryClick(@NotNull final InventoryClickEvent event) {}

    default void onSecondaryInventoryClick(@NotNull final InventoryClickEvent event) {}

    default void onInventoryClose(@NotNull final InventoryCloseEvent event) {}

    default void onInventoryCreative(@NotNull final InventoryCreativeEvent event) {}

    default void onInventoryDrag(@NotNull final InventoryDragEvent event) {}

    /*
    default void onInventory(@NotNull final InventoryEvent event) {}
    */

    default void onInventoryInteract(@NotNull final InventoryInteractEvent event) {}

    default void onInventoryMoveItemFrom(@NotNull final InventoryMoveItemEvent event) {}

    default void onInventoryMoveItemTo(@NotNull final InventoryMoveItemEvent event) {}

    default void onInventoryOpen(@NotNull final InventoryOpenEvent event) {}

    default void onInventoryPickupItem(@NotNull final InventoryPickupItemEvent event) {}

    default void onPrepareAnvil(@NotNull final PrepareAnvilEvent event) {}

    default void onPrepareItemCraft(@NotNull final PrepareItemCraftEvent event) {}

    ///////////////////////////////////////////////////////////////////////////
    // org.bukkit.event.enchantment
    ///////////////////////////////////////////////////////////////////////////

    default void onEnchantItem(@NotNull final EnchantItemEvent event) {}

    default void onPrepareItemEnchant(@NotNull final PrepareItemEnchantEvent event) {}
}
