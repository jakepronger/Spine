package me.jakepronger.spine.internal.models;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GUIData implements InventoryHolder {

    private final Inventory inventory;

    @Getter
    private final Map<Integer, Consumer<InventoryClickEvent>> events = new HashMap<>();

    @Getter
    @Setter
    private boolean stealable;

    public GUIData(String title, int rows) {
        this.inventory = Bukkit.createInventory(
                this,
                rows * 9,
                Component.text(title) // todo: use helpers for formatting
        );
    }

    public void addEvent(int slot, Consumer<InventoryClickEvent> e) {
        events.put(slot, e);
    }

    @Override
    public @NotNull Inventory getInventory() { return inventory; }

}
