package me.carson.terrariaItems.armourFolder.armors.jungleArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class JungleLeggings extends Armor {

    public JungleLeggings(Plugin plugin){
        super(plugin,"Jungle Leggings","#FFC896", Material.IRON_LEGGINGS,"jungle_leggings","jungle_armor",EquipmentSlot.LEGS,"JungleLeggings",new ArrayList<>(List.of(ChatColor.GRAY+"Increases maximum mana by 20",ChatColor.GRAY+"6% increased magic damage",ChatColor.GRAY+"+5 Armor")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new JungleLeggings(plugin).createItem();
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
