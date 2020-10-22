package ru.divinecraft.customstuff.api.block.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class StaticBlockProperties implements BlockProperties {

    boolean canExplode;

    long ticksPerBreakPhase;

    public static BlockProperties create(final boolean canExplode,
                                         final long ticksPerBreakPhase) {
        return new StaticBlockProperties(canExplode, ticksPerBreakPhase);
    }
}
