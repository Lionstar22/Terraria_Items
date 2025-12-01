package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HallowedBar extends Material {

    public HallowedBar(Plugin plugin) {
        super(plugin,"Hallowed Bar","#FF9696", org.bukkit.Material.GOLD_INGOT,"hallowed_bar","HallowedBar", new ArrayList<>(List.of((ChatColor.GRAY+"A holy bar forged from souls"))));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new HallowedBar(plugin).createItem();
    }
}
