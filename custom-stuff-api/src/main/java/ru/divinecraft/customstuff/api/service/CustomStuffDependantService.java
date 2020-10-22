package ru.divinecraft.customstuff.api.service;

import lombok.NonNull;

public interface CustomStuffDependantService {

    void setup(@NonNull CustomStuff customStuff);
}
