package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class Meteor extends Projectile {

    public Meteor(Plugin plugin) {
        super(plugin, 0,  "meteor", "Meteor",0, DamageType.PLAYER_ATTACK);
    }

    @Override
    public void hitEffect(LivingEntity entity) {

    }

}
