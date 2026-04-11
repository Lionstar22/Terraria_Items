package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class DemoniteBar extends Material {

    public DemoniteBar(Plugin plugin) {
        super(plugin,"demonite_bar.name","#9696FF", org.bukkit.Material.BRICK,"demonite_bar","DemoniteBar","demonite_bar.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DemoniteBar(plugin).createItem();
    }
}
