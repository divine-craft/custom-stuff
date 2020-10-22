package ru.divinecraft.customstuff.api.block;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.divinecraft.customstuff.api.service.AbstractCustomStuffDependantService;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCustomBlocks extends AbstractCustomStuffDependantService implements CustomBlocks {}
