package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FallenStar extends Material {

    public FallenStar(Plugin plugin) {
        super(plugin,"fallen_star.name","#9696FF", org.bukkit.Material.GOLD_NUGGET,"fallen_star","FallenStar","fallen_star.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new FallenStar(plugin).createItem();
    }

}
