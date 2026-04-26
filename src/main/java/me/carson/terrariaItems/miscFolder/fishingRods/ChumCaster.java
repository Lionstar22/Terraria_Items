package me.carson.terrariaItems.miscFolder.fishingRods;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ChumCaster extends Basic {

    public ChumCaster(Plugin plugin) {
        super(plugin,"chum_caster.name","#96FF96", Material.FISHING_ROD,"chum_caster","ChumCaster","chum_caster.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ChumCaster(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.LURE,2,false);
        meta.addEnchant(Enchantment.LUCK_OF_THE_SEA,1,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }
}
