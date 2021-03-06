package ru.divinecraft.customstuff.api.util.item;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public interface ItemStackBuilder<M extends ItemMeta> {

    @Contract("_ -> this")
    @NotNull ItemStackBuilder<M> type(@NonNull Material type);

    @Contract("_ -> this")
    @NotNull ItemStackBuilder<M> amount(int amount);

    @Contract("_ -> this")
    @NotNull ItemStackBuilder<M> damage(short damage);

    @Contract("_ -> this")
    @NotNull ItemStackBuilder<M> name(@Nullable String name);

    @Contract("_ -> this")
    @NotNull ItemStackBuilder<M> unbreakable(boolean unbreakable);

    @Contract("_ -> this")
    @NotNull ItemStackBuilder<M> lore(@NonNull List<@NotNull String> lore);

    @Contract("_ -> this")
    @NotNull <MNew extends ItemMeta> ItemStackBuilder<MNew> meta(@NonNull MNew meta);

    @Contract("_ -> this")
    @NotNull ItemStackBuilder<M> modifyMeta(@NonNull Consumer<@NotNull M> metadataModifier);

    @NotNull ItemStack build();

    @NotNull ItemStack build(@NonNull Consumer<NBTItem> nbtModifier);
}
