package me.jakepronger.spine;

import lombok.Getter;
import me.jakepronger.spine.listeners.GUIListener;
import me.jakepronger.spine.managers.ConfigManager;
import me.jakepronger.spine.managers.RegistryManager;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Spine {

    // Utilities

    // Messaging

    // Tasks? Math? Location

    @Getter
    private final RegistryManager registry;

    @Getter
    private final ConfigManager config;

    public Spine(JavaPlugin plugin) {
        registry = new RegistryManager(plugin);
        config = new ConfigManager(plugin);
        listeners();
    }

    private void listeners() {
        registry.listener(InventoryClickEvent.class, GUIListener::onClick);
    }

}