package ru.divinecraft.customstuff.api.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.progrm_jarvis.javacommons.service.AbstractConcurrentService;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class AbstractCustomStuffService extends AbstractConcurrentService implements CustomStuffService {

    @NonNull AtomicBoolean loaded;

    protected AbstractCustomStuffService(final Lock lifecycleLock) {
        super(lifecycleLock);
        loaded = new AtomicBoolean();
    }

    protected abstract void onLoad();

    protected abstract void onUnload();

    @Override
    public void load() {
        final Lock lock;
        (lock = lifecycleLock).lock();
        try {
            if (loaded.compareAndSet(false, true)) onLoad();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void unload() {
        final Lock lock;
        (lock = lifecycleLock).lock();
        try {
            if (loaded.compareAndSet(true, false)) onUnload();
        } finally {
            lock.unlock();
        }
    }
}
