package me.carson.terrariaItems.materialsFolder.materials.bullets;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class EmptyBullet extends Material {

    public EmptyBullet(Plugin plugin) {
        super(plugin,"empty_bullet.name","#FFFFFF", org.bukkit.Material.GOLD_NUGGET,"empty_bullet","EmptyBullet", "empty_bullet.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new EmptyBullet(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
