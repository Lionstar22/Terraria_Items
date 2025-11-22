package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HellstoneBar extends Material {

    public HellstoneBar(Plugin plugin) {
        super(plugin,"HellstoneBar","#96FF96", org.bukkit.Material.NETHERITE_INGOT,"hellstone_bar","HellstoneBar", new ArrayList<>(List.of((ChatColor.GRAY+"Hot to the touch"))));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new HellstoneBar(plugin).createItem();
    }
}
