package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class BulletProjectile extends Projectile {

    public BulletProjectile(Plugin plugin) {
        super(plugin, 2, "bullet_projectile", "BulletProjectile", DamageType.ARROW);
    }

    @Override
    public void hitEffect(LivingEntity entity) {

    }
}
