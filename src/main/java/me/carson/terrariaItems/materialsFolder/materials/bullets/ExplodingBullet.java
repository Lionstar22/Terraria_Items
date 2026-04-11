package me.carson.terrariaItems.materialsFolder.materials.bullets;


import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ExplodingBullet extends Material {

    public ExplodingBullet(Plugin plugin) {
        super(plugin,"exploding_bullet.name","#FFC896", org.bukkit.Material.GRAY_DYE,"exploding_bullet","ExplodingBullet", "exploding_bullet.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new ExplodingBullet(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(99);
        item.setItemMeta(meta);
        return item;
    }

}
