package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FallenStar extends Material {

    public FallenStar(Plugin plugin) {
        super(plugin,"Fallen Star","#9696FF", org.bukkit.Material.GOLD_NUGGET,"fallen_star","FallenStar", new ArrayList<>(List.of((ChatColor.GRAY+"Falls from the heavens"))));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new FallenStar(plugin).createItem();
    }

}
