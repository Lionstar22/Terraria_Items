package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;

import java.util.ArrayList;
import java.util.List;

public class RangerEmblem extends Accessory implements Listener {

    public RangerEmblem(Plugin plugin){
        super(plugin,"Ranger Emblem","#FF9696", Material.GOLD_NUGGET,"ranger_emblem","RangerEmblem",new ArrayList<>(List.of(ChatColor.GRAY+"20% increased ranged damage",ChatColor.GRAY+"Must be in accessory inventory")));
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
        if(event.getDamager() instanceof Projectile projectile) {
            ProjectileSource source = projectile.getShooter();
            if (!(source instanceof Player player)) {return;}
            if (hasItem(player)) {
                double boostedDamage = event.getDamage() * 1.2;
                event.setDamage(boostedDamage);
            }
        }
        else if(event.getDamager() instanceof Player player){
            DamageType type = event.getDamageSource().getDamageType();
            if(type==DamageType.ARROW){
                if (hasItem(player)){
                    double boostedDamage = event.getDamage() * 1.2;
                    event.setDamage(boostedDamage);
                }
            }
        }
    }

}
