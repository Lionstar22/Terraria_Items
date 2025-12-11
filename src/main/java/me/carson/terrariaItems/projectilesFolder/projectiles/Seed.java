package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class Seed extends Projectile {

    public Seed(Plugin plugin) {
        super(plugin, 0, "seed_projectile", "SeedProjectile",0, DamageType.ARROW);
    }

    @Override
    public void hitEffect(LivingEntity entity) {

    }

}
