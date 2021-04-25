package net.craftions.skywars.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class ChestItem {
    private final Material material;
    private final int amount;
    private final Ench[] enchs;

    public ChestItem(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        this.enchs = new Ench[0];
    }

    public ChestItem(Material material, int amount, Enchantment... enchs) {
        this.material = material;
        this.amount = amount;
        Ench[] enchs1 = new Ench[enchs.length];
        for (int i = 0; i < enchs.length; i++) {
            enchs1[i] = new Ench(enchs[i], 1);
        }
        this.enchs = enchs1;
    }

    public ChestItem(Material material, int amount, Ench... enchs) {
        this.material = material;
        this.amount = amount;
        this.enchs = enchs;
    }

    public Material getMaterial() {
        return material;
    }

    public Ench[] getEnchs() {
        return enchs;
    }

    public int getAmount() {
        return amount;
    }
}
