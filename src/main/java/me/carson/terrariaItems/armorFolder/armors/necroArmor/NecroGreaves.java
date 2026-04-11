package me.carson.terrariaItems.armorFolder.armors.necroArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class NecroGreaves extends Armor {

    public NecroGreaves(Plugin plugin){
        super(plugin,"necro_greaves.name","#96FF96", Material.DIAMOND_BOOTS,"necro_greaves","necro_armor",EquipmentSlot.FEET,"NecroGreaves","necro_greaves.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new NecroGreaves(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusRanged(player.getUniqueId(),0.05);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusRanged(player.getUniqueId(),0.05);
    }
}
