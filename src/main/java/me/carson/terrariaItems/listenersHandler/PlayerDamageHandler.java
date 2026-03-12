package me.carson.terrariaItems.listenersHandler;

import org.bukkit.Bukkit;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;

public class PlayerDamageHandler implements Listener {

    private final Plugin plugin;
    private final PlayerDataHandler playerInstance=PlayerDataHandler.getInstance();

    public PlayerDamageHandler(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        double extraDamage=0;
        double originalDamage=event.getDamage();
        DamageType type=event.getDamageSource().getDamageType();
        extraDamage+=originalDamage *playerInstance.getBonusDamage(player.getUniqueId());

        if(type==DamageType.PLAYER_ATTACK) {
            extraDamage+=originalDamage *playerInstance.getBonusMelee(player.getUniqueId());
        }
        if(type==DamageType.LIGHTNING_BOLT){
            extraDamage+=originalDamage *playerInstance.getBonusMagic(player.getUniqueId());
        }
        if(event.getDamager() instanceof Projectile projectile) {
            ProjectileSource source = projectile.getShooter();
            if (!(source ==player)) {return;}
            extraDamage+=originalDamage *playerInstance.getBonusRanged(player.getUniqueId());
        }
        if(type==DamageType.ARROW){
            extraDamage+=originalDamage *playerInstance.getBonusRanged(player.getUniqueId());
        }
        event.setDamage(originalDamage+extraDamage);
        //player.sendMessage("Damage: "+(originalDamage+extraDamage));
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        event.setDamage(event.getDamage()-(event.getDamage()*playerInstance.getDamageReduction(player.getUniqueId())));
    }

}
