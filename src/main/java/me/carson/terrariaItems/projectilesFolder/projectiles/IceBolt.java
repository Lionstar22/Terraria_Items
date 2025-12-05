package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class IceBolt extends Projectile {

    public IceBolt(Plugin plugin) {
        super(plugin, 8, 1f, 0, 20, "ice_bolt", "IceBolt", DamageType.PLAYER_ATTACK);
    }

    @Override
    public void hitEffect(LivingEntity entity) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:frost_bolt_impact", 1.0F, 1.0F);
    }

}
