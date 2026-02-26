package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class CrystalStormProjectile extends Projectile {

    public CrystalStormProjectile(Plugin plugin) {
        super(plugin, 0,  "crystal_storm_projectile", "CrystalStormProjectile",0,99, DamageType.MAGIC);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }
}
