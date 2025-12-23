package me.jakepronger.spine.builders;

import lombok.Getter;
import lombok.experimental.Accessors;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

@Getter
@Accessors(fluent = true, chain = true)
public class ItemStackBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemStackBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemStackBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();
    }

    public ItemStackBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Generic meta editor
     * @param consumer
     * @return
     */
    public ItemStackBuilder meta(Consumer<ItemMeta> consumer) {
        consumer.accept(this.meta); // modifies cached meta
        return this;                // allows fluent chaining
    }

    public ItemStackBuilder name(String name) {
        return meta(meta -> {
            meta.displayName(Component.text(name)); // todo formatting
        });
    }

    public ItemStackBuilder lore(String... lore) {
        return meta(meta -> {
            // todo: formatting etc proper add
            //meta.lore(Arrays.stream(lore).toList());
        });
    }

    public ItemStackBuilder flags(ItemFlag... flags) {
        return meta(meta -> {
            meta.addItemFlags(flags);
        });
    }

    public ItemStackBuilder enchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        return meta(meta -> {
            meta.addEnchant(enchantment, level, ignoreLevelRestriction);
        });
    }

    public ItemStackBuilder glow() {
        return meta(meta -> {
            meta.setEnchantmentGlintOverride(true);
        });
    }

    public ItemStackBuilder glow(boolean value) {
        return meta(meta -> {
            if (value)
                meta.setEnchantmentGlintOverride(true);
            else
                meta.setEnchantmentGlintOverride(null);
        });
    }

    public ItemStack build() {
        return item;
    }

}
