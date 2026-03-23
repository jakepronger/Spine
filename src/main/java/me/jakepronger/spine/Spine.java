package me.jakepronger.spine;

import me.jakepronger.spine.api.SpineAPI;
import me.jakepronger.spine.core.Feature;
import org.bukkit.plugin.java.JavaPlugin;

public class Spine {

    private final JavaPlugin plugin;
    private final SpineAPI api;

    private Spine(JavaPlugin plugin) {
        this.plugin = plugin;
        this.api = new SpineAPI(this);
    }

    public JavaPlugin plugin() {
        return plugin;
    }

    public SpineAPI api() {
        return api;
    }

    public void feature(Class<? extends Feature> featureClass) {
        api.features().load(featureClass);
    }

}