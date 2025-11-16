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
        super(plugin,"Shadow Scalemail","#9696FF", Material.DIAMOND_CHESTPLATE,"shadow_scalemail","shadow_armor", EquipmentSlot.CHEST,"ShadowScalemail",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Increased movement speed and acceleration")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowScalemail(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {

    }
}
