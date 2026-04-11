package me.carson.terrariaItems.materialsFolder.materials.bullets;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;


public class BubonicRound extends Material {

    public BubonicRound(Plugin plugin) {
        super(plugin,"bubonic_round.name","#FFFF0A", org.bukkit.Material.GRAY_DYE,"bubonic_round","BubonicRound", "bubonic_round.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new BubonicRound(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
