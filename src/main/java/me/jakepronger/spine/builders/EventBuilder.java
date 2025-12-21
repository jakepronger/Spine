package me.jakepronger.spine.builders;

import me.jakepronger.spine.registers.EventRegister;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import java.util.function.Consumer;

/**
 * Must use register() to initialize.
 * @param <T> Event class
 */
public class EventBuilder<T extends Event> {

    private final EventRegister register;

    private final Class<T> type;
    private final Consumer<T> action;

    private EventPriority priority;
    private boolean ignoreCancelled;

    public EventBuilder(EventRegister register, Class<T> type, Consumer<T> action) {
        this.register = register;
        this.type = type;
        this.action = action;
        this.priority = EventPriority.NORMAL;
    }

    public void register() {
        register.event(type, priority, ignoreCancelled, action);
    }

    public EventBuilder<T> eventPriority(EventPriority value) {
        priority = value;
        return this;
    }

    public EventBuilder<T> ignoreCancelled(boolean value) {
        this.ignoreCancelled = value;
        return this;
    }

}
