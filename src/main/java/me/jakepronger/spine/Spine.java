package me.jakepronger.spine;

import me.jakepronger.spine.api.SpineAPI;
import me.jakepronger.spine.core.Feature;
import me.jakepronger.spine.core.FeatureEngine;
import org.bukkit.plugin.java.JavaPlugin;

public class Spine {

    private final JavaPlugin plugin;
    private final FeatureEngine featureEngine; // todo: not sure if this should be here or SpineAPI
    private final SpineAPI api;

    private Spine(JavaPlugin plugin) {
        this.plugin = plugin;
        this.featureEngine = new FeatureEngine(this);
        this.api = new SpineAPI(this);
    }

    public JavaPlugin plugin() {
        return plugin;
    }

    public SpineAPI api() {
        return api;
    }

    public void feature(Class<? extends Feature> featureClass) {
        featureEngine.load(featureClass);
    }

}