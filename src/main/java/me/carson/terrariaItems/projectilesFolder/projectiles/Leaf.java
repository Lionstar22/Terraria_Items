package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.Bukkit;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Leaf extends Projectile {

    public Leaf(Plugin plugin) {
        super(plugin, 0,  "leaf_blade_projectile", "Leaf",0, DamageType.PLAYER_ATTACK);
    }

    @Override
    public void hitEffect(LivingEntity entity) {
        if(Math.random()<0.25){
            entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,140,2,false,true,true));
        }
    }
}
