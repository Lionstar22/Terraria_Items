package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Hellstone extends Material {

    public Hellstone(Plugin plugin) {
        super(plugin,"Hellstone","#96FF96", org.bukkit.Material.MAGMA_BLOCK,"hellstone","Hellstone", new ArrayList<>(List.of((ChatColor.GRAY+"The underworld is made of a material called hellstone."))));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Hellstone(plugin).createItem();
    }

}
