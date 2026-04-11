package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class IllegalGunParts extends Material {

    public IllegalGunParts(Plugin plugin) {
        super(plugin,"illegal_gun_parts.name","#FFFFFF", org.bukkit.Material.BRICK,"illegal_gun_parts","IllegalGunParts","illegal_gun_parts.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new IllegalGunParts(plugin).createItem();
    }

}
