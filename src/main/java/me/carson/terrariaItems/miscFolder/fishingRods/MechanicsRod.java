package me.carson.terrariaItems.miscFolder.fishingRods;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class MechanicsRod extends Basic {

    public MechanicsRod(Plugin plugin) {
        super(plugin,"mechanics_rod.name","#96FF96", Material.FISHING_ROD,"mechanics_rod","MechanicsRod","mechanics_rod.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new MechanicsRod(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.LURE,2,true);
        meta.addEnchant(Enchantment.LUCK_OF_THE_SEA,2,true);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }
}
