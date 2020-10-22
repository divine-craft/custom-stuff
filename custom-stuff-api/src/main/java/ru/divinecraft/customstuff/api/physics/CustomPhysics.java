package ru.divinecraft.customstuff.api.physics;

public interface CustomPhysics extends AutoCloseable {

    void enable();

    void disable();

    @Override
    default void close() {
        disable();
    }
}
