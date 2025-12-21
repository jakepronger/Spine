package me.jakepronger.spine;

import lombok.Getter;
import me.jakepronger.spine.managers.RegistryManager;
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
    private final RegistryManager register;

    private final JavaPlugin plugin;

    public Spine(JavaPlugin plugin) {
        this.plugin = plugin;
        register = new RegistryManager(plugin);
        logger = new LoggerHelper(plugin);
    }

}