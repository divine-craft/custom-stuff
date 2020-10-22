package ru.divinecraft.customstuff.api.block.properties;

public interface BlockProperties {

    boolean canExplode();

    // 0 for instant breaking
    long ticksPerBreakPhase();
}
