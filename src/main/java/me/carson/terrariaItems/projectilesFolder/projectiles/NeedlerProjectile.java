package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NeedlerProjectile extends Projectile {

    public NeedlerProjectile(Plugin plugin) {
        super(plugin, 0, 3f, 75, "needler_projectile", "NeedlerProjectile", DamageType.ARROW);
    }

    @Override
    public void hitEffect(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,30,3,false,true,true));
    }

}
