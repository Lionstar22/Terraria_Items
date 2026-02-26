package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class OnyxCrystal extends Projectile {

    public OnyxCrystal(Plugin plugin) {
        super(plugin, 0, "onyx_crystal", "OnyxCrystal",0,0, DamageType.ARROW);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.getWorld().createExplosion(entity.getLocation(), 1.0F, false, false);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().createExplosion(block.getLocation().add(0,1,0), 1.0F, false, false);
    }
}
