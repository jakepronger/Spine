package me.jakepronger.spine.interfaces;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.jakepronger.spine.dispatcher.CommandDispatcher;
import me.jakepronger.spine.dispatcher.EventDispatcher;
import me.jakepronger.spine.enums.Permission;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class Dispatcher {

    private final CommandDispatcher commands;
    private final EventDispatcher events;

    public Dispatcher(JavaPlugin plugin) {
        this.commands = new CommandDispatcher(plugin);
        this.events = new EventDispatcher(plugin);
    }

    public void register(
            String name,
            Consumer<CommandSourceStack> action,
            String description,
            Permission permission
    ) {
        commands.command(
                name,
                action,
                description,
                permission
        );
    }

    public <T extends Event> void event(
            Class<T> eventClass,
            EventPriority priority,
            boolean ignoreCancelled,
            Consumer<T> handler
    ) {
        events.event(
                eventClass,
                priority,
                ignoreCancelled,
                handler
        );
    }

    public <T extends Event> void event(
            Class<T> eventClass,
            Consumer<T> handler
    ) {
        event(
                eventClass,
                EventPriority.NORMAL,
                false,
                handler
        );
    }

    public <T extends Event> void event(
            Class<T> eventClass,
            boolean ignoreCancelled,
            Consumer<T> handler
    ) {
        event(
                eventClass,
                EventPriority.NORMAL,
                ignoreCancelled,
                handler
        );
    }

}
