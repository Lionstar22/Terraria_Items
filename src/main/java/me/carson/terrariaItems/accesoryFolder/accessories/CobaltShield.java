package me.carson.terrariaItems.accesoryFolder.accessories;


import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityKnockbackByEntityEvent;
import org.bukkit.event.entity.EntityKnockbackEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CobaltShield extends Accessory implements Listener {

    public CobaltShield(Plugin plugin){
        super(plugin,"Cobalt Shield","#96FF96", Material.LAPIS_LAZULI,"cobalt_shield","CobaltShield",
                new ArrayList<>(List.of(
                        ChatColor.GRAY+"Grants immunity to knockback",
                        ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @EventHandler
    public void onKnockback(EntityKnockbackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if(hasItem(player)){
            event.setCancelled(true);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CobaltShield(plugin).createItem();
    }
}
