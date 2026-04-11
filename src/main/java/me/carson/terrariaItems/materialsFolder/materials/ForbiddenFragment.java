package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ForbiddenFragment extends Material {

    public ForbiddenFragment(Plugin plugin) {
        super(plugin,"forbidden_fragment.name","#FF96FF", org.bukkit.Material.DIAMOND,"forbidden_fragment","ForbiddenFragment","forbidden_fragment.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ForbiddenFragment(plugin).createItem();
    }
}
