package me.jakepronger.spine.api.builders;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import java.util.function.Consumer;

/**
 * Must use register() to initialize.
 * @param <T> Event class
 */
public class ListenerBuilder<T extends Event> {

    private final ListenerRegister register;

    private final Class<T> type;
    private final Consumer<T> action;

    private EventPriority priority;
    private boolean ignoreCancelled;

    public ListenerBuilder(ListenerRegister register, Class<T> type, Consumer<T> action) {
        this.register = register;
        this.type = type;
        this.action = action;
        this.priority = EventPriority.NORMAL;
    }

    public void register() {
        register.listener(type, priority, ignoreCancelled, action);
    }

    public ListenerBuilder<T> priority(EventPriority value) {
        priority = value;
        return this;
    }

    public ListenerBuilder<T> ignoreCancelled(boolean value) {
        this.ignoreCancelled = value;
        return this;
    }

}
