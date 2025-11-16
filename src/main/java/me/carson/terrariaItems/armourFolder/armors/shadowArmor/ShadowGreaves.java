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

public class ShadowGreaves extends Armor {

    public ShadowGreaves(Plugin plugin){
        super(plugin,"Shadow Greaves","#9696FF", Material.DIAMOND_BOOTS,"shadow_greaves","shadow_armor", EquipmentSlot.FEET,"ShadowGreaves",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Increased movement speed and acceleration")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowGreaves(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {

    }
}
