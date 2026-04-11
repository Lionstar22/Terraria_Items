package me.carson.terrariaItems.armorFolder.armors.shadowArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ShadowLeggings extends Armor {

    public ShadowLeggings(Plugin plugin){
        super(plugin,"shadow_leggings.name","#9696FF", Material.DIAMOND_LEGGINGS,"shadow_leggings","shadow_armor", EquipmentSlot.LEGS,"ShadowLeggings","shadow_leggings.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowLeggings(plugin).createItem();
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
