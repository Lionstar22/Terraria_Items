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

public class JungleShirt extends Armor {

    public JungleShirt(Plugin plugin){
        super(plugin,"jungle_shirt.name","#FFC896", Material.IRON_CHESTPLATE,"jungle_shirt","jungle_armor",EquipmentSlot.CHEST,"JungleShirt","jungle_shirt.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new JungleShirt(plugin).createItem();
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
