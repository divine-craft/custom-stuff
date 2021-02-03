package ru.divinecraft.customstuff.api.service;

import lombok.NonNull;

@FunctionalInterface
public interface CustomStuffDependantService {

    void setup(@NonNull CustomStuff customStuff);
}
