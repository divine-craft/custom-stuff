package ru.divinecraft.customstuff.api.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class AbstractCustomStuffDependantService implements CustomStuffDependantService {

    @NonNull AtomicReference</* @Stable */ CustomStuff> customStuff = new AtomicReference<>();

    protected @NotNull CustomStuff customStuff() {
        return customStuff.get();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void setup(final @NonNull CustomStuff customStuff) {
        if (!this.customStuff.compareAndSet(null, customStuff)) throw new IllegalStateException(
                "Attempt to setup blocks multiple times"
        );
    }
}
