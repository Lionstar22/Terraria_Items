package me.carson.terrariaItems.projectilesFolder.projectiles.ornaments;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class RedOrnament extends Projectile {

    public RedOrnament(Plugin plugin) {
        super(plugin, 0,  "red_ornament", "RedOrnament",0, DamageType.PLAYER_ATTACK);
    }

    @Override
    public void hitEffect(LivingEntity entity) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:frost_bolt_impact", 1.0F, 1.0F);
    }

}
