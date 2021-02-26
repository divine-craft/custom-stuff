package ru.divinecraft.customstuff.api.service;

import org.bukkit.plugin.Plugin;
import ru.divinecraft.zaraza.common.api.annotation.BukkitService;

@FunctionalInterface
@BukkitService("CustomStuff")
public interface BukkitLoadingCustomStuff extends LoadingCustomStuff<Plugin> {}
