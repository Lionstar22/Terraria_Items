package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class ThunderZapperBolt extends Projectile {

    public ThunderZapperBolt(Plugin plugin) {
        super(plugin, 0,  "thunder_zapper_bolt", "ThunderZapperBolt",0,0, DamageType.LIGHTNING_BOLT);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
