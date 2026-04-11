package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class HellstoneBar extends Material {

    public HellstoneBar(Plugin plugin) {
        super(plugin,"hellstone_bar.name","#96FF96", org.bukkit.Material.NETHER_BRICK,"hellstone_bar","HellstoneBar","hellstone_bar.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new HellstoneBar(plugin).createItem();
    }
}
