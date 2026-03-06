package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class PulseBolt extends Projectile {

    public PulseBolt(Plugin plugin) {
        super(plugin, 0,  "pulse_bolt", "PulseBolt",5,5, DamageType.ARROW);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {
    }

    @Override
    public void hitBlockEffect(Block block) {
    }

}
