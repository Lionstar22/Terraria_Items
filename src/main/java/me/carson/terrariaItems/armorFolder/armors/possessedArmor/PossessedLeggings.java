package me.carson.terrariaItems.armorFolder.armors.possessedArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class PossessedLeggings extends Armor {

    public PossessedLeggings(Plugin plugin){
        super(plugin,"unobtainable.name","#FFFFFF", Material.IRON_LEGGINGS,"shadow_leggings","possessed_armor", EquipmentSlot.LEGS,"PossessedLeggings","unobtainable.lore");
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new PossessedLeggings(plugin).createItem();
    }

}
