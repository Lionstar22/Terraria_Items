package me.carson.terrariaItems.weaponsFolder;

import me.carson.terrariaItems.handlers.WorldDataHandler;
import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee.IceSickle;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class WeaponListeners implements Listener {

    private final Plugin plugin;
    private final NamespacedKey customItemKey;
    private final WorldDataHandler worldInstance=WorldDataHandler.getInstance();
    private static final Set<Biome> snowyBiomes = Set.of(Biome.SNOWY_TAIGA,Biome.JAGGED_PEAKS,Biome.FROZEN_PEAKS,Biome.GROVE,Biome.SNOWY_SLOPES,Biome.FROZEN_RIVER,Biome.SNOWY_PLAINS,Biome.ICE_SPIKES,Biome.SNOWY_BEACH);

    public WeaponListeners(Plugin plugin){
        this.plugin=plugin;
        customItemKey=new NamespacedKey(plugin, "custom_item_id");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Boolean hasItemInHand(ItemStack item, String id){
        if(item!=null&&item.hasItemMeta()){
            return Objects.equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(customItemKey, PersistentDataType.STRING), id);
        }
        return false;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        ItemStack item=player.getInventory().getItemInMainHand();
        if(hasItemInHand(item,"BladeOfGrass")){
            if(event.getEntity() instanceof LivingEntity livingEntity){
                if(Math.random()<0.25){
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,140,2,false,true,true));
                }
            }
        }
        if(hasItemInHand(item,"BreakerBlade")){
            if(event.getEntity() instanceof LivingEntity livingEntity){
                if(livingEntity.getHealth()>(livingEntity.getMaxHealth()*.9)){
                    event.setDamage(event.getDamage()*1.5);
                }
            }
        }
        if(hasItemInHand(item,"SlapHand")){
            event.getEntity().getWorld().playSound(event.getEntity().getLocation(), "terraria:slap_hand", 1.5F, 1.0F);
        }
        if(hasItemInHand(item,"TaintedBlade")){
            if(event.getEntity() instanceof LivingEntity target){
                target.addPotionEffect(new PotionEffect(PotionEffectType.POISON,100,0,false,true,true));
            }
        }
        if(hasItemInHand(item,"CausticEdge")){
            if(event.getEntity() instanceof LivingEntity target){
                target.addPotionEffect(new PotionEffect(PotionEffectType.POISON,100,1,false,true,true));
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,60,0,false,true,true));
            }
        }
        if(hasItemInHand(item,"Volcano")){
            player.getWorld().playSound(event.getEntity(), "terraria:volcano", 2.5F, 1.0F);
        }
    }

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Snowball snowball)) return;
        NamespacedKey key = new NamespacedKey(plugin, "custom_snowball");
        PersistentDataContainer pdc = snowball.getPersistentDataContainer();
        if (!pdc.has(key, PersistentDataType.INTEGER)) return;
        if (!(event.getHitEntity() instanceof LivingEntity target)) return;

        World world= event.getEntity().getWorld();
        world.playSound(event.getHitEntity().getLocation(), "terraria:snowball_impact", 1.0F, 1.0F);
        target.damage(8);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!event.isSneaking()) {
            player.removePotionEffect(PotionEffectType.SLOWNESS);
        }
        if(hasItemInHand(player.getInventory().getItemInMainHand(),"SniperRifle")){
            if (event.isSneaking()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 999999, 20, false, false, false));
            }
            else {
                player.removePotionEffect(PotionEffectType.SLOWNESS);
            }
        }
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Item && (event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSnowyDeath(EntityDeathEvent event){
        if(!worldInstance.getHardmode()){return;}
        LivingEntity entity = event.getEntity();
        if(!(entity instanceof Monster)){return;}
        if(snowyBiomes.contains(entity.getLocation().getBlock().getBiome())){
            if(Math.random()<0.01){
                event.getDrops().add(IceSickle.getItem(plugin));
            }
        }
    }
}
