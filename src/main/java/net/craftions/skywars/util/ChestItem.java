package net.craftions.skywars.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class ChestItem {
    private final Material material;
    private final int amount;
    private final Enchantment[] enchs;

    public ChestItem(Material material, int amount, Enchantment... enchs) {
        this.material = material;
        this.amount = amount;
        this.enchs = enchs;
    }

    public Material getMaterial() {
        return material;
    }

    public Enchantment[] getEnchs() {
        return enchs;
    }

    public int getAmount() {
        return amount;
    }
}
