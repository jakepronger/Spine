package me.jakepronger.spine.registers;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class ListenerRegister {

    private final JavaPlugin plugin;

    public ListenerRegister(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public <T extends Event> void listener(
            Class<T> eventClass,
            EventPriority priority,
            boolean ignoreCancelled,
            Consumer<T> action
    ) {
        Bukkit.getPluginManager().registerEvent(
                eventClass,
                new Listener() {},
                priority,
                (listener, event) -> action.accept(eventClass.cast(event)),
                plugin,
                ignoreCancelled
        );
    }

}
