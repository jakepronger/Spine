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
    private final RegistryManager register;

    @Getter
    private final ConfigManager config;

    public Spine(JavaPlugin plugin) {
        register = new RegistryManager(plugin);
        config = new ConfigManager(plugin);
        events();
    }

    private void events() {
        register.event(InventoryClickEvent.class, GUIListener::onClick);
    }

}