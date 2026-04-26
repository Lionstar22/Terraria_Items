package me.carson.terrariaItems.miscFolder.fishingRods;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class FiberglassFishingPole extends Basic {

    public FiberglassFishingPole(Plugin plugin) {
        super(plugin,"fiberglass_fishing_pole.name","#96FF96", Material.FISHING_ROD,"fiberglass_fishing_pole","FiberglassFishingPole","fiberglass_fishing_pole.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FiberglassFishingPole(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.LURE,2,false);
        meta.addEnchant(Enchantment.LUCK_OF_THE_SEA,2,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }
}
