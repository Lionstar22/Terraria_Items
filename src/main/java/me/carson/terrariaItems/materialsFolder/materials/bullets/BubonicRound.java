package me.carson.terrariaItems.materialsFolder.materials.bullets;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class BubonicRound extends Material {

    public BubonicRound(Plugin plugin) {
        super(plugin,"Bubonic Round","#FFFF0A", org.bukkit.Material.GRAY_DYE,"bubonic_round","BubonicRound", new ArrayList<>(List.of(ChatColor.GRAY+"Spreads the plague on hit",ChatColor.GRAY+"5 Damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new BubonicRound(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
