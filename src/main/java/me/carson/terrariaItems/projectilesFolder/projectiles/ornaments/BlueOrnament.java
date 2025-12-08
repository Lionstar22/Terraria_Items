package me.carson.terrariaItems.projectilesFolder.projectiles.ornaments;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class BlueOrnament extends Projectile {

    public BlueOrnament(Plugin plugin) {
        super(plugin, 0, 0.75f, 75, "blue_ornament", "BlueOrnament", DamageType.PLAYER_ATTACK);
    }

    @Override
    public void hitEffect(LivingEntity entity) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:frost_bolt_impact", 1.0F, 1.0F);
    }


}
