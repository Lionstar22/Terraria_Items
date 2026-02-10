package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LuckyHorseshoe extends Accessory implements Listener  {

    public LuckyHorseshoe(Plugin plugin){
        super(plugin,"Lucky Horseshoe","#9696FF",Material.GOLD_BLOCK,"lucky_horseshoe","LuckyHorseshoe",
                new ArrayList<>(List.of(
                        ChatColor.GRAY+"Negates fall damage",
                        ChatColor.GRAY+"'Said to bring good fortune and keep evil spirits at bay'",
                        ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if(((event.getCause() == EntityDamageEvent.DamageCause.FALL)||(event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL))){
            if(hasItem(player)){
                event.setCancelled(true);
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new LuckyHorseshoe(plugin).createItem();
    }

}
