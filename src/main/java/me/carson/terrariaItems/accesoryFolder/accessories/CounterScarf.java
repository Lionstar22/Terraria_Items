package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CounterScarf extends Accessory implements Listener {

    public CounterScarf(Plugin plugin){
        super(plugin,"counter_scarf.name","#FFC896", Material.RED_WOOL,"counter_scarf","CounterScarf","counter_scarf.lore");
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addBonusMelee(player.getUniqueId(),0.1);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.1);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        if(!player.hasCooldown(Material.RED_WOOL)){
            if (COUNTERSCARF_CAUSES.contains(event.getCause())){
                player.setCooldown(Material.RED_WOOL,600);
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }


    public static ItemStack getItem(Plugin plugin) {
        return new CounterScarf(plugin).createItem();
    }

}
