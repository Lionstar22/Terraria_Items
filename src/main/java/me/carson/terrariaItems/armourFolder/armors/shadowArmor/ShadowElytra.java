package me.carson.terrariaItems.armourFolder.armors.shadowArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ShadowElytra extends Armor {

    public ShadowElytra(Plugin plugin){
        super(plugin,"Shadow Elytra","#9696FF", Material.ELYTRA,"shadow_elytra","shadow_armor", EquipmentSlot.CHEST,"ShadowElytra",new ArrayList<>(List.of(ChatColor.GRAY+"")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowElytra(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {

    }
}
