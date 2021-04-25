package net.craftions.skywars.util;

import org.bukkit.enchantments.Enchantment;

public class Ench {
    private final int lvl;
    private final Enchantment ench;

    public Ench(Enchantment ench, int lvl) {
        this.ench = ench;
        this.lvl = lvl;
    }

    public Enchantment getEnch() {
        return ench;
    }

    public int getLvl() {
        return lvl;
    }
}
