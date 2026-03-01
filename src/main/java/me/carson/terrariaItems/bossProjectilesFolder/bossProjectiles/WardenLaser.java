package me.carson.terrariaItems.bossProjectilesFolder.bossProjectiles;

import me.carson.terrariaItems.bossProjectilesFolder.BossProjectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class WardenLaser extends BossProjectile {

    public WardenLaser(Plugin plugin) {
        super(plugin, 0,  "warden_laser", "WardenLaser",2,0, DamageType.GENERIC);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
