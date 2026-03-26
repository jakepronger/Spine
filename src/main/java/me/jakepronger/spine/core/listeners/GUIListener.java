package me.jakepronger.spine.core.listeners;

import me.jakepronger.spine.api.objects.GUIData;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUIListener {

    public static void onClick(InventoryClickEvent event) {

        // 1. Is the player currently LOOKING at one of our GUIs?
        // If not, stop immediately. This is the most efficient "filter".
        if (!(event.getInventory().getHolder() instanceof GUIData model)) {
            return;
        }

        // 2. Identify what was clicked
        Inventory clickedInv = event.getClickedInventory();
        if (clickedInv == null)
            return;

        // 3. Logic for clicking inside the GUI
        if (clickedInv.getHolder() instanceof GUIData) {
            if (!model.isStealable())
                event.setCancelled(true);

            var handler = model.getEvents().get(event.getSlot());
            if (handler != null)
                handler.accept(event);
        }

        // 4. Logic for clicking their own inventory WHILE our GUI is open
        else {
            // Stop items from shift-clicking INTO the GUI
            if (event.isShiftClick() && !model.isStealable()) {
                event.setCancelled(true);
            }
        }

    }

}
