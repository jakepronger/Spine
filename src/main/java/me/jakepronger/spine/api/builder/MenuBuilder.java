package me.jakepronger.spine.api.builder;

import me.jakepronger.spine.api.objects.GUIData;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MenuBuilder {

    private final GUIData data;
    private final Inventory inv;

    public MenuBuilder(String title, int rows) {
        data = new GUIData(title, rows);
        inv = data.getInventory();
    }

    public Inventory build() {
        return data.getInventory();
    }

    public MenuBuilder stealable(boolean canSteal) {
        data.setStealable(canSteal);
        return this;
    }

    public MenuBuilder clickable(int slot, Consumer<InventoryClickEvent> action) {
        data.addEvent(slot, action);
        return this;
    }

    /**
     * Internal helper to convert Material + Consumer into a finished ItemStack.
     */
    private ItemStack buildItem(Material material, Consumer<ItemBuilder> settings) {
        ItemBuilder builder = new ItemBuilder(material);
        if (settings != null) {
            settings.accept(builder);
        }
        return builder.build();
    }

    public MenuBuilder set(ItemStack item, int... slots) {
        for (int slot : slots) {
            if (slot >= 0 && slot < inv.getSize()) {
                inv.setItem(slot, item);
            }
        }
        return this;
    }

    public MenuBuilder set(Material material, Consumer<ItemBuilder> settings, int... slots) {
        return set(buildItem(material, settings), slots);
    }

    public MenuBuilder background(ItemStack item) {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, item);
            }
        }
        return this;
    }

    public MenuBuilder background(Material material, Consumer<ItemBuilder> settings) {
        return background(buildItem(material, settings));
    }

    public MenuBuilder checkeredBackground(ItemStack item1, ItemStack item2) {
        for (int i = 0; i < inv.getSize(); i++) {
            int row = i / 9;
            int col = i % 9;
            // If row + col is even, use item1, else item2
            if ((row + col) % 2 == 0) {
                inv.setItem(i, item1);
            } else {
                inv.setItem(i, item2);
            }
        }
        return this;
    }

    public MenuBuilder checkeredBackground(Material mat1, Consumer<ItemBuilder> set1,
                                           Material mat2, Consumer<ItemBuilder> set2) {
        return checkeredBackground(buildItem(mat1, set1), buildItem(mat2, set2));
    }

    public MenuBuilder border(ItemStack item) {
        int size = inv.getSize();
        int rows = size / 9;

        for (int i = 0; i < size; i++) {
            int row = i / 9;
            int col = i % 9;

            // If it's the first/last row OR first/last column
            if (row == 0 || row == rows - 1 || col == 0 || col == 8) {
                inv.setItem(i, item);
            }
        }
        return this;
    }

    public MenuBuilder border(Material material, Consumer<ItemBuilder> settings) {
        return border(buildItem(material, settings));
    }

    public MenuBuilder checkeredBorder(ItemStack item1, ItemStack item2) {
        int size = inv.getSize();
        int rows = size / 9;

        for (int i = 0; i < size; i++) {
            int row = i / 9;
            int col = i % 9;

            // Check if the current slot is on the border
            if (row == 0 || row == rows - 1 || col == 0 || col == 8) {
                // Apply checkerboard math: (row + col) % 2
                if ((row + col) % 2 == 0) {
                    inv.setItem(i, item1);
                } else {
                    inv.setItem(i, item2);
                }
            }
        }
        return this;
    }

    public MenuBuilder checkeredBorder(Material mat1, Consumer<ItemBuilder> set1,
                                       Material mat2, Consumer<ItemBuilder> set2) {
        return checkeredBorder(buildItem(mat1, set1), buildItem(mat2, set2));
    }

    public MenuBuilder range(int start, int end, ItemStack item) {
        int first = Math.max(0, Math.min(start, end));
        int last = Math.min(inv.getSize() - 1, Math.max(start, end));

        for (int i = first; i <= last; i++) {
            inv.setItem(i, item);
        }
        return this;
    }

    public MenuBuilder range(int start, int end, Material material, Consumer<ItemBuilder> settings) {
        return range(start, end, buildItem(material, settings));
    }

}
