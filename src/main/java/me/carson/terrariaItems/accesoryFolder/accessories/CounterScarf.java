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

    private static final Set<EntityDamageEvent.DamageCause> COUNTERABLE_CAUSES = Set.of(
            EntityDamageEvent.DamageCause.ENTITY_ATTACK,
            EntityDamageEvent.DamageCause.PROJECTILE,
            EntityDamageEvent.DamageCause.ENTITY_EXPLOSION,
            EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK,
            EntityDamageEvent.DamageCause.LIGHTNING,
            EntityDamageEvent.DamageCause.MAGIC,
            EntityDamageEvent.DamageCause.SONIC_BOOM,
            EntityDamageEvent.DamageCause.DRAGON_BREATH
    );

    public CounterScarf(Plugin plugin){
        super(plugin,"Counter Scarf","#FFC896", Material.RED_WOOL,"counter_scarf","CounterScarf",new ArrayList<>(List.of(ChatColor.GRAY+"10% Increased Damage",ChatColor.GRAY+"Grants a Dodge every 30 Seconds",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CounterScarf(plugin).createItem();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (hasItem(player)) {
            if(!player.hasCooldown(Material.RED_WOOL)){
                if (COUNTERABLE_CAUSES.contains(event.getCause())){
                    player.setCooldown(Material.RED_WOOL,600);
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (CounterScarf.this.isThisItem(itemInv)) {
                if (CounterScarf.this.isActivated(itemInv)){
                    double baseDamage = event.getDamage();
                    double boostedDamage = baseDamage * 1.1;
                    event.setDamage(boostedDamage);
                    break;
                }
            }
        }
    }
}
