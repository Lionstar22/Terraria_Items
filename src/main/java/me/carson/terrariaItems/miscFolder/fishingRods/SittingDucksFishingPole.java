package me.carson.terrariaItems.miscFolder.fishingRods;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class SittingDucksFishingPole extends Basic {

    public SittingDucksFishingPole(Plugin plugin) {
        super(plugin,"sitting_ducks_fishing_pole.name","#96FF96", Material.FISHING_ROD,"sitting_ducks_fishing_pole","SittingDucksFishingPole","sitting_ducks_fishing_pole.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new SittingDucksFishingPole(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.LURE,3,true);
        meta.addEnchant(Enchantment.LUCK_OF_THE_SEA,3,true);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }
}
