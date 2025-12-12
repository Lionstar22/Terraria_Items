package me.carson.terrariaItems.projectilesFolder.projectiles.ornaments;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class StarOrnament extends Projectile {

    public StarOrnament(Plugin plugin) {
        super(plugin, 0, "star_ornament", "StarOrnament",0, DamageType.PLAYER_ATTACK);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:frost_bolt_impact", 1.0F, 1.0F);
    }

    @Override
    public void hitBlockEffect(Block block) {

    }

}
