package me.carson.terrariaItems.armourFolder.armors.possessedArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class PossessedHelmet extends Armor {

    public PossessedHelmet(Plugin plugin){
        super(plugin,"Possessed Helmet","#FFFFFF", Material.DIAMOND_HELMET,"shadow_helmet","possessed_armor", EquipmentSlot.HEAD,"PossessedHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"How did you even get this?")));
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new PossessedHelmet(plugin).createItem();
    }

}
