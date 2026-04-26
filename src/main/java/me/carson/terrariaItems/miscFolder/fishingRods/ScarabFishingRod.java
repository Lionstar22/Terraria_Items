package me.carson.terrariaItems.miscFolder.fishingRods;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ScarabFishingRod extends Basic {

    public ScarabFishingRod(Plugin plugin) {
        super(plugin,"scarab_fishing_rod.name","#9696FF", Material.FISHING_ROD,"scarab_fishing_rod","ScarabFishingRod","scarab_fishing_rod.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ScarabFishingRod(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.LURE,2,false);
        meta.addEnchant(Enchantment.LUCK_OF_THE_SEA,2,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }
}
