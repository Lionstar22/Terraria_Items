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
        super(plugin,"Avenger Emblem","#FF96FF", Material.GOLD_INGOT,"avenger_emblem","AvengerEmblem",new ArrayList<>(List.of(ChatColor.GRAY+"15% increased damage",ChatColor.GRAY+"Must be in accessory inventory")));
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
