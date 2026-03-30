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

public class ShadowScalemail extends Armor {

    public ShadowScalemail(Plugin plugin){
        super(plugin,"Shadow Scalemail","#9696FF", Material.DIAMOND_CHESTPLATE,"shadow_scalemail","shadow_armor", EquipmentSlot.CHEST,"ShadowScalemail",new ArrayList<>(List.of(ChatColor.GRAY+"5% increased critical strike chance",ChatColor.GRAY+"5% Increased movement speed")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowScalemail(plugin).createItem();
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
