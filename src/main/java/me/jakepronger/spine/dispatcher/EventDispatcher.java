package me.jakepronger.spine.dispatcher;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class EventDispatcher {

    private final JavaPlugin plugin;

    public EventDispatcher(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public <T extends Event> void event(
            Class<T> eventClass,
            EventPriority priority,
            boolean ignoreCancelled,
            Consumer<T> handler
    ) {
        Bukkit.getPluginManager().registerEvent(
                eventClass,
                new Listener() {},
                priority,
                (listener, event) -> handler.accept(eventClass.cast(event)),
                plugin,
                ignoreCancelled
        );
    }

}
