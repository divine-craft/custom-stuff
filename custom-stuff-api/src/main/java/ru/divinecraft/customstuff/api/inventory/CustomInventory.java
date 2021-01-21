package ru.divinecraft.customstuff.api.inventory;

import org.bukkit.entity.HumanEntity;

@FunctionalInterface
public interface CustomInventory {

    void showTo(HumanEntity viewer);
}
