package net.craftions.skywars;

import net.craftions.skywars.util.ChestItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public enum ChestGenerator {
    NORMAL(new ChestItem(Material.STONE_SWORD, 1), new ChestItem(Material.STONE, 64),
            new ChestItem(Material.OAK_PLANKS, 32), new ChestItem(Material.IRON_SWORD, 1),
            new ChestItem(Material.CHAINMAIL_LEGGINGS, 1),
            new ChestItem(Material.WOODEN_SWORD, 1, Enchantment.DAMAGE_ALL),
            new ChestItem(Material.IRON_CHESTPLATE, 1, Enchantment.PROTECTION_ENVIRONMENTAL),
            new ChestItem(Material.LEATHER_BOOTS, 1),
            new ChestItem(Material.EXPERIENCE_BOTTLE, 48),
            new ChestItem(Material.GOLDEN_APPLE, 5),
            new ChestItem(Material.COOKED_BEEF, 16),
            new ChestItem(Material.LAVA_BUCKET, 1),
            new ChestItem(Material.WATER_BUCKET, 1),
            new ChestItem(Material.COBWEB, 16),
            new ChestItem(Material.DIAMOND_HELMET, 1),
            new ChestItem(Material.CHAINMAIL_HELMET, 1, Enchantment.PROTECTION_FIRE),
            new ChestItem(Material.IRON_LEGGINGS, 1),
            new ChestItem(Material.TNT, 38),
            new ChestItem(Material.DIAMOND_CHESTPLATE, 1, Enchantment.PROTECTION_ENVIRONMENTAL),
            new ChestItem(Material.DIAMOND_BOOTS, 1),
            new ChestItem(Material.DIAMOND_HELMET, 1),
            new ChestItem(Material.DIAMOND_SWORD, 1),
            new ChestItem(Material.IRON_AXE, 1)),
    HARD(new ChestItem(Material.STONE_SWORD, 1), new ChestItem(Material.STONE, 64),
            new ChestItem(Material.OAK_PLANKS, 32), new ChestItem(Material.IRON_SWORD, 1),
            new ChestItem(Material.CHAINMAIL_LEGGINGS, 1),
            new ChestItem(Material.WOODEN_SWORD, 1, Enchantment.DAMAGE_ALL),
            new ChestItem(Material.IRON_CHESTPLATE, 1, Enchantment.PROTECTION_ENVIRONMENTAL),
            new ChestItem(Material.LEATHER_BOOTS, 1),
            new ChestItem(Material.EXPERIENCE_BOTTLE, 48),
            new ChestItem(Material.GOLDEN_APPLE, 5),
            new ChestItem(Material.COOKED_BEEF, 16),
            new ChestItem(Material.LAVA_BUCKET, 1),
            new ChestItem(Material.WATER_BUCKET, 1),
            new ChestItem(Material.COBWEB, 16),
            new ChestItem(Material.DIAMOND_HELMET, 1),
            new ChestItem(Material.CHAINMAIL_HELMET, 1, Enchantment.PROTECTION_FIRE),
            new ChestItem(Material.IRON_LEGGINGS, 1),
            new ChestItem(Material.TNT, 38),
            new ChestItem(Material.DIAMOND_CHESTPLATE, 1),
            new ChestItem(Material.DIAMOND_CHESTPLATE, 1, Enchantment.PROTECTION_ENVIRONMENTAL),
            new ChestItem(Material.DIAMOND_BOOTS, 1),
            new ChestItem(Material.DIAMOND_HELMET, 1),
            new ChestItem(Material.DIAMOND_SWORD, 1),
            new ChestItem(Material.IRON_AXE, 1),
            new ChestItem(Material.NETHERITE_SWORD, 1),
            new ChestItem(Material.NETHERITE_BOOTS, 1),
            new ChestItem(Material.ENCHANTED_GOLDEN_APPLE, 1)),
    EASY(new ChestItem(Material.STONE_SWORD, 1), new ChestItem(Material.STONE, 64),
            new ChestItem(Material.OAK_PLANKS, 32), new ChestItem(Material.IRON_SWORD, 1),
            new ChestItem(Material.CHAINMAIL_LEGGINGS, 1),
            new ChestItem(Material.WOODEN_SWORD, 1, Enchantment.DAMAGE_ALL),
            new ChestItem(Material.IRON_CHESTPLATE, 1, Enchantment.PROTECTION_ENVIRONMENTAL),
            new ChestItem(Material.LEATHER_BOOTS, 1),
            new ChestItem(Material.EXPERIENCE_BOTTLE, 48),
            new ChestItem(Material.GOLDEN_APPLE, 5),
            new ChestItem(Material.COOKED_BEEF, 16),
            new ChestItem(Material.LAVA_BUCKET, 1),
            new ChestItem(Material.WATER_BUCKET, 1),
            new ChestItem(Material.COBWEB, 16),
            new ChestItem(Material.DIAMOND_HELMET, 1),
            new ChestItem(Material.CHAINMAIL_HELMET, 1, Enchantment.PROTECTION_FIRE),
            new ChestItem(Material.IRON_LEGGINGS, 1),
            new ChestItem(Material.TNT, 38));

    private final ChestItem[] itemStacks;

    ChestGenerator(ChestItem... itemStacks) {
        this.itemStacks = itemStacks;
    }

    public ChestItem[] getItemStacks() {
        return itemStacks;
    }
}
