package me.carson.terrariaItems.materialsFolder.materials.bullets;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MusketBall extends Material {

    public MusketBall(Plugin plugin) {
        super(plugin,"musket_ball.name","#FFFFFF", org.bukkit.Material.GRAY_DYE,"musket_ball","MusketBall","musket_ball.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new MusketBall(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }
}
