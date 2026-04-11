package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FrostCore extends Material {

    public FrostCore(Plugin plugin) {
        super(plugin,"frost_core.name","#FF96FF", org.bukkit.Material.DIAMOND,"frost_core","FrostCore","frost_core.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new FrostCore(plugin).createItem();
    }
}
