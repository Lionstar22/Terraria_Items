package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class RangerEmblem extends Accessory implements Listener {

    public RangerEmblem(Plugin plugin){
        super(plugin,"Ranger Emblem","#FF9696", Material.GOLD_INGOT,"ranger_emblem","RangerEmblem",new ArrayList<>(List.of(ChatColor.GRAY+"20% increased ranged damage",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player) {

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new RangerEmblem(plugin).createItem();
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getType() != EntityType.BLAZE){return;}

        if(Math.random()<0.05){
            ItemStack custom = RangerEmblem.getItem(plugin);
            e.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onMeleeDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return;

        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (RangerEmblem.this.isThisItem(itemInv)) {
                if (RangerEmblem.this.isActivated(itemInv)){
                    double boostedDamage = event.getDamage() * 1.2;
                    event.setDamage(boostedDamage);
                    break;
                }
            }
        }
    }

}
