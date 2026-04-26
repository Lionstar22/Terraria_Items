package me.carson.terrariaItems.miscFolder.fishingRods;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class GoldenFishingRod extends Basic {

    public GoldenFishingRod(Plugin plugin) {
        super(plugin,"golden_fishing_rod.name","#FFC896", Material.FISHING_ROD,"golden_fishing_rod","GoldenFishingRod","golden_fishing_rod.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new GoldenFishingRod(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.LURE,4,true);
        meta.addEnchant(Enchantment.LUCK_OF_THE_SEA,3,true);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }
}
