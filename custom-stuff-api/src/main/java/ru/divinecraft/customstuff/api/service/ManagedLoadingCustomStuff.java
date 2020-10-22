package ru.divinecraft.customstuff.api.service;

public interface ManagedLoadingCustomStuff<T> extends LoadingCustomStuff<T> {

    boolean isSafelyStarted();

    void ready();
}
