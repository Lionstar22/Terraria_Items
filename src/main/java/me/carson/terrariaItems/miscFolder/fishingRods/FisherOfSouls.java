package me.carson.terrariaItems.miscFolder.fishingRods;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class FisherOfSouls extends Basic {

    public FisherOfSouls(Plugin plugin) {
        super(plugin,"fisher_of_souls.name","#9696FF", Material.FISHING_ROD,"fisher_of_souls","FisherOfSouls","fisher_of_souls.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FisherOfSouls(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.LURE,1,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }
}
