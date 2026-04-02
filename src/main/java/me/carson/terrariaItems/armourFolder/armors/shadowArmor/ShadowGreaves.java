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
        super(plugin,"Shadow Greaves","#9696FF", Material.DIAMOND_BOOTS,"shadow_greaves","shadow_armor", EquipmentSlot.FEET,"ShadowGreaves",new ArrayList<>(List.of(ChatColor.GRAY+"5% increased critical strike chance",ChatColor.GRAY+"5% Increased movement speed",ChatColor.GRAY+"+3 Armor")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowGreaves(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addCritChance(player.getUniqueId(),0.05);
        player.setWalkSpeed(player.getWalkSpeed()+0.01f);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractCritChance(player.getUniqueId(),0.05);
        player.setWalkSpeed(Math.max(player.getWalkSpeed() - 0.01f, 0.2f));
    }
}
