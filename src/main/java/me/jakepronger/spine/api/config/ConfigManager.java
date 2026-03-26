package me.jakepronger.spine.api.config;

import lombok.experimental.Delegate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

    private final JavaPlugin plugin;

    @Delegate(types = ConfigurationSection.class) // This "steals" all methods from config
    private final FileConfiguration config;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = load();
    }

    public FileConfiguration load() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        return plugin.getConfig();
    }

    // todo: do we need this?
    public void reload() {
        load();
    }

    // todo: use async helpers
    public void save() {
        plugin.saveConfig();
    }

}
