package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DiamondBolt extends Projectile {

    public DiamondBolt(Plugin plugin) {
        super(plugin, 0,  "diamond_bolt", "DiamondBolt",1,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(204, 204, 204), 1f),0);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {
        entity.getWorld().createExplosion(entity.getLocation().add(0,0.5,0), 0.5F, false, false);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().createExplosion(block.getLocation().add(0.5,1.1f,0.5), 0.5F, false, false);
    }
}
