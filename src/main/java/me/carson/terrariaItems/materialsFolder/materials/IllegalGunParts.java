package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class IllegalGunParts extends Material {

    public IllegalGunParts(Plugin plugin) {
        super(plugin,"Illegal Gun Parts","#FFFFFF", org.bukkit.Material.BRICK,"illegal_gun_parts","IllegalGunParts", new ArrayList<>(List.of(ChatColor.GRAY+"Banned in most places")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new IllegalGunParts(plugin).createItem();
    }

}
