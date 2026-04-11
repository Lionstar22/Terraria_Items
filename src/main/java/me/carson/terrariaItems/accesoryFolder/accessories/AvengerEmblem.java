package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class AvengerEmblem extends Accessory{

    public AvengerEmblem(Plugin plugin){
        super(plugin,"avenger_emblem.name","#FF96FF", Material.GOLD_INGOT,"avenger_emblem","AvengerEmblem","avenger_emblem.lore");
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addBonusDamage(player.getUniqueId(),0.15);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractBonusDamage(player.getUniqueId(),0.15);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new AvengerEmblem(plugin).createItem();
    }

}
