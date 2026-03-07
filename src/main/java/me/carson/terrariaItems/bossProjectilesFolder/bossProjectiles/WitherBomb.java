package me.carson.terrariaItems.bossProjectilesFolder.bossProjectiles;

import me.carson.terrariaItems.bossProjectilesFolder.BossProjectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class WitherBomb extends BossProjectile {

    public WitherBomb(Plugin plugin) {
        super(plugin, 0,  "wither_bomb", "WitherBomb",0,0, DamageType.GENERIC);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.getWorld().createExplosion(entity.getLocation(), 3.0F, false, false);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().createExplosion(block.getLocation().add(0.5,1,0.5), 3.0F, false, false);
    }

}
