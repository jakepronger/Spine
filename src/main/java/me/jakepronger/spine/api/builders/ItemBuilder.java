package me.jakepronger.spine.api.builders;

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
public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder meta(Consumer<ItemMeta> consumer) {
        consumer.accept(this.meta); // modifies cached meta
        return this;                // allows fluent chaining
    }

    public ItemBuilder name(String name) {
        return meta(meta -> {
            meta.displayName(Component.text(name)); // todo formatting
        });
    }

    public ItemBuilder lore(String... lore) {
        return meta(meta -> {
            // todo: formatting etc proper add
            //meta.lore(Arrays.stream(lore).toList());
        });
    }

    public ItemBuilder flags(ItemFlag... flags) {
        return meta(meta -> meta.addItemFlags(flags));
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        return meta(meta -> meta.addEnchant(enchantment, level, true));
    }

    public ItemBuilder glow() {
        return meta(meta -> meta.setEnchantmentGlintOverride(true));
    }

    public ItemBuilder glow(boolean value) {
        return meta(meta -> {
            if (value)
                meta.setEnchantmentGlintOverride(true);
            else
                meta.setEnchantmentGlintOverride(null);
        });
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

}
