package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class WarriorEmblem extends Accessory {

    public WarriorEmblem(Plugin plugin){
        super(plugin,"warrior_emblem.name","#FF9696", Material.GOLD_NUGGET,"warrior_emblem","WarriorEmblem","warrior_emblem.lore");
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addBonusMelee(player.getUniqueId(),0.2);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.2);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new WarriorEmblem(plugin).createItem();
    }

}
