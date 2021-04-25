package net.craftions.skywars.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder addEnchant(Enchantment ench, int lvl) {
        this.item.addEnchantment(ench, lvl);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench) {
        return addEnchant(ench, 1);
    }

    public ItemStack build() {
        return item;
    }
}
