package me.carson.terrariaItems.miscFolder.BasicItems;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class BonePickaxe extends Basic {

    public BonePickaxe(Plugin plugin) {
        super(plugin,"bone_pickaxe.name","#9696FF", Material.IRON_PICKAXE,"bone_pickaxe","BonePickaxe","bone_pickaxe.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BonePickaxe(plugin).createItem();
    }
}
