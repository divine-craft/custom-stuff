package ru.divinecraft.customstuff.api.block;

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
public class StaticCustomBlocks extends AbstractCustomBlocks {

    @NotNull Collection<@NotNull CustomBlockRegistration> customBlockRegistrations;

    @Override
    public @NotNull Enumeration<@NotNull CustomBlockRegistration> registrations() {
        return Collections.enumeration(customBlockRegistrations);
    }

    public static @NotNull CustomBlocks create(
            final @NonNull Collection<@NonNull CustomBlockRegistration> registrations
    ) {
        for (val registration : registrations) if (registration == null) throw new NullPointerException(
                "None of the registrations can be null"
        );

        return new StaticCustomBlocks(registrations);
    }
}
