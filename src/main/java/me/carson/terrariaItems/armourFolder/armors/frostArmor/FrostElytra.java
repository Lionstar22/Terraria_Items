package me.carson.terrariaItems.armourFolder.armors.frostArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FrostElytra extends Armor {

    public FrostElytra(Plugin plugin){
        super(plugin,"Frost Elytra","#FF96FF", Material.ELYTRA,"frost_elytra_sprite","frost_elytra_armor", EquipmentSlot.CHEST,"FrostElytra",new ArrayList<>(List.of(ChatColor.GRAY+"")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new FrostElytra(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {

    }

}
