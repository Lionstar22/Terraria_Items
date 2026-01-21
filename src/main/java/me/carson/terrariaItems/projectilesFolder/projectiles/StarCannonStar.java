package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class StarCannonStar extends Projectile {

    public StarCannonStar(Plugin plugin) {
        super(plugin, 0,  "fallen_star", "StarCannonStar",99,0, DamageType.ARROW);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:impact_1", 2.0F, 1.0F);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:impact_1", 2.0F, 1.0F);
    }

}
