package ru.divinecraft.customstuff.api.block.event;

import lombok.*;
import lombok.experimental.NonFinal;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.CustomBlock;

@Value
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("PublicMethodNotExposedInInterface")
public class CustomBlockBreakEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Setter @NonFinal boolean cancelled = false;

    @NotNull CustomBlock block;
    @NotNull Player player;

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
