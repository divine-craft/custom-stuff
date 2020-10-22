package ru.divinecraft.customstuff.api.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.divinecraft.customstuff.api.service.AbstractCustomStuffDependantService;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCustomItems extends AbstractCustomStuffDependantService implements CustomItems {}
