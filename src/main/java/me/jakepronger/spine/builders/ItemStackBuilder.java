package me.jakepronger.spine.builders;

import lombok.Getter;
import lombok.experimental.Accessors;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
@Accessors(fluent = true, chain = true)
public class ItemStackBuilder {

    private final ItemStack item;

    public ItemStackBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemStackBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemStackBuilder name(String name) {
        // todo formatting
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));
        item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder lore(String... lore) {
        ItemMeta meta = item.getItemMeta();
        //meta.lore(Arrays.stream(lore).toList()); // todo: formatting etc proper add
        item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder flags(ItemFlag... flags) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder enchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, ignoreLevelRestriction);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder glow() {
        ItemMeta meta = item.getItemMeta();
        meta.setEnchantmentGlintOverride(true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder glow(boolean value) {
        ItemMeta meta = item.getItemMeta();
        if (value)
            meta.setEnchantmentGlintOverride(true);
        else
            meta.setEnchantmentGlintOverride(null);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return item;
    }

}
