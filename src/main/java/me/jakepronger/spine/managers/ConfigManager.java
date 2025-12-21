package me.jakepronger.spine.managers;

import me.jakepronger.spine.Spine;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class ConfigManager {

    // todo: remake this, fileconfiguration is in memory, adjustments need writing (async)

    private final JavaPlugin plugin;

    private final FileConfiguration config;
    private final HashMap<String, Object> values;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        values = new HashMap<>();
        load();
    }

    /**
     * Loads config.yml and variables into memory
     */
    @Deprecated // not required.. it's stored in memory in fileconfiguration type
    private void load() {

        plugin.saveDefaultConfig();
        plugin.reloadConfig();

        // dynamically load config and set all value pairs in ahasmap
        /*ConfigurationSection cs = config.getConfigurationSection("");
        if (cs == null) return;

        for (String key : cs.getKeys(false)) {
            Object value = cs.get(key);
            String fullPath = path.isEmpty() ? key : path + "." + key;
            if (value instanceof ConfigurationSection) {
                this.accept((ConfigurationSection) value, fullPath);
            } else {
                values.put(fullPath, value);
            }
        }*/
    }

    // load specific path?
    private void loadPath(String path) {

    }

    @Nullable
    public <T> T getValue(String path, Class<T> type) {
        Object value = values.getOrDefault(path, null);

        if (value == null) {
            // attempt to try load it from config
            loadPath(path);
        }

        if (type.isInstance(value)) {
            return type.cast(value);
        }

        Spine.getLogger().warn("&cError getting string using path: " + path);
        return null;
    }

}
