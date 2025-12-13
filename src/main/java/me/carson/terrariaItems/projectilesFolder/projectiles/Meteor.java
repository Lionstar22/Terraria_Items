package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class Meteor extends Projectile implements Listener {

    public Meteor(Plugin plugin) {
        super(plugin, 0,  "meteor", "Meteor",0,0, DamageType.PLAYER_ATTACK);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:meteor_impact", 1.5F, 1.0F);
        entity.getWorld().createExplosion(entity.getLocation(), 2.0F, false, false);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:meteor_impact", 1.5F, 1.0F);
        block.getWorld().createExplosion(block.getLocation().add(0,1,0), 2.0F, false, false);
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Item && (event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
            event.setCancelled(true);
        }
    }

}
