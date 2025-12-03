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

public class WarriorEmblem extends Accessory implements Listener {

    public WarriorEmblem(Plugin plugin){
        super(plugin,"Warrior Emblem","#FF9696", Material.GOLD_INGOT,"warrior_emblem","WarriorEmblem",new ArrayList<>(List.of(ChatColor.GRAY+"20% increased melee damage",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player) {

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new WarriorEmblem(plugin).createItem();
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getType() != EntityType.WITHER_SKELETON){return;}

        if(Math.random()<0.05){
            ItemStack custom = WarriorEmblem.getItem(plugin);
            e.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onMeleeDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;

        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (WarriorEmblem.this.isThisItem(itemInv)) {
                if (WarriorEmblem.this.isActivated(itemInv)){
                    double boostedDamage = event.getDamage() * 1.2;
                    event.setDamage(boostedDamage);
                    break;
                }
            }
        }
    }
}
