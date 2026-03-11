package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CounterScarf extends Accessory implements Listener {

    public CounterScarf(Plugin plugin){
        super(plugin,"Counter Scarf","#FFC896", Material.RED_WOOL,"counter_scarf","CounterScarf",new ArrayList<>(List.of(ChatColor.GRAY+"10% Increased Melee Damage",ChatColor.GRAY+"Grants a Dodge every 30 Seconds",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addBonusMelee(player.getUniqueId(),0.1);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.1);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CounterScarf(plugin).createItem();
    }

}
