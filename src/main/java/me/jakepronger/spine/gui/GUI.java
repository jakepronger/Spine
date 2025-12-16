package me.jakepronger.spine.gui;

import me.jakepronger.spine.interfaces.Dispatcher;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GUI {

    private final String title;
    private final int rows;
    private final Inventory inventory;

    private final Dispatcher dispatcher;

    private final Map<Integer, Consumer<InventoryClickEvent>> clickHandlers = new HashMap<>();

    public GUI(Dispatcher dispatcher, String title, int rows) {
        this.title = title;
        this.rows = rows;
        this.inventory = Bukkit.createInventory(
                null,
                rows * 9,
                Component.text(title)
        );
        this.dispatcher = dispatcher;
        registerListener();
    }

    // Internal listener for clicks and closes
    private void registerListener() {

        dispatcher.event(InventoryClickEvent.class, event -> {
            if (!event.getInventory().equals(inventory)) return;

            event.setCancelled(true); // Prevent moving items
            Consumer<InventoryClickEvent> handler = clickHandlers.get(event.getRawSlot());
            if (handler != null) {
                handler.accept(event);
            }
        });

        dispatcher.event(InventoryCloseEvent.class, event -> {
            if (event.getInventory().equals(inventory)) {
                clickHandlers.clear(); // Clean up
            }
        });

    }

}
