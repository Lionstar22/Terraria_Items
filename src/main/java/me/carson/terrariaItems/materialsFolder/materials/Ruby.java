package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Ruby extends Material {

    public Ruby(Plugin plugin) {
        super(plugin,"ruby.name","#FFFFFF", org.bukkit.Material.DIAMOND,"ruby","Ruby","ruby.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Ruby(plugin).createItem();
    }
}
