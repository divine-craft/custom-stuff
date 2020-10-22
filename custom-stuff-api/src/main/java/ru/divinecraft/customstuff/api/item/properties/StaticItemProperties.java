package ru.divinecraft.customstuff.api.item.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@ToString
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public final class StaticItemProperties implements ItemProperties {

    private static final @NotNull ItemProperties UNSTACKABLE = create(1), STACKABLE = create(64);

    int maxStackSize;

    public static ItemProperties create(final int maxStackSize) {
        return new StaticItemProperties(maxStackSize);
    }

    public static @NotNull ItemProperties unstackable() {
        return UNSTACKABLE;
    }

    public static @NotNull ItemProperties stackable() {
        return STACKABLE;
    }
}
