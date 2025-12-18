package me.jakepronger.spine.interfaces;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.jakepronger.spine.dispatcher.CommandDispatcher;
import me.jakepronger.spine.dispatcher.EventDispatcher;
import me.jakepronger.spine.enums.Permission;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class Dispatcher {

    private final CommandDispatcher commands;
    private final EventDispatcher events;

    public Dispatcher(JavaPlugin plugin) {
        this.commands = new CommandDispatcher(plugin);
        this.events = new EventDispatcher(plugin);
    }

    // Master Method
    public void command(String name, Consumer<CommandSourceStack> action, @Nullable String description, @Nullable Permission permission, @Nullable String... aliases) {
        commands.command(name, action, description, permission, aliases);
    }

    // Overload: No Aliases
    public void command(String name, Consumer<CommandSourceStack> action, @Nullable String description, @Nullable Permission permission) {
        command(name, action, description, permission, null);
    }

    // Overload: Public Command (No Permission)
    public void command(String name, Consumer<CommandSourceStack> action, String description) {
        command(name, action, description, Permission.DEFAULT);
    }

    // Overload: Minimal
    public void command(String name, Consumer<CommandSourceStack> action) {
        command(name, action, "");
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
