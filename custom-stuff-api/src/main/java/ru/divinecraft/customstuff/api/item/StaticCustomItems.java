package ru.divinecraft.customstuff.api.item;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class StaticCustomItems extends AbstractCustomItems {

    @NotNull Collection<@NotNull CustomItemRegistration> customItemRegistrations;

    @Override
    public @NotNull Enumeration<@NotNull CustomItemRegistration> registrations() {
        return Collections.enumeration(customItemRegistrations);
    }

    public static @NotNull CustomItems create(
            final @NonNull Collection<@NonNull CustomItemRegistration> registrations
    ) {
        for (val registration : registrations) if (registration == null) throw new NullPointerException(
                "None of the registrations can be null"
        );

        return new StaticCustomItems(registrations);
    }
}
