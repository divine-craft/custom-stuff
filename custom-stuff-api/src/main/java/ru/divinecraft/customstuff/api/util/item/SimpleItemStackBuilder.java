package ru.divinecraft.customstuff.api.util.item;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkState;

@Accessors(chain = true, fluent = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class SimpleItemStackBuilder<M extends ItemMeta> implements ItemStackBuilder<M> {

    final @NonNull ItemFactory itemFactory;

    @Setter @NonNull Material type;
    @Setter int amount = 1;
    @Setter short damage;
    @Nullable M meta;

    protected SimpleItemStackBuilder(final @NonNull ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public static <M extends ItemMeta> ItemStackBuilder<M> create(final @NonNull ItemFactory itemFactory) {
        return new SimpleItemStackBuilder<>(itemFactory);
    }

    public static <M extends ItemMeta> ItemStackBuilder<M> create(final @NonNull Server server) {
        return create(server.getItemFactory());
    }

    protected M nonNullMeta() {
        final M thisMeta;
        if ((thisMeta = meta) == null) {
            final Material thisType;

            //noinspection unchecked
            return meta = (M) ((thisType = type) == null
                    ? itemFactory.getItemMeta(Material.STONE) : itemFactory.getItemMeta(thisType));
        }

        return thisMeta;
    }

    @Override
    public @NotNull ItemStackBuilder<M> name(@Nullable final String name) {
        nonNullMeta().setDisplayName(name);

        return this;

    }

    @Override
    public @NotNull ItemStackBuilder<M> unbreakable(final boolean unbreakable) {
        nonNullMeta().setUnbreakable(unbreakable);

        return this;
    }

    @Override
    public @NotNull ItemStackBuilder<M> lore(@NonNull final List<@NotNull String> lore) {
        nonNullMeta().setLore(lore);

        return this;
    }

    @Override
    public @NotNull ItemStackBuilder<M> modifyMeta(@NonNull final Consumer<@NotNull M> metadataModifier) {
        metadataModifier.accept(nonNullMeta());

        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull <MNew extends ItemMeta> ItemStackBuilder<MNew> meta(@NonNull final MNew meta) {
        @SuppressWarnings("LocalVariableOfConcreteClass") final SimpleItemStackBuilder<MNew> self;
        //noinspection CastToConcreteClass
        (self = (SimpleItemStackBuilder<MNew>) this).meta = meta;

        return self;
    }

    @Override
    public @NotNull ItemStack build() {
        final Material thisType;
        checkState((thisType = type) != null, "Type is unset");

        val item = new ItemStack(thisType, amount, damage);
        final M thisMeta;
        if ((thisMeta = meta) != null) item.setItemMeta(thisMeta);

        return item;
    }

    @Override
    public @NotNull ItemStack build(@NonNull final Consumer<NBTItem> nbtModifier) {
        final NBTItem nbtItem;
        nbtModifier.accept(nbtItem = new NBTItem(build()));

        return nbtItem.getItem();
    }
}
