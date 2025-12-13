package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class Icicle extends Projectile {

    public Icicle(Plugin plugin) {
        super(plugin, 0,  "icicle", "Icicle",0,0, DamageType.PLAYER_ATTACK);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.getWorld().playSound(entity.getLocation(), "terraria:frost_bolt_impact", 1.5F, 1.0F);
    }

    @Override
    public void hitBlockEffect(Block block) {
       block.getWorld().playSound(block.getLocation(), "terraria:frost_bolt_impact", 1.5F, 1.0F);
    }

}
