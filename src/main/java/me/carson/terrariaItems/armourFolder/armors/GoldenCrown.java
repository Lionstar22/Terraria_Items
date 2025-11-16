package me.carson.terrariaItems.armourFolder.armors;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class GoldenCrown extends Armor {

    public GoldenCrown(Plugin plugin){
        super(plugin,"Golden Crown","#FFFFFF", Material.DIAMOND_CHESTPLATE,"iron_gem","GoldenCrown", EquipmentSlot.HEAD,"GoldenCrown",new ArrayList<>(List.of(ChatColor.GRAY+"",ChatColor.GRAY+"")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new GoldenCrown(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {

    }
}
