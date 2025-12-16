package me.jakepronger.spine.helpers;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class LoggerHelper {

    // todo: is this color coded support?
    // todo: does it include plugin name?

    private final Logger log;

    public LoggerHelper(JavaPlugin plugin) {
        this.log = plugin.getLogger();
    }

    public void info(String message) {
        log.info(message);
    }

    public void warn(String message) {
        log.warning(message);
    }

    public void error(String message) {
        log.severe(message);
    }

}
