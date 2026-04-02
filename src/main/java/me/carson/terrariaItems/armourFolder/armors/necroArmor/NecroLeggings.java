package me.carson.terrariaItems.armourFolder.armors.necroArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class NecroLeggings extends Armor {

    public NecroLeggings(Plugin plugin){
        super(plugin,"Necro Leggings","#96FF96", Material.DIAMOND_LEGGINGS,"necro_leggings","necro_armor",EquipmentSlot.LEGS,"NecroLeggings",new ArrayList<>(List.of(ChatColor.GRAY+"5% increased ranged damage",ChatColor.GRAY+"5% increased critical strike chance",ChatColor.GRAY+"+6 Armor")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new NecroLeggings(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusRanged(player.getUniqueId(),0.05);
        playerInstance.addCritChance(player.getUniqueId(),0.05);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusRanged(player.getUniqueId(),0.05);
        playerInstance.subtractCritChance(player.getUniqueId(),0.05);
    }
}
