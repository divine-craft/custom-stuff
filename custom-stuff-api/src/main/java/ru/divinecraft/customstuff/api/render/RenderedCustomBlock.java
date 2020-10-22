package ru.divinecraft.customstuff.api.render;

import org.jetbrains.annotations.NotNull;

public interface RenderedCustomBlock extends AutoCloseable {

    void start();

    void rotate(float xRotation, float yRotation, float zRotation);

    /*
    void rotate(@NotNull BlockFace direction);

    void rotate(float dxRotation, float dyRotation, float dzRotation);

    void rotateTo(float xRotation, float yRotation, float zRotation);
    */

    void update(@NotNull CustomBlockRenderingProperties properties);

    @Override
    void close();
}
