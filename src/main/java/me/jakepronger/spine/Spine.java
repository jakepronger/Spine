package me.jakepronger.spine;

import lombok.Getter;
import me.jakepronger.spine.interfaces.Dispatcher;
import me.jakepronger.spine.helpers.LoggerHelper;
import org.bukkit.plugin.java.JavaPlugin;

public class Spine {

    // Utilities

    // Messaging

    // Tasks? Math? Location

    // Config

    // GUI, Creating, Listeners

    @Getter
    private static LoggerHelper logger;

    @Getter
    private final Dispatcher dispatcher;

    private final JavaPlugin plugin;

    public Spine(JavaPlugin plugin) {
        this.plugin = plugin;
        dispatcher = new Dispatcher(plugin);
        logger = new LoggerHelper(plugin);
    }

}