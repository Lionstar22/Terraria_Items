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

public class ShadowHelmet extends Armor {

    public ShadowHelmet(Plugin plugin){
        super(plugin,"Shadow Helmet","#9696FF", Material.DIAMOND_HELMET,"shadow_helmet","shadow_armor", EquipmentSlot.HEAD,"ShadowHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Increased movement speed and acceleration")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowHelmet(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        player.setWalkSpeed(0.3f); // default is 0.2f
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        player.setWalkSpeed(0.2f); // default is 0.2f
    }
}
