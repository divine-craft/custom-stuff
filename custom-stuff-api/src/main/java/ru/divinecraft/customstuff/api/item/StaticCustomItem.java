package ru.divinecraft.customstuff.api.item;

import com.flowpowered.nbt.CompoundMap;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.item.manager.CustomItemManager;
import ru.divinecraft.customstuff.api.item.properties.ItemProperties;

@ToString
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class StaticCustomItem extends AbstractCustomItem {

    @Getter @NotNull NamespacedKey type;
    @Getter @NotNull ItemProperties properties;
    @NotNull ItemStack originalItemStack;
    @Getter @Nullable NamespacedKey blockType;
    @Getter @Nullable CompoundMap blockNbtTags;

    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType") // constructor
    protected StaticCustomItem(final @NotNull NamespacedKey type,
                               final @NotNull ItemProperties properties,
                               final @NotNull CustomItemManager itemManager,
                               final @NotNull ItemStack originalItemStack,
                               final @Nullable NamespacedKey blockType,
                               final @Nullable CompoundMap blockNbtTags) {
        super(itemManager);

        this.type = type;
        this.properties = properties;
        this.originalItemStack = originalItemStack;
        this.blockType = blockType;
        this.blockNbtTags = blockNbtTags;
    }

    @Override
    public @NotNull ItemStack asItemStack() {
        return originalItemStack.clone();
    }

    public static CustomItem create(final @NotNull NamespacedKey type,
                                    final @NotNull ItemProperties properties,
                                    final @NotNull CustomItemManager itemManager,
                                    final @NotNull ItemStack originalItemStack,
                                    final @Nullable NamespacedKey blockType,
                                    final  @Nullable CompoundMap blockNbtTags) {
        return new StaticCustomItem(type, properties, itemManager, originalItemStack, blockType, blockNbtTags);
    }
}
