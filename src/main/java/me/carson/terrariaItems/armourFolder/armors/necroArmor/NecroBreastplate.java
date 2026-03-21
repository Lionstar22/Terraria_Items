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

public class NecroBreastplate extends Armor {

    public NecroBreastplate(Plugin plugin){
        super(plugin,"Necro Breastplate","#96FF96", Material.DIAMOND_CHESTPLATE,"necro_breastplate","necro_armor",EquipmentSlot.CHEST,"NecroBreastplate",new ArrayList<>(List.of(ChatColor.GRAY+"5% increased ranged damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new NecroBreastplate(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusRanged(player.getUniqueId(),0.05);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.addBonusRanged(player.getUniqueId(),0.05);
    }
}
