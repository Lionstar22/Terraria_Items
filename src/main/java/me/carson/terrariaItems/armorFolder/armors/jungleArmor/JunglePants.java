package me.carson.terrariaItems.armorFolder.armors.jungleArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class JunglePants extends Armor {

    public JunglePants(Plugin plugin){
        super(plugin,"jungle_pants.name","#FFC896", Material.IRON_BOOTS,"jungle_pants","jungle_armor",EquipmentSlot.FEET,"JunglePants","jungle_pants.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new JunglePants(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addExtraMana(player.getUniqueId(),20);
        playerInstance.addBonusMagic(player.getUniqueId(),0.06);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),20);
        playerInstance.subtractBonusMagic(player.getUniqueId(),0.06);
    }
}
