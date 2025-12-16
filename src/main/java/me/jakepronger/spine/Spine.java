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
    private static JavaPlugin plugin;

    @Getter
    public final Dispatcher dispatcher;

    @Getter
    public static LoggerHelper log;

    public Spine(JavaPlugin plugin) {
        Spine.plugin = plugin;
        dispatcher = new Dispatcher(plugin);
        log = new LoggerHelper(plugin);
    }

}