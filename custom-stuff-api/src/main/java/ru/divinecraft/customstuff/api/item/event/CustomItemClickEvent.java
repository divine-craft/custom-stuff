package ru.divinecraft.customstuff.api.item.event;

import lombok.*;
import lombok.experimental.NonFinal;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.divinecraft.customstuff.api.item.CustomItem;

@Value
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("PublicMethodNotExposedInInterface")
public class CustomItemClickEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Setter @NonFinal boolean cancelled = false;

    @Nullable CustomItem item;
    @NotNull Player player;
    @NotNull Action action;
    @NotNull BlockFace blockFace;
    boolean mainHand;
    @NotNull Block block;

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
